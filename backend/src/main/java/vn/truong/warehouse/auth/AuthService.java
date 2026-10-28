package vn.truong.warehouse.auth;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.jdbc.core.JdbcTemplate;
import vn.truong.warehouse.entity.AppUser;
import vn.truong.warehouse.repository.AppUserRepository;

/**
 * Xử lý nghiệp vụ tài khoản: băm mật khẩu BCrypt, gán quyền VIEWER khi đăng ký và cập nhật hồ sơ. Không nhận quyền quản trị do trình duyệt tự truyền lên.
 */
@Service
public class AuthService {
    private final AppUserRepository users;
    private final PasswordEncoder encoder;
    private final JdbcTemplate jdbc;
    /**
     * Nhận các thành phần phụ thuộc qua hàm tạo để Spring kết nối lớp này với tầng xử lý bên dưới.
     */
    public AuthService(AppUserRepository users, PasswordEncoder encoder, JdbcTemplate jdbc) { this.users=users; this.encoder=encoder; this.jdbc=jdbc; }
    /**
     * Hồ sơ an toàn trả cho giao diện, gồm định danh, họ tên và vai trò; không có mật khẩu hoặc chuỗi băm.
     * @param id Id.
     * @param username Tên đăng nhập.
     * @param fullName Họ và tên.
     * @param roles Danh sách vai trò.
     */
    public record Account(Long id, String username, String fullName, List<String> roles) {}
    /**
     * Đọc các mã vai trò được gán cho ID tài khoản trong MySQL; không tin danh sách vai trò từ trình duyệt.
     * @param id Khóa của bản ghi; với bảng liên kết có thể là một khóa gồm nhiều thành phần.
     */
    public List<String> roles(Long id) { return jdbc.queryForList("SELECT r.code FROM app_role r JOIN user_role ur ON ur.role_id=r.id WHERE ur.user_id=?", String.class, id); }
    /**
     * Chuyển tài khoản trong database thành hồ sơ an toàn kèm danh sách vai trò; bỏ hoàn toàn mật khẩu và chuỗi băm.
     * @param user Tài khoản hoặc thông tin định danh do tầng xác thực cung cấp.
     */
    public Account account(AppUser user) { return new Account(user.getId(),user.getUsername(),user.getFullName(),roles(user.getId())); }
    /**
     * Tìm tài khoản theo ID; báo lỗi xác thực nếu tài khoản trong phiên không còn tồn tại.
     * @param id Khóa của bản ghi; với bảng liên kết có thể là một khóa gồm nhiều thành phần.
     */
    public AppUser require(Long id) { return users.findById(id).orElseThrow(()->new BadCredentialsException("Phiên đăng nhập không hợp lệ.")); }
    /**
     * Tạo dấu SHA-256 từ chuỗi mật khẩu đã băm để phát hiện mật khẩu đã đổi giữa các phiên. Đây không phải hàm băm mật khẩu để lưu vào database; việc đó dùng BCrypt.
     * @param hash Chuỗi mật khẩu đã băm, không phải mật khẩu thô.
     */
    public static String fingerprint(String hash) {
        try { return HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(hash.getBytes(StandardCharsets.UTF_8))); }
        catch (java.security.NoSuchAlgorithmException e) { throw new IllegalStateException(e); }
    }
    /**
     * Kiểm tra giới hạn 72 byte UTF-8 của BCrypt. Số byte có thể lớn hơn số ký tự khi mật khẩu có dấu.
     */
    private void password(String value) { if (value.getBytes(StandardCharsets.UTF_8).length>72) throw new IllegalArgumentException("Mật khẩu không được vượt 72 byte UTF-8."); }
    /**
     * Kiểm tra tên trùng và vai trò VIEWER, băm mật khẩu rồi lưu tài khoản cùng liên kết vai trò trong một transaction.
     * @param username Tên đăng nhập cần kiểm tra hoặc tạo mới.
     * @param fullName Họ tên mới; không dùng để thay đổi tên đăng nhập hay vai trò.
     */
    @Transactional
    public Account register(String username,String fullName,String password) {
        password(password);
        if(users.findByUsername(username).isPresent()) throw new org.springframework.dao.DuplicateKeyException("Username exists");
        var role = jdbc.queryForList("SELECT id FROM app_role WHERE code='VIEWER'",Long.class);
        if(role.isEmpty()) throw new IllegalStateException("Missing VIEWER role; initialize schema seeds first");
        var user=new AppUser();user.setUsername(username);user.setFullName(fullName.strip());user.setPasswordHash(encoder.encode(password));user.setIsActive(true);
        users.saveAndFlush(user);
        jdbc.update("INSERT INTO user_role(user_id,role_id) VALUES (?,?)",user.getId(),role.getFirst());
        return account(user);
    }
    /**
     * Chỉ sửa họ tên của tài khoản được chỉ định bởi tầng xác thực; giữ nguyên tên đăng nhập, mật khẩu, quyền và trạng thái.
     * @param id Khóa của bản ghi; với bảng liên kết có thể là một khóa gồm nhiều thành phần.
     * @param fullName Họ tên mới; không dùng để thay đổi tên đăng nhập hay vai trò.
     */
    @Transactional
    public Account update(Long id,String fullName){var user=require(id);user.setFullName(fullName.strip());users.saveAndFlush(user);return account(user);}
    /**
     * Kiểm tra mật khẩu cũ, từ chối mật khẩu mới giống mật khẩu cũ và lưu BCrypt mới. Bộ lọc phiên sẽ từ chối các phiên còn giữ dấu mật khẩu cũ.
     * @param id Khóa của bản ghi; với bảng liên kết có thể là một khóa gồm nhiều thành phần.
     * @param current Mật khẩu hiện tại để xác minh quyền đổi mật khẩu.
     * @param next Mật khẩu mới cần kiểm tra và băm trước khi lưu.
     */
    @Transactional
    public void changePassword(Long id,String current,String next){password(next);var user=require(id);if(!encoder.matches(current,user.getPasswordHash()))throw new BadCredentialsException("Mật khẩu hiện tại không đúng.");if(encoder.matches(next,user.getPasswordHash()))throw new IllegalArgumentException("Mật khẩu mới phải khác mật khẩu hiện tại.");user.setPasswordHash(encoder.encode(next));users.saveAndFlush(user);}
}
