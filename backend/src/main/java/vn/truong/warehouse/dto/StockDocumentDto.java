package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

public record StockDocumentDto(
    Long id,
    String documentNo,
    StockDocumentDocumentType documentType,
    StockDocumentStatus status,
    LocalDate documentDate,
    Long supplierId,
    Long recipientId,
    String recipientNameSnapshot,
    String recipientDepartmentSnapshot,
    String reason,
    Long createdBy,
    LocalDateTime submittedAt,
    Long approvedBy,
    LocalDateTime approvedAt,
    Long postedBy,
    LocalDateTime postedAt,
    Long revision,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
