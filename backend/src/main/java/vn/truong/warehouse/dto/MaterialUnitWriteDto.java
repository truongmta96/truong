package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;
import jakarta.validation.constraints.*;

public record MaterialUnitWriteDto(
    @NotNull Long materialId,
    @NotNull Long unitId,
    @NotNull @Digits(integer = 14, fraction = 6) @DecimalMin("0.000001") BigDecimal conversionFactor,
    @NotNull Boolean allowReceipt,
    @NotNull Boolean allowIssue
) {}
