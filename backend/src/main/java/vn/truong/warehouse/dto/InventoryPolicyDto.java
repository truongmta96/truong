package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

/**
 * Dữ liệu chính sách kho trả về cho giao diện, tách khỏi đối tượng JPA để tránh lộ dữ liệu hoặc quan hệ ngoài ý muốn.
 * @param id Id.
 * @param costingMethod Phương pháp giá.
 * @param postingMode Thời điểm ghi sổ.
 */
public record InventoryPolicyDto(
    Integer id,
    InventoryPolicyCostingMethod costingMethod,
    InventoryPolicyPostingMode postingMode
) {}
