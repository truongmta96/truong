package vn.truong.warehouse.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned", name = "id", nullable = false)
    private Long id;

    @Column(name = "code", nullable = false, length = 60)
    private String code;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "phone", length = 30)
    private String phone;

    @Column(name = "address", length = 500)
    private String address;

    @Column(name = "tax_code", length = 30)
    private String taxCode;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    public Supplier() {}
    public Long getId() { return id; }
    public void setId(Long value) { this.id = value; }
    public String getCode() { return code; }
    public void setCode(String value) { this.code = value; }
    public String getName() { return name; }
    public void setName(String value) { this.name = value; }
    public String getPhone() { return phone; }
    public void setPhone(String value) { this.phone = value; }
    public String getAddress() { return address; }
    public void setAddress(String value) { this.address = value; }
    public String getTaxCode() { return taxCode; }
    public void setTaxCode(String value) { this.taxCode = value; }
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean value) { this.isActive = value; }
}
