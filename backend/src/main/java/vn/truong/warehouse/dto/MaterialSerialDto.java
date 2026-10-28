package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

/**
 * Dữ liệu serial vật tư trả về cho giao diện, tách khỏi đối tượng JPA để tránh lộ dữ liệu hoặc quan hệ ngoài ý muốn.
 * @param id Id.
 * @param materialId Vật tư.
 * @param lotId Lô vật tư.
 * @param serialNumber Số serial.
 * @param expiresOn Hạn sử dụng.
 */
public record MaterialSerialDto(
    Long id,
    Long materialId,
    Long lotId,
    String serialNumber,
    LocalDate expiresOn
) {}
