package vn.truong.warehouse.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.*;

@Entity
@Table(name = "inventory_balance")
public class InventoryBalance {
    @Id
    @Column(columnDefinition = "bigint unsigned", name = "material_id", nullable = false)
    private Long materialId;

    @Column(name = "quantity_on_hand", nullable = false, precision = 24, scale = 6)
    private BigDecimal quantityOnHand;

    @Column(name = "inventory_value", nullable = false, precision = 30, scale = 6)
    private BigDecimal inventoryValue;

    @Version
    @Column(columnDefinition = "bigint unsigned", name = "version", nullable = false)
    private Long version;

    @Column(name = "updated_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    public InventoryBalance() {}
    public Long getMaterialId() { return materialId; }
    public void setMaterialId(Long value) { this.materialId = value; }
    public BigDecimal getQuantityOnHand() { return quantityOnHand; }
    public void setQuantityOnHand(BigDecimal value) { this.quantityOnHand = value; }
    public BigDecimal getInventoryValue() { return inventoryValue; }
    public void setInventoryValue(BigDecimal value) { this.inventoryValue = value; }
    public Long getVersion() { return version; }
    public void setVersion(Long value) { this.version = value; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime value) { this.updatedAt = value; }
}
