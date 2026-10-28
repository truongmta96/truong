package vn.truong.warehouse.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Khóa ghép định danh một liên kết quy đổi đơn vị. Các thành phần phải được xét cùng nhau khi so sánh hoặc tìm bản ghi.
 */
public class MaterialUnitId implements Serializable {
    /**
     * Thành phần vật tư của khóa ghép.
     */
    public Long materialId;
    /**
     * Thành phần đơn vị tính của khóa ghép.
     */
    public Long unitId;
    /**
     * Tạo đối tượng khóa rỗng để JPA khôi phục từ database, hoặc nhận đủ thành phần khóa từ bên gọi.
     */
    public MaterialUnitId() {}
    /**
     * Tạo đối tượng khóa rỗng để JPA khôi phục từ database, hoặc nhận đủ thành phần khóa từ bên gọi.
     */
    public MaterialUnitId(Long materialId, Long unitId) { this.materialId = materialId; this.unitId = unitId; }
    /**
     * So sánh đầy đủ các thành phần khóa để hai đối tượng cùng liên kết được nhận diện là bằng nhau.
     */
    @Override public boolean equals(Object o) { if (this == o) return true; if (!(o instanceof MaterialUnitId that)) return false; return Objects.equals(materialId, that.materialId) && Objects.equals(unitId, that.unitId); }
    /**
     * Tính mã băm từ cùng các thành phần được dùng trong equals, phục vụ tập hợp và bộ nhớ đệm của JPA.
     */
    @Override public int hashCode() { return Objects.hash(materialId, unitId); }
}
