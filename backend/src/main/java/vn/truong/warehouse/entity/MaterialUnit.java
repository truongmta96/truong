package vn.truong.warehouse.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "material_unit")
@IdClass(MaterialUnitId.class)
public class MaterialUnit {
    @Id
    @Column(columnDefinition = "bigint unsigned", name = "material_id", nullable = false)
    private Long materialId;

    @Id
    @Column(columnDefinition = "bigint unsigned", name = "unit_id", nullable = false)
    private Long unitId;

    @Column(name = "conversion_factor", nullable = false, precision = 20, scale = 6)
    private BigDecimal conversionFactor;

    @Column(name = "allow_receipt", nullable = false)
    private Boolean allowReceipt;

    @Column(name = "allow_issue", nullable = false)
    private Boolean allowIssue;

    public MaterialUnit() {}
    public Long getMaterialId() { return materialId; }
    public void setMaterialId(Long value) { this.materialId = value; }
    public Long getUnitId() { return unitId; }
    public void setUnitId(Long value) { this.unitId = value; }
    public BigDecimal getConversionFactor() { return conversionFactor; }
    public void setConversionFactor(BigDecimal value) { this.conversionFactor = value; }
    public Boolean getAllowReceipt() { return allowReceipt; }
    public void setAllowReceipt(Boolean value) { this.allowReceipt = value; }
    public Boolean getAllowIssue() { return allowIssue; }
    public void setAllowIssue(Boolean value) { this.allowIssue = value; }
}
