package vn.truong.warehouse.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.*;

/**
 * Ánh xạ bảng inventory_balance trong MySQL thành đối tượng Java dùng cho tồn kho. Khóa ngoại được giữ bằng ID; các ràng buộc liên kết được kiểm tra ở database. Trường version dùng cho khóa lạc quan; không sửa số dư bằng thao tác danh mục.
 */
@Entity
@Table(name = "inventory_balance")
public class InventoryBalance {
    /**
     * Lưu vật tư của bản ghi.
     */
    @Id
    @Column(columnDefinition = "bigint unsigned", name = "material_id", nullable = false)
    private Long materialId;

    /**
     * Lưu tồn hiện tại của bản ghi.
     */
    @Column(name = "quantity_on_hand", nullable = false, precision = 24, scale = 6)
    private BigDecimal quantityOnHand;

    /**
     * Lưu giá trị tồn của bản ghi.
     */
    @Column(name = "inventory_value", nullable = false, precision = 30, scale = 6)
    private BigDecimal inventoryValue;

    /**
     * Lưu phiên bản của bản ghi.
     */
    @Version
    @Column(columnDefinition = "bigint unsigned", name = "version", nullable = false)
    private Long version;

    /**
     * Lưu cập nhật của bản ghi.
     */
    @Column(name = "updated_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    /**
     * Hàm tạo rỗng phục vụ JPA khôi phục bản ghi.
     */
    public InventoryBalance() {}
    /**
     * Đọc giá trị vật tư hiện có trong đối tượng Java.
     */
    public Long getMaterialId() { return materialId; }
    /**
     * Gán giá trị vật tư trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setMaterialId(Long value) { this.materialId = value; }
    /**
     * Đọc giá trị tồn hiện tại hiện có trong đối tượng Java.
     */
    public BigDecimal getQuantityOnHand() { return quantityOnHand; }
    /**
     * Gán giá trị tồn hiện tại trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setQuantityOnHand(BigDecimal value) { this.quantityOnHand = value; }
    /**
     * Đọc giá trị giá trị tồn hiện có trong đối tượng Java.
     */
    public BigDecimal getInventoryValue() { return inventoryValue; }
    /**
     * Gán giá trị giá trị tồn trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setInventoryValue(BigDecimal value) { this.inventoryValue = value; }
    /**
     * Đọc giá trị phiên bản hiện có trong đối tượng Java.
     */
    public Long getVersion() { return version; }
    /**
     * Gán giá trị phiên bản trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setVersion(Long value) { this.version = value; }
    /**
     * Đọc giá trị cập nhật hiện có trong đối tượng Java.
     */
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    /**
     * Gán giá trị cập nhật trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setUpdatedAt(LocalDateTime value) { this.updatedAt = value; }
}
