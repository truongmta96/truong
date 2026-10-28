package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

public record AuditLogDto(
    Long id,
    Long actorId,
    String entityType,
    Long entityId,
    String action,
    JsonNode oldData,
    JsonNode newData,
    LocalDateTime createdAt
) {}
