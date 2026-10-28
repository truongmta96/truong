package vn.truong.warehouse.entity;

import jakarta.persistence.*;
import java.time.*;

/**
 * Ánh xạ bảng app_user trong MySQL thành đối tượng Java dùng cho người dùng. Khóa ngoại được giữ bằng ID; các ràng buộc liên kết được kiểm tra ở database. Chỉ chứa chuỗi băm mật khẩu, và trường này bị loại khỏi JSON.
 */
@Entity
@Table(name = "app_user")
public class AppUser {
    /**
     * Lưu id của bản ghi.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned", name = "id", nullable = false)
    private Long id;

    /**
     * Lưu tên đăng nhập của bản ghi.
     */
    @Column(name = "username", nullable = false, length = 150)
    private String username;

    /**
     * Lưu chuỗi mật khẩu đã băm của bản ghi.
     */
    @com.fasterxml.jackson.annotation.JsonIgnore
    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    /**
     * Lưu họ và tên của bản ghi.
     */
    @Column(name = "full_name", nullable = false, length = 200)
    private String fullName;

    /**
     * Lưu trạng thái của bản ghi.
     */
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    /**
     * Lưu ngày tạo của bản ghi.
     */
    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Hàm tạo rỗng phục vụ JPA khôi phục bản ghi.
     */
    public AppUser() {}
    /**
     * Đọc giá trị id hiện có trong đối tượng Java.
     */
    public Long getId() { return id; }
    /**
     * Gán giá trị id trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setId(Long value) { this.id = value; }
    /**
     * Đọc giá trị tên đăng nhập hiện có trong đối tượng Java.
     */
    public String getUsername() { return username; }
    /**
     * Gán giá trị tên đăng nhập trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setUsername(String value) { this.username = value; }
    /**
     * Đọc giá trị chuỗi mật khẩu đã băm hiện có trong đối tượng Java. Chỉ dùng nội bộ cho xác thực; không trả chuỗi băm trong JSON.
     */
    public String getPasswordHash() { return passwordHash; }
    /**
     * Gán giá trị chuỗi mật khẩu đã băm trong đối tượng Java; bản thân hàm này không ghi database. Bên gọi phải cung cấp chuỗi đã băm, không truyền mật khẩu thô.
     */
    public void setPasswordHash(String value) { this.passwordHash = value; }
    /**
     * Đọc giá trị họ và tên hiện có trong đối tượng Java.
     */
    public String getFullName() { return fullName; }
    /**
     * Gán giá trị họ và tên trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setFullName(String value) { this.fullName = value; }
    /**
     * Đọc giá trị trạng thái hiện có trong đối tượng Java.
     */
    public Boolean getIsActive() { return isActive; }
    /**
     * Gán giá trị trạng thái trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setIsActive(Boolean value) { this.isActive = value; }
    /**
     * Đọc giá trị ngày tạo hiện có trong đối tượng Java.
     */
    public LocalDateTime getCreatedAt() { return createdAt; }
    /**
     * Gán giá trị ngày tạo trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setCreatedAt(LocalDateTime value) { this.createdAt = value; }
}
