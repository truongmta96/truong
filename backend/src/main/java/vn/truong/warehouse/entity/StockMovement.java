package vn.truong.warehouse.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.*;
import vn.truong.warehouse.enums.*;

@Entity
@Table(name = "stock_movement")
public class StockMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned", name = "id", nullable = false)
    private Long id;

    @Column(columnDefinition = "bigint unsigned", name = "allocation_id", nullable = false)
    private Long allocationId;

    @Column(columnDefinition = "bigint unsigned", name = "material_id", nullable = false)
    private Long materialId;

    @Column(columnDefinition = "smallint", name = "direction", nullable = false)
    private Integer direction;

    @Column(name = "base_quantity", nullable = false, precision = 20, scale = 6)
    private BigDecimal baseQuantity;

    @Column(name = "unit_cost", nullable = false, precision = 24, scale = 6)
    private BigDecimal unitCost;

    @Column(name = "value_amount", nullable = false, precision = 30, scale = 6)
    private BigDecimal valueAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "costing_method", nullable = false)
    private StockMovementCostingMethod costingMethod;

    @Column(name = "posted_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime postedAt;

    @Column(columnDefinition = "bigint unsigned", name = "posted_by", nullable = false)
    private Long postedBy;

    public StockMovement() {}
    public Long getId() { return id; }
    public void setId(Long value) { this.id = value; }
    public Long getAllocationId() { return allocationId; }
    public void setAllocationId(Long value) { this.allocationId = value; }
    public Long getMaterialId() { return materialId; }
    public void setMaterialId(Long value) { this.materialId = value; }
    public Integer getDirection() { return direction; }
    public void setDirection(Integer value) { this.direction = value; }
    public BigDecimal getBaseQuantity() { return baseQuantity; }
    public void setBaseQuantity(BigDecimal value) { this.baseQuantity = value; }
    public BigDecimal getUnitCost() { return unitCost; }
    public void setUnitCost(BigDecimal value) { this.unitCost = value; }
    public BigDecimal getValueAmount() { return valueAmount; }
    public void setValueAmount(BigDecimal value) { this.valueAmount = value; }
    public StockMovementCostingMethod getCostingMethod() { return costingMethod; }
    public void setCostingMethod(StockMovementCostingMethod value) { this.costingMethod = value; }
    public LocalDateTime getPostedAt() { return postedAt; }
    public void setPostedAt(LocalDateTime value) { this.postedAt = value; }
    public Long getPostedBy() { return postedBy; }
    public void setPostedBy(Long value) { this.postedBy = value; }
}
