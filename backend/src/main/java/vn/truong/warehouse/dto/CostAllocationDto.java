package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

public record CostAllocationDto(
    Long id,
    Long issueMovementId,
    Long receiptMovementId,
    BigDecimal baseQuantity,
    BigDecimal valueAmount
) {}
