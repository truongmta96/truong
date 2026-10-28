package vn.truong.warehouse.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Khóa ghép định danh một liên kết phân quyền. Các thành phần phải được xét cùng nhau khi so sánh hoặc tìm bản ghi.
 */
public class UserRoleId implements Serializable {
    /**
     * Thành phần người dùng của khóa ghép.
     */
    public Long userId;
    /**
     * Thành phần vai trò của khóa ghép.
     */
    public Long roleId;
    /**
     * Tạo đối tượng khóa rỗng để JPA khôi phục từ database, hoặc nhận đủ thành phần khóa từ bên gọi.
     */
    public UserRoleId() {}
    /**
     * Tạo đối tượng khóa rỗng để JPA khôi phục từ database, hoặc nhận đủ thành phần khóa từ bên gọi.
     */
    public UserRoleId(Long userId, Long roleId) { this.userId = userId; this.roleId = roleId; }
    /**
     * So sánh đầy đủ các thành phần khóa để hai đối tượng cùng liên kết được nhận diện là bằng nhau.
     */
    @Override public boolean equals(Object o) { if (this == o) return true; if (!(o instanceof UserRoleId that)) return false; return Objects.equals(userId, that.userId) && Objects.equals(roleId, that.roleId); }
    /**
     * Tính mã băm từ cùng các thành phần được dùng trong equals, phục vụ tập hợp và bộ nhớ đệm của JPA.
     */
    @Override public int hashCode() { return Objects.hash(userId, roleId); }
}
