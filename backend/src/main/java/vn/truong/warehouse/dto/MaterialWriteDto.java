package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;
import jakarta.validation.constraints.*;

/**
 * Dữ liệu đầu vào để tạo hoặc sửa vật tư. Các annotation trên trường kiểm tra dữ liệu trước khi xử lý; ID tự sinh và trường chỉ đọc không nằm trong mẫu ghi.
 * @param code Mã.
 * @param name Tên.
 * @param categoryId Nhóm vật tư.
 * @param baseUnitId Đơn vị cơ sở.
 * @param trackLot Theo dõi lô.
 * @param trackSerial Theo dõi serial.
 * @param trackExpiry Theo dõi hạn dùng.
 * @param allowFractionalQuantity Cho phép số lẻ.
 * @param minStock Tồn tối thiểu.
 * @param isActive Trạng thái.
 */
public record MaterialWriteDto(
    @NotBlank @Size(max = 60) String code,
    @NotBlank @Size(max = 255) String name,
    Long categoryId,
    @NotNull Long baseUnitId,
    @NotNull Boolean trackLot,
    @NotNull Boolean trackSerial,
    @NotNull Boolean trackExpiry,
    @NotNull Boolean allowFractionalQuantity,
    @NotNull @Digits(integer = 14, fraction = 6) @DecimalMin("0") BigDecimal minStock,
    @NotNull Boolean isActive
) {}
