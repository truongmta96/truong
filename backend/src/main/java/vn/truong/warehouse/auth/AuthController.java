package vn.truong.warehouse.auth;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import jakarta.servlet.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import vn.truong.warehouse.repository.AppUserRepository;
import java.util.Map;

/**
 * Cung cấp API đăng ký, đăng nhập và quản lý tài khoản của chính người đang đăng nhập. Cookie giữ phiên; các thao tác ghi phải có token CSRF.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService accounts;private final AuthenticationManager manager;private final SecurityContextRepository contexts;private final AppUserRepository users;
    /**
     * Nhận các thành phần phụ thuộc qua hàm tạo để Spring kết nối lớp này với tầng xử lý bên dưới.
     */
    public AuthController(AuthService accounts,AuthenticationManager manager,SecurityContextRepository contexts,AppUserRepository users){this.accounts=accounts;this.manager=manager;this.contexts=contexts;this.users=users;}
    /**
     * Dữ liệu đăng ký từ giao diện; chỉ cho phép tên đăng nhập, họ tên và mật khẩu. Vai trò được backend quyết định.
     * @param username Tên đăng nhập.
     * @param fullName Họ và tên.
     * @param password Mật khẩu.
     */
    public record Register(@NotBlank @Pattern(regexp="[A-Za-z0-9._-]{3,150}") String username,@NotBlank @Size(max=200) String fullName,@NotBlank @Size(min=12,max=72) String password){}
    /**
     * Dữ liệu dùng xác minh tên đăng nhập và mật khẩu; không được ghi mật khẩu vào nhật ký.
     * @param username Tên đăng nhập.
     * @param password Mật khẩu.
     */
    public record Login(@NotBlank @Size(max=150) String username,@NotBlank @Size(max=72) String password){}
    /**
     * Thông tin hồ sơ được phép sửa: họ tên. Không có ID tài khoản, vai trò hoặc trạng thái hoạt động để tránh sửa vượt quyền.
     * @param fullName Họ và tên.
     */
    public record Profile(@NotBlank @Size(max=200) String fullName){}
    /**
     * Yêu cầu đổi mật khẩu, gồm mật khẩu hiện tại và mật khẩu mới. Backend kiểm tra mật khẩu hiện tại trước khi lưu chuỗi băm mới.
     * @param currentPassword Mật khẩu hiện tại.
     * @param newPassword Mật khẩu mới.
     */
    public record Password(@NotBlank @Size(max=72) String currentPassword,@NotBlank @Size(min=12,max=72) String newPassword){}
    /**
     * Trả token và tên header CSRF để trình duyệt gửi kèm các yêu cầu ghi. Token gắn với phiên hiện tại.
     * @param token Token chống giả mạo yêu cầu, gắn với phiên hiện tại.
     */
    @GetMapping("/csrf") public Map<String,String> csrf(CsrfToken token){return Map.of("token",token.getToken(),"headerName",token.getHeaderName());}
    /**
     * Nhận dữ liệu đã được kiểm tra, tạo tài khoản mới với quyền VIEWER và trả hồ sơ. Không tự đăng nhập sau đăng ký.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     */
    @PostMapping("/register") @ResponseStatus(HttpStatus.CREATED)
    public AuthService.Account register(@Valid @RequestBody Register dto){return accounts.register(dto.username(),dto.fullName(),dto.password());}
    /**
     * Xác minh mật khẩu, đổi ID phiên chống chiếm dụng phiên cũ, làm mới token CSRF và lưu thông tin đăng nhập vào phiên.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     * @param req Yêu cầu HTTP hiện tại, dùng để đọc hoặc cập nhật phiên.
     * @param res Phản hồi HTTP gửi về trình duyệt.
     */
    @PostMapping("/login") public AuthService.Account login(@Valid @RequestBody Login dto,HttpServletRequest req,HttpServletResponse res){
        manager.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(dto.username(),dto.password()));
        var user=users.findByUsername(dto.username()).orElseThrow();
        if(req.getSession(false)!=null)req.changeSessionId();else req.getSession(true);
        new org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository().saveToken(null,req,res);
        var principal=new AuthPrincipal(user.getId(),user.getUsername(),AuthService.fingerprint(user.getPasswordHash()));
        var auth=UsernamePasswordAuthenticationToken.authenticated(principal,null,accounts.roles(user.getId()).stream().map(r->new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_"+r)).toList());
        var context=SecurityContextHolder.createEmptyContext();context.setAuthentication(auth);SecurityContextHolder.setContext(context);contexts.saveContext(context,req,res);
        return accounts.account(user);
    }
    /**
     * Lấy hồ sơ của tài khoản trong phiên; không nhận ID tùy ý từ trình duyệt.
     * @param user Tài khoản hoặc thông tin định danh do tầng xác thực cung cấp.
     */
    @GetMapping("/me") public AuthService.Account me(@AuthenticationPrincipal AuthPrincipal user){return accounts.account(accounts.require(user.id()));}
    /**
     * Cập nhật họ tên của chính tài khoản đã đăng nhập. ID lấy từ phiên, không lấy từ nội dung gửi lên.
     * @param user Tài khoản hoặc thông tin định danh do tầng xác thực cung cấp.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     */
    @PutMapping("/me") public AuthService.Account update(@AuthenticationPrincipal AuthPrincipal user,@Valid @RequestBody Profile dto){return accounts.update(user.id(),dto.fullName());}
    /**
     * Đổi mật khẩu sau khi xác minh mật khẩu hiện tại, rồi hủy phiên đang dùng để yêu cầu đăng nhập lại.
     * @param user Tài khoản hoặc thông tin định danh do tầng xác thực cung cấp.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     * @param req Yêu cầu HTTP hiện tại, dùng để đọc hoặc cập nhật phiên.
     */
    @PutMapping("/password") public Map<String,String> password(@AuthenticationPrincipal AuthPrincipal user,@Valid @RequestBody Password dto,HttpServletRequest req){accounts.changePassword(user.id(),dto.currentPassword(),dto.newPassword());SecurityContextHolder.clearContext();if(req.getSession(false)!=null)req.getSession(false).invalidate();return Map.of("message","Đã đổi mật khẩu. Vui lòng đăng nhập lại.");}
}
