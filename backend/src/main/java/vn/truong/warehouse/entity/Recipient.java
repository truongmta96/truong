package vn.truong.warehouse.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "recipient")
public class Recipient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned", name = "id", nullable = false)
    private Long id;

    @Column(name = "code", nullable = false, length = 60)
    private String code;

    @Column(name = "full_name", nullable = false, length = 200)
    private String fullName;

    @Column(name = "department", length = 200)
    private String department;

    @Column(name = "phone", length = 30)
    private String phone;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    public Recipient() {}
    public Long getId() { return id; }
    public void setId(Long value) { this.id = value; }
    public String getCode() { return code; }
    public void setCode(String value) { this.code = value; }
    public String getFullName() { return fullName; }
    public void setFullName(String value) { this.fullName = value; }
    public String getDepartment() { return department; }
    public void setDepartment(String value) { this.department = value; }
    public String getPhone() { return phone; }
    public void setPhone(String value) { this.phone = value; }
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean value) { this.isActive = value; }
}
