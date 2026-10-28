package vn.truong.warehouse.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "stock_document_line")
public class StockDocumentLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned", name = "id", nullable = false)
    private Long id;

    @Column(columnDefinition = "bigint unsigned", name = "document_id", nullable = false)
    private Long documentId;

    @Column(columnDefinition = "int unsigned", name = "line_no", nullable = false)
    private Long lineNo;

    @Column(columnDefinition = "bigint unsigned", name = "material_id", nullable = false)
    private Long materialId;

    @Column(columnDefinition = "bigint unsigned", name = "unit_id", nullable = false)
    private Long unitId;

    @Column(name = "quantity", nullable = false, precision = 20, scale = 6)
    private BigDecimal quantity;

    @Column(name = "conversion_factor", nullable = false, precision = 20, scale = 6)
    private BigDecimal conversionFactor;

    @Column(name = "base_quantity", precision = 30, scale = 6, insertable = false, updatable = false)
    private BigDecimal baseQuantity;

    @Column(name = "input_unit_price", precision = 24, scale = 6)
    private BigDecimal inputUnitPrice;

    @Column(name = "note", length = 500)
    private String note;

    public StockDocumentLine() {}
    public Long getId() { return id; }
    public void setId(Long value) { this.id = value; }
    public Long getDocumentId() { return documentId; }
    public void setDocumentId(Long value) { this.documentId = value; }
    public Long getLineNo() { return lineNo; }
    public void setLineNo(Long value) { this.lineNo = value; }
    public Long getMaterialId() { return materialId; }
    public void setMaterialId(Long value) { this.materialId = value; }
    public Long getUnitId() { return unitId; }
    public void setUnitId(Long value) { this.unitId = value; }
    public BigDecimal getQuantity() { return quantity; }
    public void setQuantity(BigDecimal value) { this.quantity = value; }
    public BigDecimal getConversionFactor() { return conversionFactor; }
    public void setConversionFactor(BigDecimal value) { this.conversionFactor = value; }
    public BigDecimal getBaseQuantity() { return baseQuantity; }
    public void setBaseQuantity(BigDecimal value) { this.baseQuantity = value; }
    public BigDecimal getInputUnitPrice() { return inputUnitPrice; }
    public void setInputUnitPrice(BigDecimal value) { this.inputUnitPrice = value; }
    public String getNote() { return note; }
    public void setNote(String value) { this.note = value; }
}
