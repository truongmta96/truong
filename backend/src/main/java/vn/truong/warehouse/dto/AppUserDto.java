package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

public record AppUserDto(
    Long id,
    String username,
    String fullName,
    Boolean isActive,
    LocalDateTime createdAt
) {}
