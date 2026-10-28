package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;
import jakarta.validation.constraints.*;

/**
 * Dữ liệu đầu vào để tạo hoặc sửa người nhận. Các annotation trên trường kiểm tra dữ liệu trước khi xử lý; ID tự sinh và trường chỉ đọc không nằm trong mẫu ghi.
 * @param code Mã.
 * @param fullName Họ và tên.
 * @param department Bộ phận.
 * @param phone Điện thoại.
 * @param isActive Trạng thái.
 */
public record RecipientWriteDto(
    @NotBlank @Size(max = 60) String code,
    @NotBlank @Size(max = 200) String fullName,
    @Size(max = 200) String department,
    @Size(max = 30) String phone,
    @NotNull Boolean isActive
) {}
