package vn.truong.warehouse.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.*;
import vn.truong.warehouse.enums.*;

/**
 * Ánh xạ bảng stock_movement trong MySQL thành đối tượng Java dùng cho sổ biến động. Khóa ngoại được giữ bằng ID; các ràng buộc liên kết được kiểm tra ở database.
 */
@Entity
@Table(name = "stock_movement")
public class StockMovement {
    /**
     * Lưu id của bản ghi.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned", name = "id", nullable = false)
    private Long id;

    /**
     * Lưu phân bổ của bản ghi.
     */
    @Column(columnDefinition = "bigint unsigned", name = "allocation_id", nullable = false)
    private Long allocationId;

    /**
     * Lưu vật tư của bản ghi.
     */
    @Column(columnDefinition = "bigint unsigned", name = "material_id", nullable = false)
    private Long materialId;

    /**
     * Lưu chiều biến động của bản ghi.
     */
    @Column(columnDefinition = "smallint", name = "direction", nullable = false)
    private Integer direction;

    /**
     * Lưu số lượng cơ sở của bản ghi.
     */
    @Column(name = "base_quantity", nullable = false, precision = 20, scale = 6)
    private BigDecimal baseQuantity;

    /**
     * Lưu giá vốn đơn vị của bản ghi.
     */
    @Column(name = "unit_cost", nullable = false, precision = 24, scale = 6)
    private BigDecimal unitCost;

    /**
     * Lưu giá trị của bản ghi.
     */
    @Column(name = "value_amount", nullable = false, precision = 30, scale = 6)
    private BigDecimal valueAmount;

    /**
     * Lưu phương pháp giá của bản ghi.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "costing_method", nullable = false)
    private StockMovementCostingMethod costingMethod;

    /**
     * Lưu ngày ghi sổ của bản ghi.
     */
    @Column(name = "posted_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime postedAt;

    /**
     * Lưu người ghi sổ của bản ghi.
     */
    @Column(columnDefinition = "bigint unsigned", name = "posted_by", nullable = false)
    private Long postedBy;

    /**
     * Hàm tạo rỗng phục vụ JPA khôi phục bản ghi.
     */
    public StockMovement() {}
    /**
     * Đọc giá trị id hiện có trong đối tượng Java.
     */
    public Long getId() { return id; }
    /**
     * Gán giá trị id trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setId(Long value) { this.id = value; }
    /**
     * Đọc giá trị phân bổ hiện có trong đối tượng Java.
     */
    public Long getAllocationId() { return allocationId; }
    /**
     * Gán giá trị phân bổ trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setAllocationId(Long value) { this.allocationId = value; }
    /**
     * Đọc giá trị vật tư hiện có trong đối tượng Java.
     */
    public Long getMaterialId() { return materialId; }
    /**
     * Gán giá trị vật tư trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setMaterialId(Long value) { this.materialId = value; }
    /**
     * Đọc giá trị chiều biến động hiện có trong đối tượng Java.
     */
    public Integer getDirection() { return direction; }
    /**
     * Gán giá trị chiều biến động trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setDirection(Integer value) { this.direction = value; }
    /**
     * Đọc giá trị số lượng cơ sở hiện có trong đối tượng Java.
     */
    public BigDecimal getBaseQuantity() { return baseQuantity; }
    /**
     * Gán giá trị số lượng cơ sở trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setBaseQuantity(BigDecimal value) { this.baseQuantity = value; }
    /**
     * Đọc giá trị giá vốn đơn vị hiện có trong đối tượng Java.
     */
    public BigDecimal getUnitCost() { return unitCost; }
    /**
     * Gán giá trị giá vốn đơn vị trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setUnitCost(BigDecimal value) { this.unitCost = value; }
    /**
     * Đọc giá trị giá trị hiện có trong đối tượng Java.
     */
    public BigDecimal getValueAmount() { return valueAmount; }
    /**
     * Gán giá trị giá trị trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setValueAmount(BigDecimal value) { this.valueAmount = value; }
    /**
     * Đọc giá trị phương pháp giá hiện có trong đối tượng Java.
     */
    public StockMovementCostingMethod getCostingMethod() { return costingMethod; }
    /**
     * Gán giá trị phương pháp giá trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setCostingMethod(StockMovementCostingMethod value) { this.costingMethod = value; }
    /**
     * Đọc giá trị ngày ghi sổ hiện có trong đối tượng Java.
     */
    public LocalDateTime getPostedAt() { return postedAt; }
    /**
     * Gán giá trị ngày ghi sổ trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setPostedAt(LocalDateTime value) { this.postedAt = value; }
    /**
     * Đọc giá trị người ghi sổ hiện có trong đối tượng Java.
     */
    public Long getPostedBy() { return postedBy; }
    /**
     * Gán giá trị người ghi sổ trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setPostedBy(Long value) { this.postedBy = value; }
}
