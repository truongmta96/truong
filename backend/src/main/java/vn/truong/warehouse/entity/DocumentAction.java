package vn.truong.warehouse.entity;

import jakarta.persistence.*;
import java.time.*;
import vn.truong.warehouse.enums.*;

/**
 * Ánh xạ bảng document_action trong MySQL thành đối tượng Java dùng cho lịch sử phiếu. Khóa ngoại được giữ bằng ID; các ràng buộc liên kết được kiểm tra ở database.
 */
@Entity
@Table(name = "document_action")
public class DocumentAction {
    /**
     * Lưu id của bản ghi.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned", name = "id", nullable = false)
    private Long id;

    /**
     * Lưu phiếu kho của bản ghi.
     */
    @Column(columnDefinition = "bigint unsigned", name = "document_id", nullable = false)
    private Long documentId;

    /**
     * Lưu phiên bản phiếu của bản ghi.
     */
    @Column(columnDefinition = "int unsigned", name = "revision", nullable = false)
    private Long revision;

    /**
     * Lưu hành động của bản ghi.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "action", nullable = false)
    private DocumentActionAction action;

    /**
     * Lưu người thực hiện của bản ghi.
     */
    @Column(columnDefinition = "bigint unsigned", name = "actor_id", nullable = false)
    private Long actorId;

    /**
     * Lưu nhận xét của bản ghi.
     */
    @Column(name = "comment", length = 1000)
    private String comment;

    /**
     * Lưu thời điểm của bản ghi.
     */
    @Column(name = "acted_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime actedAt;

    /**
     * Hàm tạo rỗng phục vụ JPA khôi phục bản ghi.
     */
    public DocumentAction() {}
    /**
     * Đọc giá trị id hiện có trong đối tượng Java.
     */
    public Long getId() { return id; }
    /**
     * Gán giá trị id trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setId(Long value) { this.id = value; }
    /**
     * Đọc giá trị phiếu kho hiện có trong đối tượng Java.
     */
    public Long getDocumentId() { return documentId; }
    /**
     * Gán giá trị phiếu kho trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setDocumentId(Long value) { this.documentId = value; }
    /**
     * Đọc giá trị phiên bản phiếu hiện có trong đối tượng Java.
     */
    public Long getRevision() { return revision; }
    /**
     * Gán giá trị phiên bản phiếu trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setRevision(Long value) { this.revision = value; }
    /**
     * Đọc giá trị hành động hiện có trong đối tượng Java.
     */
    public DocumentActionAction getAction() { return action; }
    /**
     * Gán giá trị hành động trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setAction(DocumentActionAction value) { this.action = value; }
    /**
     * Đọc giá trị người thực hiện hiện có trong đối tượng Java.
     */
    public Long getActorId() { return actorId; }
    /**
     * Gán giá trị người thực hiện trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setActorId(Long value) { this.actorId = value; }
    /**
     * Đọc giá trị nhận xét hiện có trong đối tượng Java.
     */
    public String getComment() { return comment; }
    /**
     * Gán giá trị nhận xét trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setComment(String value) { this.comment = value; }
    /**
     * Đọc giá trị thời điểm hiện có trong đối tượng Java.
     */
    public LocalDateTime getActedAt() { return actedAt; }
    /**
     * Gán giá trị thời điểm trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setActedAt(LocalDateTime value) { this.actedAt = value; }
}
