package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

public record InventoryBalanceDto(
    Long materialId,
    BigDecimal quantityOnHand,
    BigDecimal inventoryValue,
    Long version,
    LocalDateTime updatedAt
) {}
