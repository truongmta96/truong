package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;
import jakarta.validation.constraints.*;

public record RecipientWriteDto(
    @NotNull @Size(max = 60) String code,
    @NotNull @Size(max = 200) String fullName,
    @Size(max = 200) String department,
    @Size(max = 30) String phone,
    @NotNull Boolean isActive
) {}
