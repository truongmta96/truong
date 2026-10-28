package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

/**
 * Dữ liệu quy đổi đơn vị trả về cho giao diện, tách khỏi đối tượng JPA để tránh lộ dữ liệu hoặc quan hệ ngoài ý muốn.
 * @param materialId Vật tư.
 * @param unitId Đơn vị tính.
 * @param conversionFactor Hệ số quy đổi.
 * @param allowReceipt Cho phép nhập.
 * @param allowIssue Cho phép xuất.
 */
public record MaterialUnitDto(
    Long materialId,
    Long unitId,
    BigDecimal conversionFactor,
    Boolean allowReceipt,
    Boolean allowIssue
) {}
