package vn.truong.warehouse.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Ánh xạ bảng material_unit trong MySQL thành đối tượng Java dùng cho quy đổi đơn vị. Khóa ngoại được giữ bằng ID; các ràng buộc liên kết được kiểm tra ở database.
 */
@Entity
@Table(name = "material_unit")
@IdClass(MaterialUnitId.class)
public class MaterialUnit {
    /**
     * Lưu vật tư của bản ghi.
     */
    @Id
    @Column(columnDefinition = "bigint unsigned", name = "material_id", nullable = false)
    private Long materialId;

    /**
     * Lưu đơn vị tính của bản ghi.
     */
    @Id
    @Column(columnDefinition = "bigint unsigned", name = "unit_id", nullable = false)
    private Long unitId;

    /**
     * Lưu hệ số quy đổi của bản ghi.
     */
    @Column(name = "conversion_factor", nullable = false, precision = 20, scale = 6)
    private BigDecimal conversionFactor;

    /**
     * Lưu cho phép nhập của bản ghi.
     */
    @Column(name = "allow_receipt", nullable = false)
    private Boolean allowReceipt;

    /**
     * Lưu cho phép xuất của bản ghi.
     */
    @Column(name = "allow_issue", nullable = false)
    private Boolean allowIssue;

    /**
     * Hàm tạo rỗng phục vụ JPA khôi phục bản ghi.
     */
    public MaterialUnit() {}
    /**
     * Đọc giá trị vật tư hiện có trong đối tượng Java.
     */
    public Long getMaterialId() { return materialId; }
    /**
     * Gán giá trị vật tư trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setMaterialId(Long value) { this.materialId = value; }
    /**
     * Đọc giá trị đơn vị tính hiện có trong đối tượng Java.
     */
    public Long getUnitId() { return unitId; }
    /**
     * Gán giá trị đơn vị tính trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setUnitId(Long value) { this.unitId = value; }
    /**
     * Đọc giá trị hệ số quy đổi hiện có trong đối tượng Java.
     */
    public BigDecimal getConversionFactor() { return conversionFactor; }
    /**
     * Gán giá trị hệ số quy đổi trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setConversionFactor(BigDecimal value) { this.conversionFactor = value; }
    /**
     * Đọc giá trị cho phép nhập hiện có trong đối tượng Java.
     */
    public Boolean getAllowReceipt() { return allowReceipt; }
    /**
     * Gán giá trị cho phép nhập trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setAllowReceipt(Boolean value) { this.allowReceipt = value; }
    /**
     * Đọc giá trị cho phép xuất hiện có trong đối tượng Java.
     */
    public Boolean getAllowIssue() { return allowIssue; }
    /**
     * Gán giá trị cho phép xuất trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setAllowIssue(Boolean value) { this.allowIssue = value; }
}
