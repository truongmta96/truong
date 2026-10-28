package vn.truong.warehouse.entity;

import jakarta.persistence.*;

/**
 * Ánh xạ bảng user_role trong MySQL thành đối tượng Java dùng cho phân quyền. Khóa ngoại được giữ bằng ID; các ràng buộc liên kết được kiểm tra ở database.
 */
@Entity
@Table(name = "user_role")
@IdClass(UserRoleId.class)
public class UserRole {
    /**
     * Lưu người dùng của bản ghi.
     */
    @Id
    @Column(columnDefinition = "bigint unsigned", name = "user_id", nullable = false)
    private Long userId;

    /**
     * Lưu vai trò của bản ghi.
     */
    @Id
    @Column(columnDefinition = "bigint unsigned", name = "role_id", nullable = false)
    private Long roleId;

    /**
     * Hàm tạo rỗng phục vụ JPA khôi phục bản ghi.
     */
    public UserRole() {}
    /**
     * Đọc giá trị người dùng hiện có trong đối tượng Java.
     */
    public Long getUserId() { return userId; }
    /**
     * Gán giá trị người dùng trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setUserId(Long value) { this.userId = value; }
    /**
     * Đọc giá trị vai trò hiện có trong đối tượng Java.
     */
    public Long getRoleId() { return roleId; }
    /**
     * Gán giá trị vai trò trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setRoleId(Long value) { this.roleId = value; }
}
