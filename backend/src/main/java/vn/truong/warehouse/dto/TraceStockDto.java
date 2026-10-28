package vn.truong.warehouse.dto;
import java.math.BigDecimal;
/**
 * Dữ liệu tồn theo vật tư, lô và serial trả về cho giao diện, tách khỏi đối tượng JPA để tránh lộ dữ liệu hoặc quan hệ ngoài ý muốn.
 * @param materialId Vật tư.
 * @param lotId Lô vật tư.
 * @param serialId Serial.
 * @param quantityOnHand Tồn hiện tại.
 */
public record TraceStockDto(Long materialId, Long lotId, Long serialId, BigDecimal quantityOnHand) {}
