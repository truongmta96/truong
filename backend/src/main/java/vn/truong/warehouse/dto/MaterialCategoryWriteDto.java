package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;
import jakarta.validation.constraints.*;

public record MaterialCategoryWriteDto(
    @NotBlank @Size(max = 40) String code,
    @NotBlank @Size(max = 150) String name
) {}
