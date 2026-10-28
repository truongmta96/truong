package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

public record MaterialUnitDto(
    Long materialId,
    Long unitId,
    BigDecimal conversionFactor,
    Boolean allowReceipt,
    Boolean allowIssue
) {}
