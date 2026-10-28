package vn.truong.warehouse.entity;

import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "app_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned", name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 150)
    private String username;

    @com.fasterxml.jackson.annotation.JsonIgnore
    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "full_name", nullable = false, length = 200)
    private String fullName;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public AppUser() {}
    public Long getId() { return id; }
    public void setId(Long value) { this.id = value; }
    public String getUsername() { return username; }
    public void setUsername(String value) { this.username = value; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String value) { this.passwordHash = value; }
    public String getFullName() { return fullName; }
    public void setFullName(String value) { this.fullName = value; }
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean value) { this.isActive = value; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime value) { this.createdAt = value; }
}
