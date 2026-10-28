package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

/**
 * Dữ liệu phân bổ lô / serial trả về cho giao diện, tách khỏi đối tượng JPA để tránh lộ dữ liệu hoặc quan hệ ngoài ý muốn.
 * @param id Id.
 * @param lineId Dòng phiếu.
 * @param materialId Vật tư.
 * @param lotId Lô vật tư.
 * @param serialId Serial.
 * @param baseQuantity Số lượng cơ sở.
 */
public record StockLineAllocationDto(
    Long id,
    Long lineId,
    Long materialId,
    Long lotId,
    Long serialId,
    BigDecimal baseQuantity
) {}
