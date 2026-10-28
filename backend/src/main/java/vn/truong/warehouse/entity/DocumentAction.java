package vn.truong.warehouse.entity;

import jakarta.persistence.*;
import java.time.*;
import vn.truong.warehouse.enums.*;

@Entity
@Table(name = "document_action")
public class DocumentAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned", name = "id", nullable = false)
    private Long id;

    @Column(columnDefinition = "bigint unsigned", name = "document_id", nullable = false)
    private Long documentId;

    @Column(columnDefinition = "int unsigned", name = "revision", nullable = false)
    private Long revision;

    @Enumerated(EnumType.STRING)
    @Column(name = "action", nullable = false)
    private DocumentActionAction action;

    @Column(columnDefinition = "bigint unsigned", name = "actor_id", nullable = false)
    private Long actorId;

    @Column(name = "comment", length = 1000)
    private String comment;

    @Column(name = "acted_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime actedAt;

    public DocumentAction() {}
    public Long getId() { return id; }
    public void setId(Long value) { this.id = value; }
    public Long getDocumentId() { return documentId; }
    public void setDocumentId(Long value) { this.documentId = value; }
    public Long getRevision() { return revision; }
    public void setRevision(Long value) { this.revision = value; }
    public DocumentActionAction getAction() { return action; }
    public void setAction(DocumentActionAction value) { this.action = value; }
    public Long getActorId() { return actorId; }
    public void setActorId(Long value) { this.actorId = value; }
    public String getComment() { return comment; }
    public void setComment(String value) { this.comment = value; }
    public LocalDateTime getActedAt() { return actedAt; }
    public void setActedAt(LocalDateTime value) { this.actedAt = value; }
}
