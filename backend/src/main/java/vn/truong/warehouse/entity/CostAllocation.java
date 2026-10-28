package vn.truong.warehouse.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cost_allocation")
public class CostAllocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned", name = "id", nullable = false)
    private Long id;

    @Column(columnDefinition = "bigint unsigned", name = "issue_movement_id", nullable = false)
    private Long issueMovementId;

    @Column(columnDefinition = "bigint unsigned", name = "receipt_movement_id", nullable = false)
    private Long receiptMovementId;

    @Column(name = "base_quantity", nullable = false, precision = 20, scale = 6)
    private BigDecimal baseQuantity;

    @Column(name = "value_amount", nullable = false, precision = 30, scale = 6)
    private BigDecimal valueAmount;

    public CostAllocation() {}
    public Long getId() { return id; }
    public void setId(Long value) { this.id = value; }
    public Long getIssueMovementId() { return issueMovementId; }
    public void setIssueMovementId(Long value) { this.issueMovementId = value; }
    public Long getReceiptMovementId() { return receiptMovementId; }
    public void setReceiptMovementId(Long value) { this.receiptMovementId = value; }
    public BigDecimal getBaseQuantity() { return baseQuantity; }
    public void setBaseQuantity(BigDecimal value) { this.baseQuantity = value; }
    public BigDecimal getValueAmount() { return valueAmount; }
    public void setValueAmount(BigDecimal value) { this.valueAmount = value; }
}
