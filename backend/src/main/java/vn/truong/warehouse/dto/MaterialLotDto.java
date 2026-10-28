package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

public record MaterialLotDto(
    Long id,
    Long materialId,
    String lotCode,
    LocalDate manufacturedOn,
    LocalDate expiresOn
) {}
