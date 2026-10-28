package vn.truong.warehouse.entity;

import jakarta.persistence.*;
import java.time.*;
import vn.truong.warehouse.enums.*;

/**
 * Ánh xạ bảng stock_document trong MySQL thành đối tượng Java dùng cho phiếu kho. Khóa ngoại được giữ bằng ID; các ràng buộc liên kết được kiểm tra ở database.
 */
@Entity
@Table(name = "stock_document")
public class StockDocument {
    /**
     * Lưu id của bản ghi.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned", name = "id", nullable = false)
    private Long id;

    /**
     * Lưu số phiếu của bản ghi.
     */
    @Column(name = "document_no", nullable = false, length = 60)
    private String documentNo;

    /**
     * Lưu loại phiếu của bản ghi.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", nullable = false)
    private StockDocumentDocumentType documentType;

    /**
     * Lưu trạng thái của bản ghi.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StockDocumentStatus status;

    /**
     * Lưu ngày phiếu của bản ghi.
     */
    @Column(name = "document_date", nullable = false)
    private LocalDate documentDate;

    /**
     * Lưu nhà cung cấp của bản ghi.
     */
    @Column(columnDefinition = "bigint unsigned", name = "supplier_id")
    private Long supplierId;

    /**
     * Lưu người nhận của bản ghi.
     */
    @Column(columnDefinition = "bigint unsigned", name = "recipient_id")
    private Long recipientId;

    /**
     * Lưu tên người nhận trên phiếu của bản ghi.
     */
    @Column(name = "recipient_name_snapshot", length = 200)
    private String recipientNameSnapshot;

    /**
     * Lưu bộ phận trên phiếu của bản ghi.
     */
    @Column(name = "recipient_department_snapshot", length = 200)
    private String recipientDepartmentSnapshot;

    /**
     * Lưu lý do của bản ghi.
     */
    @Column(name = "reason", length = 1000)
    private String reason;

    /**
     * Lưu người tạo của bản ghi.
     */
    @Column(columnDefinition = "bigint unsigned", name = "created_by", nullable = false)
    private Long createdBy;

    /**
     * Lưu ngày gửi duyệt của bản ghi.
     */
    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    /**
     * Lưu người duyệt của bản ghi.
     */
    @Column(columnDefinition = "bigint unsigned", name = "approved_by")
    private Long approvedBy;

    /**
     * Lưu ngày duyệt của bản ghi.
     */
    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    /**
     * Lưu người ghi sổ của bản ghi.
     */
    @Column(columnDefinition = "bigint unsigned", name = "posted_by")
    private Long postedBy;

    /**
     * Lưu ngày ghi sổ của bản ghi.
     */
    @Column(name = "posted_at")
    private LocalDateTime postedAt;

    /**
     * Lưu phiên bản phiếu của bản ghi.
     */
    @Column(columnDefinition = "int unsigned", name = "revision", nullable = false)
    private Long revision;

    /**
     * Lưu ngày tạo của bản ghi.
     */
    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Lưu cập nhật của bản ghi.
     */
    @Column(name = "updated_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    /**
     * Hàm tạo rỗng phục vụ JPA khôi phục bản ghi.
     */
    public StockDocument() {}
    /**
     * Đọc giá trị id hiện có trong đối tượng Java.
     */
    public Long getId() { return id; }
    /**
     * Gán giá trị id trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setId(Long value) { this.id = value; }
    /**
     * Đọc giá trị số phiếu hiện có trong đối tượng Java.
     */
    public String getDocumentNo() { return documentNo; }
    /**
     * Gán giá trị số phiếu trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setDocumentNo(String value) { this.documentNo = value; }
    /**
     * Đọc giá trị loại phiếu hiện có trong đối tượng Java.
     */
    public StockDocumentDocumentType getDocumentType() { return documentType; }
    /**
     * Gán giá trị loại phiếu trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setDocumentType(StockDocumentDocumentType value) { this.documentType = value; }
    /**
     * Đọc giá trị trạng thái hiện có trong đối tượng Java.
     */
    public StockDocumentStatus getStatus() { return status; }
    /**
     * Gán giá trị trạng thái trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setStatus(StockDocumentStatus value) { this.status = value; }
    /**
     * Đọc giá trị ngày phiếu hiện có trong đối tượng Java.
     */
    public LocalDate getDocumentDate() { return documentDate; }
    /**
     * Gán giá trị ngày phiếu trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setDocumentDate(LocalDate value) { this.documentDate = value; }
    /**
     * Đọc giá trị nhà cung cấp hiện có trong đối tượng Java.
     */
    public Long getSupplierId() { return supplierId; }
    /**
     * Gán giá trị nhà cung cấp trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setSupplierId(Long value) { this.supplierId = value; }
    /**
     * Đọc giá trị người nhận hiện có trong đối tượng Java.
     */
    public Long getRecipientId() { return recipientId; }
    /**
     * Gán giá trị người nhận trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setRecipientId(Long value) { this.recipientId = value; }
    /**
     * Đọc giá trị tên người nhận trên phiếu hiện có trong đối tượng Java.
     */
    public String getRecipientNameSnapshot() { return recipientNameSnapshot; }
    /**
     * Gán giá trị tên người nhận trên phiếu trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setRecipientNameSnapshot(String value) { this.recipientNameSnapshot = value; }
    /**
     * Đọc giá trị bộ phận trên phiếu hiện có trong đối tượng Java.
     */
    public String getRecipientDepartmentSnapshot() { return recipientDepartmentSnapshot; }
    /**
     * Gán giá trị bộ phận trên phiếu trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setRecipientDepartmentSnapshot(String value) { this.recipientDepartmentSnapshot = value; }
    /**
     * Đọc giá trị lý do hiện có trong đối tượng Java.
     */
    public String getReason() { return reason; }
    /**
     * Gán giá trị lý do trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setReason(String value) { this.reason = value; }
    /**
     * Đọc giá trị người tạo hiện có trong đối tượng Java.
     */
    public Long getCreatedBy() { return createdBy; }
    /**
     * Gán giá trị người tạo trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setCreatedBy(Long value) { this.createdBy = value; }
    /**
     * Đọc giá trị ngày gửi duyệt hiện có trong đối tượng Java.
     */
    public LocalDateTime getSubmittedAt() { return submittedAt; }
    /**
     * Gán giá trị ngày gửi duyệt trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setSubmittedAt(LocalDateTime value) { this.submittedAt = value; }
    /**
     * Đọc giá trị người duyệt hiện có trong đối tượng Java.
     */
    public Long getApprovedBy() { return approvedBy; }
    /**
     * Gán giá trị người duyệt trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setApprovedBy(Long value) { this.approvedBy = value; }
    /**
     * Đọc giá trị ngày duyệt hiện có trong đối tượng Java.
     */
    public LocalDateTime getApprovedAt() { return approvedAt; }
    /**
     * Gán giá trị ngày duyệt trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setApprovedAt(LocalDateTime value) { this.approvedAt = value; }
    /**
     * Đọc giá trị người ghi sổ hiện có trong đối tượng Java.
     */
    public Long getPostedBy() { return postedBy; }
    /**
     * Gán giá trị người ghi sổ trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setPostedBy(Long value) { this.postedBy = value; }
    /**
     * Đọc giá trị ngày ghi sổ hiện có trong đối tượng Java.
     */
    public LocalDateTime getPostedAt() { return postedAt; }
    /**
     * Gán giá trị ngày ghi sổ trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setPostedAt(LocalDateTime value) { this.postedAt = value; }
    /**
     * Đọc giá trị phiên bản phiếu hiện có trong đối tượng Java.
     */
    public Long getRevision() { return revision; }
    /**
     * Gán giá trị phiên bản phiếu trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setRevision(Long value) { this.revision = value; }
    /**
     * Đọc giá trị ngày tạo hiện có trong đối tượng Java.
     */
    public LocalDateTime getCreatedAt() { return createdAt; }
    /**
     * Gán giá trị ngày tạo trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setCreatedAt(LocalDateTime value) { this.createdAt = value; }
    /**
     * Đọc giá trị cập nhật hiện có trong đối tượng Java.
     */
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    /**
     * Gán giá trị cập nhật trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setUpdatedAt(LocalDateTime value) { this.updatedAt = value; }
}
