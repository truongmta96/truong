package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;
import jakarta.validation.constraints.*;

/**
 * Dữ liệu đầu vào để tạo hoặc sửa quy đổi đơn vị. Các annotation trên trường kiểm tra dữ liệu trước khi xử lý; ID tự sinh và trường chỉ đọc không nằm trong mẫu ghi.
 * @param materialId Vật tư.
 * @param unitId Đơn vị tính.
 * @param conversionFactor Hệ số quy đổi.
 * @param allowReceipt Cho phép nhập.
 * @param allowIssue Cho phép xuất.
 */
public record MaterialUnitWriteDto(
    @NotNull Long materialId,
    @NotNull Long unitId,
    @NotNull @Digits(integer = 14, fraction = 6) @DecimalMin("0.000001") BigDecimal conversionFactor,
    @NotNull Boolean allowReceipt,
    @NotNull Boolean allowIssue
) {}
