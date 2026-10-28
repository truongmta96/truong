package vn.truong.warehouse.auth;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import vn.truong.warehouse.repository.AppUserRepository;

/**
 * Kiểm tra lại tài khoản, mật khẩu và quyền ở mỗi yêu cầu đã đăng nhập. Chặn phiên cũ nếu tài khoản bị khóa, bị xóa hoặc mật khẩu đã đổi.
 */
public class SessionAccountFilter extends OncePerRequestFilter {
    private final AppUserRepository users; private final AuthService accounts;
    /**
     * Nhận các thành phần phụ thuộc qua hàm tạo để Spring kết nối lớp này với tầng xử lý bên dưới.
     */
    public SessionAccountFilter(AppUserRepository users,AuthService accounts){this.users=users;this.accounts=accounts;}
    /**
     * Đối chiếu tài khoản và dấu mật khẩu trong phiên với database; nếu hợp lệ thì nạp lại quyền, nếu không thì hủy phiên và trả HTTP 401.
     * @param request Yêu cầu HTTP hiện tại.
     * @param response Phản hồi HTTP gửi về trình duyệt.
     * @param chain Chuỗi bộ lọc tiếp theo, chỉ được gọi khi kiểm tra hiện tại cho phép.
     */
    @Override protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain)throws ServletException,IOException{
        var auth=SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null && auth.getPrincipal() instanceof AuthPrincipal principal){
            var user=users.findById(principal.id()).orElse(null);
            if(user==null||!Boolean.TRUE.equals(user.getIsActive())||!principal.fingerprint().equals(AuthService.fingerprint(user.getPasswordHash()))){
                SecurityContextHolder.clearContext();var session=request.getSession(false);if(session!=null)session.invalidate();
                response.setStatus(401);response.setContentType("application/json;charset=UTF-8");response.getWriter().write("{\"message\":\"Phiên đăng nhập đã hết hiệu lực. Vui lòng đăng nhập lại.\"}");return;
            }
            var fresh=UsernamePasswordAuthenticationToken.authenticated(principal,null,accounts.roles(principal.id()).stream().map(r->new SimpleGrantedAuthority("ROLE_"+r)).toList());
            SecurityContextHolder.getContext().setAuthentication(fresh);
        }
        chain.doFilter(request,response);
    }
}
