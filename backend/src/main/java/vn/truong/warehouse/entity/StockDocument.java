package vn.truong.warehouse.entity;

import jakarta.persistence.*;
import java.time.*;
import vn.truong.warehouse.enums.*;

@Entity
@Table(name = "stock_document")
public class StockDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned", name = "id", nullable = false)
    private Long id;

    @Column(name = "document_no", nullable = false, length = 60)
    private String documentNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", nullable = false)
    private StockDocumentDocumentType documentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StockDocumentStatus status;

    @Column(name = "document_date", nullable = false)
    private LocalDate documentDate;

    @Column(columnDefinition = "bigint unsigned", name = "supplier_id")
    private Long supplierId;

    @Column(columnDefinition = "bigint unsigned", name = "recipient_id")
    private Long recipientId;

    @Column(name = "recipient_name_snapshot", length = 200)
    private String recipientNameSnapshot;

    @Column(name = "recipient_department_snapshot", length = 200)
    private String recipientDepartmentSnapshot;

    @Column(name = "reason", length = 1000)
    private String reason;

    @Column(columnDefinition = "bigint unsigned", name = "created_by", nullable = false)
    private Long createdBy;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    @Column(columnDefinition = "bigint unsigned", name = "approved_by")
    private Long approvedBy;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Column(columnDefinition = "bigint unsigned", name = "posted_by")
    private Long postedBy;

    @Column(name = "posted_at")
    private LocalDateTime postedAt;

    @Column(columnDefinition = "int unsigned", name = "revision", nullable = false)
    private Long revision;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    public StockDocument() {}
    public Long getId() { return id; }
    public void setId(Long value) { this.id = value; }
    public String getDocumentNo() { return documentNo; }
    public void setDocumentNo(String value) { this.documentNo = value; }
    public StockDocumentDocumentType getDocumentType() { return documentType; }
    public void setDocumentType(StockDocumentDocumentType value) { this.documentType = value; }
    public StockDocumentStatus getStatus() { return status; }
    public void setStatus(StockDocumentStatus value) { this.status = value; }
    public LocalDate getDocumentDate() { return documentDate; }
    public void setDocumentDate(LocalDate value) { this.documentDate = value; }
    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long value) { this.supplierId = value; }
    public Long getRecipientId() { return recipientId; }
    public void setRecipientId(Long value) { this.recipientId = value; }
    public String getRecipientNameSnapshot() { return recipientNameSnapshot; }
    public void setRecipientNameSnapshot(String value) { this.recipientNameSnapshot = value; }
    public String getRecipientDepartmentSnapshot() { return recipientDepartmentSnapshot; }
    public void setRecipientDepartmentSnapshot(String value) { this.recipientDepartmentSnapshot = value; }
    public String getReason() { return reason; }
    public void setReason(String value) { this.reason = value; }
    public Long getCreatedBy() { return createdBy; }
    public void setCreatedBy(Long value) { this.createdBy = value; }
    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDateTime value) { this.submittedAt = value; }
    public Long getApprovedBy() { return approvedBy; }
    public void setApprovedBy(Long value) { this.approvedBy = value; }
    public LocalDateTime getApprovedAt() { return approvedAt; }
    public void setApprovedAt(LocalDateTime value) { this.approvedAt = value; }
    public Long getPostedBy() { return postedBy; }
    public void setPostedBy(Long value) { this.postedBy = value; }
    public LocalDateTime getPostedAt() { return postedAt; }
    public void setPostedAt(LocalDateTime value) { this.postedAt = value; }
    public Long getRevision() { return revision; }
    public void setRevision(Long value) { this.revision = value; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime value) { this.createdAt = value; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime value) { this.updatedAt = value; }
}
