package vn.truong.warehouse.entity;

import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "material_serial")
public class MaterialSerial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned", name = "id", nullable = false)
    private Long id;

    @Column(columnDefinition = "bigint unsigned", name = "material_id", nullable = false)
    private Long materialId;

    @Column(columnDefinition = "bigint unsigned", name = "lot_id")
    private Long lotId;

    @Column(name = "serial_number", nullable = false, length = 150)
    private String serialNumber;

    @Column(name = "expires_on")
    private LocalDate expiresOn;

    public MaterialSerial() {}
    public Long getId() { return id; }
    public void setId(Long value) { this.id = value; }
    public Long getMaterialId() { return materialId; }
    public void setMaterialId(Long value) { this.materialId = value; }
    public Long getLotId() { return lotId; }
    public void setLotId(Long value) { this.lotId = value; }
    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String value) { this.serialNumber = value; }
    public LocalDate getExpiresOn() { return expiresOn; }
    public void setExpiresOn(LocalDate value) { this.expiresOn = value; }
}
