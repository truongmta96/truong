package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

public record MaterialDto(
    Long id,
    String code,
    String name,
    Long categoryId,
    Long baseUnitId,
    Boolean trackLot,
    Boolean trackSerial,
    Boolean trackExpiry,
    Boolean allowFractionalQuantity,
    BigDecimal minStock,
    Boolean isActive,
    LocalDateTime createdAt
) {}
