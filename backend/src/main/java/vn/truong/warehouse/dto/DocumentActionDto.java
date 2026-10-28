package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

public record DocumentActionDto(
    Long id,
    Long documentId,
    Long revision,
    DocumentActionAction action,
    Long actorId,
    String comment,
    LocalDateTime actedAt
) {}
