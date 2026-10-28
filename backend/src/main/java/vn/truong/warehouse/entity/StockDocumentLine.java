package vn.truong.warehouse.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Ánh xạ bảng stock_document_line trong MySQL thành đối tượng Java dùng cho chi tiết phiếu. Khóa ngoại được giữ bằng ID; các ràng buộc liên kết được kiểm tra ở database. Số lượng cơ sở do MySQL tự tính từ số lượng và hệ số, ứng dụng không ghi trực tiếp cột này.
 */
@Entity
@Table(name = "stock_document_line")
public class StockDocumentLine {
    /**
     * Lưu id của bản ghi.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned", name = "id", nullable = false)
    private Long id;

    /**
     * Lưu phiếu kho của bản ghi.
     */
    @Column(columnDefinition = "bigint unsigned", name = "document_id", nullable = false)
    private Long documentId;

    /**
     * Lưu dòng của bản ghi.
     */
    @Column(columnDefinition = "int unsigned", name = "line_no", nullable = false)
    private Long lineNo;

    /**
     * Lưu vật tư của bản ghi.
     */
    @Column(columnDefinition = "bigint unsigned", name = "material_id", nullable = false)
    private Long materialId;

    /**
     * Lưu đơn vị tính của bản ghi.
     */
    @Column(columnDefinition = "bigint unsigned", name = "unit_id", nullable = false)
    private Long unitId;

    /**
     * Lưu số lượng của bản ghi.
     */
    @Column(name = "quantity", nullable = false, precision = 20, scale = 6)
    private BigDecimal quantity;

    /**
     * Lưu hệ số quy đổi của bản ghi.
     */
    @Column(name = "conversion_factor", nullable = false, precision = 20, scale = 6)
    private BigDecimal conversionFactor;

    /**
     * Lưu số lượng cơ sở của bản ghi.
     */
    @Column(name = "base_quantity", precision = 30, scale = 6, insertable = false, updatable = false)
    private BigDecimal baseQuantity;

    /**
     * Lưu đơn giá nhập của bản ghi.
     */
    @Column(name = "input_unit_price", precision = 24, scale = 6)
    private BigDecimal inputUnitPrice;

    /**
     * Lưu ghi chú của bản ghi.
     */
    @Column(name = "note", length = 500)
    private String note;

    /**
     * Hàm tạo rỗng phục vụ JPA khôi phục bản ghi.
     */
    public StockDocumentLine() {}
    /**
     * Đọc giá trị id hiện có trong đối tượng Java.
     */
    public Long getId() { return id; }
    /**
     * Gán giá trị id trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setId(Long value) { this.id = value; }
    /**
     * Đọc giá trị phiếu kho hiện có trong đối tượng Java.
     */
    public Long getDocumentId() { return documentId; }
    /**
     * Gán giá trị phiếu kho trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setDocumentId(Long value) { this.documentId = value; }
    /**
     * Đọc giá trị dòng hiện có trong đối tượng Java.
     */
    public Long getLineNo() { return lineNo; }
    /**
     * Gán giá trị dòng trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setLineNo(Long value) { this.lineNo = value; }
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
     * Đọc giá trị số lượng hiện có trong đối tượng Java.
     */
    public BigDecimal getQuantity() { return quantity; }
    /**
     * Gán giá trị số lượng trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setQuantity(BigDecimal value) { this.quantity = value; }
    /**
     * Đọc giá trị hệ số quy đổi hiện có trong đối tượng Java.
     */
    public BigDecimal getConversionFactor() { return conversionFactor; }
    /**
     * Gán giá trị hệ số quy đổi trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setConversionFactor(BigDecimal value) { this.conversionFactor = value; }
    /**
     * Đọc giá trị số lượng cơ sở hiện có trong đối tượng Java. Cột tương ứng trong database do MySQL tự tính và chỉ đọc khi lưu entity.
     */
    public BigDecimal getBaseQuantity() { return baseQuantity; }
    /**
     * Gán giá trị số lượng cơ sở trong đối tượng Java; bản thân hàm này không ghi database. Cột tương ứng trong database do MySQL tự tính và chỉ đọc khi lưu entity.
     */
    public void setBaseQuantity(BigDecimal value) { this.baseQuantity = value; }
    /**
     * Đọc giá trị đơn giá nhập hiện có trong đối tượng Java.
     */
    public BigDecimal getInputUnitPrice() { return inputUnitPrice; }
    /**
     * Gán giá trị đơn giá nhập trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setInputUnitPrice(BigDecimal value) { this.inputUnitPrice = value; }
    /**
     * Đọc giá trị ghi chú hiện có trong đối tượng Java.
     */
    public String getNote() { return note; }
    /**
     * Gán giá trị ghi chú trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setNote(String value) { this.note = value; }
}
