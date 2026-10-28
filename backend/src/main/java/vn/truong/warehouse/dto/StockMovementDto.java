package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

/**
 * Dữ liệu sổ biến động trả về cho giao diện, tách khỏi đối tượng JPA để tránh lộ dữ liệu hoặc quan hệ ngoài ý muốn.
 * @param id Id.
 * @param allocationId Phân bổ.
 * @param materialId Vật tư.
 * @param direction Chiều biến động.
 * @param baseQuantity Số lượng cơ sở.
 * @param unitCost Giá vốn đơn vị.
 * @param valueAmount Giá trị.
 * @param costingMethod Phương pháp giá.
 * @param postedAt Ngày ghi sổ.
 * @param postedBy Người ghi sổ.
 */
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
