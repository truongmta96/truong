package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;
import jakarta.validation.constraints.*;

public record SupplierWriteDto(
    @NotBlank @Size(max = 60) String code,
    @NotBlank @Size(max = 255) String name,
    @Size(max = 30) String phone,
    @Size(max = 500) String address,
    @Size(max = 30) String taxCode,
    @NotNull Boolean isActive
) {}
