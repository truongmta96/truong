package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;
import jakarta.validation.constraints.*;

public record UnitOfMeasureWriteDto(
    @NotBlank @Size(max = 30) String code,
    @NotBlank @Size(max = 100) String name
) {}
