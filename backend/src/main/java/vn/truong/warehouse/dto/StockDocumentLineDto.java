package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

/**
 * Dữ liệu chi tiết phiếu trả về cho giao diện, tách khỏi đối tượng JPA để tránh lộ dữ liệu hoặc quan hệ ngoài ý muốn.
 * @param id Id.
 * @param documentId Phiếu kho.
 * @param lineNo Dòng.
 * @param materialId Vật tư.
 * @param unitId Đơn vị tính.
 * @param quantity Số lượng.
 * @param conversionFactor Hệ số quy đổi.
 * @param baseQuantity Số lượng cơ sở.
 * @param inputUnitPrice Đơn giá nhập.
 * @param note Ghi chú.
 */
public record StockDocumentLineDto(
    Long id,
    Long documentId,
    Long lineNo,
    Long materialId,
    Long unitId,
    BigDecimal quantity,
    BigDecimal conversionFactor,
    BigDecimal baseQuantity,
    BigDecimal inputUnitPrice,
    String note
) {}
