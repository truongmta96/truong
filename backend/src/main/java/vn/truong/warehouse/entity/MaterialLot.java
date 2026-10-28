package vn.truong.warehouse.entity;

import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "material_lot")
public class MaterialLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned", name = "id", nullable = false)
    private Long id;

    @Column(columnDefinition = "bigint unsigned", name = "material_id", nullable = false)
    private Long materialId;

    @Column(name = "lot_code", nullable = false, length = 100)
    private String lotCode;

    @Column(name = "manufactured_on")
    private LocalDate manufacturedOn;

    @Column(name = "expires_on")
    private LocalDate expiresOn;

    public MaterialLot() {}
    public Long getId() { return id; }
    public void setId(Long value) { this.id = value; }
    public Long getMaterialId() { return materialId; }
    public void setMaterialId(Long value) { this.materialId = value; }
    public String getLotCode() { return lotCode; }
    public void setLotCode(String value) { this.lotCode = value; }
    public LocalDate getManufacturedOn() { return manufacturedOn; }
    public void setManufacturedOn(LocalDate value) { this.manufacturedOn = value; }
    public LocalDate getExpiresOn() { return expiresOn; }
    public void setExpiresOn(LocalDate value) { this.expiresOn = value; }
}
