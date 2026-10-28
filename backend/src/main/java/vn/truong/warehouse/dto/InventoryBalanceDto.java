package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

/**
 * Dữ liệu tồn kho trả về cho giao diện, tách khỏi đối tượng JPA để tránh lộ dữ liệu hoặc quan hệ ngoài ý muốn.
 * @param materialId Vật tư.
 * @param quantityOnHand Tồn hiện tại.
 * @param inventoryValue Giá trị tồn.
 * @param version Phiên bản.
 * @param updatedAt Cập nhật.
 */
public record InventoryBalanceDto(
    Long materialId,
    BigDecimal quantityOnHand,
    BigDecimal inventoryValue,
    Long version,
    LocalDateTime updatedAt
) {}
