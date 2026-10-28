package vn.truong.warehouse.entity;

import jakarta.persistence.*;
import java.time.*;

/**
 * Ánh xạ bảng material_serial trong MySQL thành đối tượng Java dùng cho serial vật tư. Khóa ngoại được giữ bằng ID; các ràng buộc liên kết được kiểm tra ở database.
 */
@Entity
@Table(name = "material_serial")
public class MaterialSerial {
    /**
     * Lưu id của bản ghi.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned", name = "id", nullable = false)
    private Long id;

    /**
     * Lưu vật tư của bản ghi.
     */
    @Column(columnDefinition = "bigint unsigned", name = "material_id", nullable = false)
    private Long materialId;

    /**
     * Lưu lô vật tư của bản ghi.
     */
    @Column(columnDefinition = "bigint unsigned", name = "lot_id")
    private Long lotId;

    /**
     * Lưu số serial của bản ghi.
     */
    @Column(name = "serial_number", nullable = false, length = 150)
    private String serialNumber;

    /**
     * Lưu hạn sử dụng của bản ghi.
     */
    @Column(name = "expires_on")
    private LocalDate expiresOn;

    /**
     * Hàm tạo rỗng phục vụ JPA khôi phục bản ghi.
     */
    public MaterialSerial() {}
    /**
     * Đọc giá trị id hiện có trong đối tượng Java.
     */
    public Long getId() { return id; }
    /**
     * Gán giá trị id trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setId(Long value) { this.id = value; }
    /**
     * Đọc giá trị vật tư hiện có trong đối tượng Java.
     */
    public Long getMaterialId() { return materialId; }
    /**
     * Gán giá trị vật tư trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setMaterialId(Long value) { this.materialId = value; }
    /**
     * Đọc giá trị lô vật tư hiện có trong đối tượng Java.
     */
    public Long getLotId() { return lotId; }
    /**
     * Gán giá trị lô vật tư trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setLotId(Long value) { this.lotId = value; }
    /**
     * Đọc giá trị số serial hiện có trong đối tượng Java.
     */
    public String getSerialNumber() { return serialNumber; }
    /**
     * Gán giá trị số serial trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setSerialNumber(String value) { this.serialNumber = value; }
    /**
     * Đọc giá trị hạn sử dụng hiện có trong đối tượng Java.
     */
    public LocalDate getExpiresOn() { return expiresOn; }
    /**
     * Gán giá trị hạn sử dụng trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setExpiresOn(LocalDate value) { this.expiresOn = value; }
}
