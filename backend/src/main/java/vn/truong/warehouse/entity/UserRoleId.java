package vn.truong.warehouse.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserRoleId implements Serializable {
    public Long userId;
    public Long roleId;
    public UserRoleId() {}
    public UserRoleId(Long userId, Long roleId) { this.userId = userId; this.roleId = roleId; }
    @Override public boolean equals(Object o) { if (this == o) return true; if (!(o instanceof UserRoleId that)) return false; return Objects.equals(userId, that.userId) && Objects.equals(roleId, that.roleId); }
    @Override public int hashCode() { return Objects.hash(userId, roleId); }
}
