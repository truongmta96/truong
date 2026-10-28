package vn.truong.warehouse.entity;

import jakarta.persistence.*;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "audit_log")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned", name = "id", nullable = false)
    private Long id;

    @Column(columnDefinition = "bigint unsigned", name = "actor_id")
    private Long actorId;

    @Column(name = "entity_type", nullable = false, length = 80)
    private String entityType;

    @Column(columnDefinition = "bigint unsigned", name = "entity_id", nullable = false)
    private Long entityId;

    @Column(name = "action", nullable = false, length = 40)
    private String action;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "old_data", columnDefinition = "json")
    private JsonNode oldData;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "new_data", columnDefinition = "json")
    private JsonNode newData;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public AuditLog() {}
    public Long getId() { return id; }
    public void setId(Long value) { this.id = value; }
    public Long getActorId() { return actorId; }
    public void setActorId(Long value) { this.actorId = value; }
    public String getEntityType() { return entityType; }
    public void setEntityType(String value) { this.entityType = value; }
    public Long getEntityId() { return entityId; }
    public void setEntityId(Long value) { this.entityId = value; }
    public String getAction() { return action; }
    public void setAction(String value) { this.action = value; }
    public JsonNode getOldData() { return oldData; }
    public void setOldData(JsonNode value) { this.oldData = value; }
    public JsonNode getNewData() { return newData; }
    public void setNewData(JsonNode value) { this.newData = value; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime value) { this.createdAt = value; }
}
