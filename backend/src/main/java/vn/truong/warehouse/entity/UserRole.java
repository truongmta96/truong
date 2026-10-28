package vn.truong.warehouse.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_role")
@IdClass(UserRoleId.class)
public class UserRole {
    @Id
    @Column(columnDefinition = "bigint unsigned", name = "user_id", nullable = false)
    private Long userId;

    @Id
    @Column(columnDefinition = "bigint unsigned", name = "role_id", nullable = false)
    private Long roleId;

    public UserRole() {}
    public Long getUserId() { return userId; }
    public void setUserId(Long value) { this.userId = value; }
    public Long getRoleId() { return roleId; }
    public void setRoleId(Long value) { this.roleId = value; }
}
