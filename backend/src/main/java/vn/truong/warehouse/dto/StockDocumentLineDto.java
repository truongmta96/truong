package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

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
