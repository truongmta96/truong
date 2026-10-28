package vn.truong.warehouse.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "material_category")
public class MaterialCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned", name = "id", nullable = false)
    private Long id;

    @Column(name = "code", nullable = false, length = 40)
    private String code;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    public MaterialCategory() {}
    public Long getId() { return id; }
    public void setId(Long value) { this.id = value; }
    public String getCode() { return code; }
    public void setCode(String value) { this.code = value; }
    public String getName() { return name; }
    public void setName(String value) { this.name = value; }
}
