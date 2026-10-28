package vn.truong.warehouse.entity;

import jakarta.persistence.*;
import java.time.*;

/**
 * Ánh xạ bảng material_lot trong MySQL thành đối tượng Java dùng cho lô vật tư. Khóa ngoại được giữ bằng ID; các ràng buộc liên kết được kiểm tra ở database.
 */
@Entity
@Table(name = "material_lot")
public class MaterialLot {
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
     * Lưu mã lô của bản ghi.
     */
    @Column(name = "lot_code", nullable = false, length = 100)
    private String lotCode;

    /**
     * Lưu ngày sản xuất của bản ghi.
     */
    @Column(name = "manufactured_on")
    private LocalDate manufacturedOn;

    /**
     * Lưu hạn sử dụng của bản ghi.
     */
    @Column(name = "expires_on")
    private LocalDate expiresOn;

    /**
     * Hàm tạo rỗng phục vụ JPA khôi phục bản ghi.
     */
    public MaterialLot() {}
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
     * Đọc giá trị mã lô hiện có trong đối tượng Java.
     */
    public String getLotCode() { return lotCode; }
    /**
     * Gán giá trị mã lô trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setLotCode(String value) { this.lotCode = value; }
    /**
     * Đọc giá trị ngày sản xuất hiện có trong đối tượng Java.
     */
    public LocalDate getManufacturedOn() { return manufacturedOn; }
    /**
     * Gán giá trị ngày sản xuất trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setManufacturedOn(LocalDate value) { this.manufacturedOn = value; }
    /**
     * Đọc giá trị hạn sử dụng hiện có trong đối tượng Java.
     */
    public LocalDate getExpiresOn() { return expiresOn; }
    /**
     * Gán giá trị hạn sử dụng trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setExpiresOn(LocalDate value) { this.expiresOn = value; }
}
