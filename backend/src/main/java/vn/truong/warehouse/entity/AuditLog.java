package vn.truong.warehouse.entity;

import jakarta.persistence.*;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

/**
 * Ánh xạ bảng audit_log trong MySQL thành đối tượng Java dùng cho nhật ký hệ thống. Khóa ngoại được giữ bằng ID; các ràng buộc liên kết được kiểm tra ở database.
 * Không ghi mật khẩu hoặc token vào JSON trước/sau thay đổi; API hiện chỉ trả metadata của nhật ký.
 */
@Entity
@Table(name = "audit_log")
public class AuditLog {
    /**
     * Lưu id của bản ghi.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned", name = "id", nullable = false)
    private Long id;

    /**
     * Lưu người thực hiện của bản ghi.
     */
    @Column(columnDefinition = "bigint unsigned", name = "actor_id")
    private Long actorId;

    /**
     * Lưu đối tượng của bản ghi.
     */
    @Column(name = "entity_type", nullable = false, length = 80)
    private String entityType;

    /**
     * Lưu id đối tượng của bản ghi.
     */
    @Column(columnDefinition = "bigint unsigned", name = "entity_id", nullable = false)
    private Long entityId;

    /**
     * Lưu hành động của bản ghi.
     */
    @Column(name = "action", nullable = false, length = 40)
    private String action;

    /**
     * Lưu dữ liệu JSON trước thay đổi của bản ghi.
     */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "old_data", columnDefinition = "json")
    private JsonNode oldData;

    /**
     * Lưu dữ liệu JSON sau thay đổi của bản ghi.
     */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "new_data", columnDefinition = "json")
    private JsonNode newData;

    /**
     * Lưu ngày tạo của bản ghi.
     */
    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Hàm tạo rỗng phục vụ JPA khôi phục bản ghi.
     */
    public AuditLog() {}
    /**
     * Đọc giá trị id hiện có trong đối tượng Java.
     */
    public Long getId() { return id; }
    /**
     * Gán giá trị id trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setId(Long value) { this.id = value; }
    /**
     * Đọc giá trị người thực hiện hiện có trong đối tượng Java.
     */
    public Long getActorId() { return actorId; }
    /**
     * Gán giá trị người thực hiện trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setActorId(Long value) { this.actorId = value; }
    /**
     * Đọc giá trị đối tượng hiện có trong đối tượng Java.
     */
    public String getEntityType() { return entityType; }
    /**
     * Gán giá trị đối tượng trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setEntityType(String value) { this.entityType = value; }
    /**
     * Đọc giá trị id đối tượng hiện có trong đối tượng Java.
     */
    public Long getEntityId() { return entityId; }
    /**
     * Gán giá trị id đối tượng trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setEntityId(Long value) { this.entityId = value; }
    /**
     * Đọc giá trị hành động hiện có trong đối tượng Java.
     */
    public String getAction() { return action; }
    /**
     * Gán giá trị hành động trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setAction(String value) { this.action = value; }
    /**
     * Đọc giá trị dữ liệu JSON trước thay đổi hiện có trong đối tượng Java.
     */
    public JsonNode getOldData() { return oldData; }
    /**
     * Gán giá trị dữ liệu JSON trước thay đổi trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setOldData(JsonNode value) { this.oldData = value; }
    /**
     * Đọc giá trị dữ liệu JSON sau thay đổi hiện có trong đối tượng Java.
     */
    public JsonNode getNewData() { return newData; }
    /**
     * Gán giá trị dữ liệu JSON sau thay đổi trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setNewData(JsonNode value) { this.newData = value; }
    /**
     * Đọc giá trị ngày tạo hiện có trong đối tượng Java.
     */
    public LocalDateTime getCreatedAt() { return createdAt; }
    /**
     * Gán giá trị ngày tạo trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setCreatedAt(LocalDateTime value) { this.createdAt = value; }
}
