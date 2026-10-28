package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;
import jakarta.validation.constraints.*;

public record MaterialWriteDto(
    @NotBlank @Size(max = 60) String code,
    @NotBlank @Size(max = 255) String name,
    Long categoryId,
    @NotNull Long baseUnitId,
    @NotNull Boolean trackLot,
    @NotNull Boolean trackSerial,
    @NotNull Boolean trackExpiry,
    @NotNull Boolean allowFractionalQuantity,
    @NotNull @Digits(integer = 14, fraction = 6) @DecimalMin("0") BigDecimal minStock,
    @NotNull Boolean isActive
) {}
