package vn.truong.warehouse.entity;

import java.io.Serializable;
import java.util.Objects;

public class MaterialUnitId implements Serializable {
    public Long materialId;
    public Long unitId;
    public MaterialUnitId() {}
    public MaterialUnitId(Long materialId, Long unitId) { this.materialId = materialId; this.unitId = unitId; }
    @Override public boolean equals(Object o) { if (this == o) return true; if (!(o instanceof MaterialUnitId that)) return false; return Objects.equals(materialId, that.materialId) && Objects.equals(unitId, that.unitId); }
    @Override public int hashCode() { return Objects.hash(materialId, unitId); }
}
