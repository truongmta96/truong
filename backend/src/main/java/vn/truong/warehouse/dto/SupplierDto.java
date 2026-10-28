package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

public record SupplierDto(
    Long id,
    String code,
    String name,
    String phone,
    String address,
    String taxCode,
    Boolean isActive
) {}
