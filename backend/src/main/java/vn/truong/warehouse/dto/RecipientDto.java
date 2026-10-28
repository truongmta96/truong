package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

public record RecipientDto(
    Long id,
    String code,
    String fullName,
    String department,
    String phone,
    Boolean isActive
) {}
