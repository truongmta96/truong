package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

public record MaterialSerialDto(
    Long id,
    Long materialId,
    Long lotId,
    String serialNumber,
    LocalDate expiresOn
) {}
