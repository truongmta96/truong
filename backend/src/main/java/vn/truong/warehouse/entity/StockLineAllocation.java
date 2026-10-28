package vn.truong.warehouse.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Ánh xạ bảng stock_line_allocation trong MySQL thành đối tượng Java dùng cho phân bổ lô / serial. Khóa ngoại được giữ bằng ID; các ràng buộc liên kết được kiểm tra ở database.
 */
@Entity
@Table(name = "stock_line_allocation")
public class StockLineAllocation {
    /**
     * Lưu id của bản ghi.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned", name = "id", nullable = false)
    private Long id;

    /**
     * Lưu dòng phiếu của bản ghi.
     */
    @Column(columnDefinition = "bigint unsigned", name = "line_id", nullable = false)
    private Long lineId;

    /**
     * Lưu vật tư của bản ghi.
     */
    @Column(columnDefinition = "bigint unsigned", name = "material_id", nullable = false)
    private Long materialId;

    /**
     * Lưu lô vật tư của bản ghi.
     */
    @Column(columnDefinition = "bigint unsigned", name = "lot_id")
    private Long lotId;

    /**
     * Lưu serial của bản ghi.
     */
    @Column(columnDefinition = "bigint unsigned", name = "serial_id")
    private Long serialId;

    /**
     * Lưu số lượng cơ sở của bản ghi.
     */
    @Column(name = "base_quantity", nullable = false, precision = 20, scale = 6)
    private BigDecimal baseQuantity;

    /**
     * Hàm tạo rỗng phục vụ JPA khôi phục bản ghi.
     */
    public StockLineAllocation() {}
    /**
     * Đọc giá trị id hiện có trong đối tượng Java.
     */
    public Long getId() { return id; }
    /**
     * Gán giá trị id trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setId(Long value) { this.id = value; }
    /**
     * Đọc giá trị dòng phiếu hiện có trong đối tượng Java.
     */
    public Long getLineId() { return lineId; }
    /**
     * Gán giá trị dòng phiếu trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setLineId(Long value) { this.lineId = value; }
    /**
     * Đọc giá trị vật tư hiện có trong đối tượng Java.
     */
    public Long getMaterialId() { return materialId; }
    /**
     * Gán giá trị vật tư trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setMaterialId(Long value) { this.materialId = value; }
    /**
     * Đọc giá trị lô vật tư hiện có trong đối tượng Java.
     */
    public Long getLotId() { return lotId; }
    /**
     * Gán giá trị lô vật tư trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setLotId(Long value) { this.lotId = value; }
    /**
     * Đọc giá trị serial hiện có trong đối tượng Java.
     */
    public Long getSerialId() { return serialId; }
    /**
     * Gán giá trị serial trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setSerialId(Long value) { this.serialId = value; }
    /**
     * Đọc giá trị số lượng cơ sở hiện có trong đối tượng Java.
     */
    public BigDecimal getBaseQuantity() { return baseQuantity; }
    /**
     * Gán giá trị số lượng cơ sở trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setBaseQuantity(BigDecimal value) { this.baseQuantity = value; }
}
