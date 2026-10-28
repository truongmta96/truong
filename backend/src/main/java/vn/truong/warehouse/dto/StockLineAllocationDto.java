package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

public record StockLineAllocationDto(
    Long id,
    Long lineId,
    Long materialId,
    Long lotId,
    Long serialId,
    BigDecimal baseQuantity
) {}
