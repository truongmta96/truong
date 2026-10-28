package vn.truong.warehouse.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Ánh xạ bảng cost_allocation trong MySQL thành đối tượng Java dùng cho phân bổ giá vốn. Khóa ngoại được giữ bằng ID; các ràng buộc liên kết được kiểm tra ở database.
 */
@Entity
@Table(name = "cost_allocation")
public class CostAllocation {
    /**
     * Lưu id của bản ghi.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned", name = "id", nullable = false)
    private Long id;

    /**
     * Lưu biến động xuất của bản ghi.
     */
    @Column(columnDefinition = "bigint unsigned", name = "issue_movement_id", nullable = false)
    private Long issueMovementId;

    /**
     * Lưu biến động nhập của bản ghi.
     */
    @Column(columnDefinition = "bigint unsigned", name = "receipt_movement_id", nullable = false)
    private Long receiptMovementId;

    /**
     * Lưu số lượng cơ sở của bản ghi.
     */
    @Column(name = "base_quantity", nullable = false, precision = 20, scale = 6)
    private BigDecimal baseQuantity;

    /**
     * Lưu giá trị của bản ghi.
     */
    @Column(name = "value_amount", nullable = false, precision = 30, scale = 6)
    private BigDecimal valueAmount;

    /**
     * Hàm tạo rỗng phục vụ JPA khôi phục bản ghi.
     */
    public CostAllocation() {}
    /**
     * Đọc giá trị id hiện có trong đối tượng Java.
     */
    public Long getId() { return id; }
    /**
     * Gán giá trị id trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setId(Long value) { this.id = value; }
    /**
     * Đọc giá trị biến động xuất hiện có trong đối tượng Java.
     */
    public Long getIssueMovementId() { return issueMovementId; }
    /**
     * Gán giá trị biến động xuất trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setIssueMovementId(Long value) { this.issueMovementId = value; }
    /**
     * Đọc giá trị biến động nhập hiện có trong đối tượng Java.
     */
    public Long getReceiptMovementId() { return receiptMovementId; }
    /**
     * Gán giá trị biến động nhập trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setReceiptMovementId(Long value) { this.receiptMovementId = value; }
    /**
     * Đọc giá trị số lượng cơ sở hiện có trong đối tượng Java.
     */
    public BigDecimal getBaseQuantity() { return baseQuantity; }
    /**
     * Gán giá trị số lượng cơ sở trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setBaseQuantity(BigDecimal value) { this.baseQuantity = value; }
    /**
     * Đọc giá trị giá trị hiện có trong đối tượng Java.
     */
    public BigDecimal getValueAmount() { return valueAmount; }
    /**
     * Gán giá trị giá trị trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setValueAmount(BigDecimal value) { this.valueAmount = value; }
}
