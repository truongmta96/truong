package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;
import jakarta.validation.constraints.*;

public record MaterialLotWriteDto(
    @NotNull Long materialId,
    @NotBlank @Size(max = 100) String lotCode,
    LocalDate manufacturedOn,
    LocalDate expiresOn
) {}
