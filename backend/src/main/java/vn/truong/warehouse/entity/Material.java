package vn.truong.warehouse.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.*;

@Entity
@Table(name = "material")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned", name = "id", nullable = false)
    private Long id;

    @Column(name = "code", nullable = false, length = 60)
    private String code;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(columnDefinition = "bigint unsigned", name = "category_id")
    private Long categoryId;

    @Column(columnDefinition = "bigint unsigned", name = "base_unit_id", nullable = false)
    private Long baseUnitId;

    @Column(name = "track_lot", nullable = false)
    private Boolean trackLot;

    @Column(name = "track_serial", nullable = false)
    private Boolean trackSerial;

    @Column(name = "track_expiry", nullable = false)
    private Boolean trackExpiry;

    @Column(name = "allow_fractional_quantity", nullable = false)
    private Boolean allowFractionalQuantity;

    @Column(name = "min_stock", nullable = false, precision = 20, scale = 6)
    private BigDecimal minStock;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public Material() {}
    public Long getId() { return id; }
    public void setId(Long value) { this.id = value; }
    public String getCode() { return code; }
    public void setCode(String value) { this.code = value; }
    public String getName() { return name; }
    public void setName(String value) { this.name = value; }
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long value) { this.categoryId = value; }
    public Long getBaseUnitId() { return baseUnitId; }
    public void setBaseUnitId(Long value) { this.baseUnitId = value; }
    public Boolean getTrackLot() { return trackLot; }
    public void setTrackLot(Boolean value) { this.trackLot = value; }
    public Boolean getTrackSerial() { return trackSerial; }
    public void setTrackSerial(Boolean value) { this.trackSerial = value; }
    public Boolean getTrackExpiry() { return trackExpiry; }
    public void setTrackExpiry(Boolean value) { this.trackExpiry = value; }
    public Boolean getAllowFractionalQuantity() { return allowFractionalQuantity; }
    public void setAllowFractionalQuantity(Boolean value) { this.allowFractionalQuantity = value; }
    public BigDecimal getMinStock() { return minStock; }
    public void setMinStock(BigDecimal value) { this.minStock = value; }
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean value) { this.isActive = value; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime value) { this.createdAt = value; }
}
