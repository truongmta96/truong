package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

/**
 * Dữ liệu phân bổ giá vốn trả về cho giao diện, tách khỏi đối tượng JPA để tránh lộ dữ liệu hoặc quan hệ ngoài ý muốn.
 * @param id Id.
 * @param issueMovementId Biến động xuất.
 * @param receiptMovementId Biến động nhập.
 * @param baseQuantity Số lượng cơ sở.
 * @param valueAmount Giá trị.
 */
public record CostAllocationDto(
    Long id,
    Long issueMovementId,
    Long receiptMovementId,
    BigDecimal baseQuantity,
    BigDecimal valueAmount
) {}
