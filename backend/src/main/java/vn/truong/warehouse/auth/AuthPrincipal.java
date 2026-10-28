package vn.truong.warehouse.auth;
import java.io.Serializable;
import java.security.Principal;
/**
 * Thông tin định danh được lưu trong phiên đăng nhập: ID, tên đăng nhập và dấu nhận diện của mật khẩu đã băm. Không chứa mật khẩu thô.
 * @param id Id.
 * @param username Tên đăng nhập.
 * @param fingerprint Dấu nhận diện của chuỗi mật khẩu đã băm.
 */
public record AuthPrincipal(Long id, String username, String fingerprint) implements Serializable, Principal {
    /**
     * Trả tên đăng nhập để Spring Security định danh tài khoản trong phiên.
     */
    @Override public String getName(){return username;}
    /**
     * Chỉ biểu diễn tên đăng nhập, tránh vô tình ghi dấu mật khẩu vào nhật ký khi in đối tượng.
     */
    @Override public String toString(){return username;}
}
