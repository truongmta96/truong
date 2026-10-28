package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

public record StockMovementDto(
    Long id,
    Long allocationId,
    Long materialId,
    Integer direction,
    BigDecimal baseQuantity,
    BigDecimal unitCost,
    BigDecimal valueAmount,
    StockMovementCostingMethod costingMethod,
    LocalDateTime postedAt,
    Long postedBy
) {}
