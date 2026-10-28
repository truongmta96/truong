package vn.truong.warehouse.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "stock_line_allocation")
public class StockLineAllocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned", name = "id", nullable = false)
    private Long id;

    @Column(columnDefinition = "bigint unsigned", name = "line_id", nullable = false)
    private Long lineId;

    @Column(columnDefinition = "bigint unsigned", name = "material_id", nullable = false)
    private Long materialId;

    @Column(columnDefinition = "bigint unsigned", name = "lot_id")
    private Long lotId;

    @Column(columnDefinition = "bigint unsigned", name = "serial_id")
    private Long serialId;

    @Column(name = "base_quantity", nullable = false, precision = 20, scale = 6)
    private BigDecimal baseQuantity;

    public StockLineAllocation() {}
    public Long getId() { return id; }
    public void setId(Long value) { this.id = value; }
    public Long getLineId() { return lineId; }
    public void setLineId(Long value) { this.lineId = value; }
    public Long getMaterialId() { return materialId; }
    public void setMaterialId(Long value) { this.materialId = value; }
    public Long getLotId() { return lotId; }
    public void setLotId(Long value) { this.lotId = value; }
    public Long getSerialId() { return serialId; }
    public void setSerialId(Long value) { this.serialId = value; }
    public BigDecimal getBaseQuantity() { return baseQuantity; }
    public void setBaseQuantity(BigDecimal value) { this.baseQuantity = value; }
}
