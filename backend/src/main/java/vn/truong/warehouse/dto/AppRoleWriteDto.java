package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;
import jakarta.validation.constraints.*;

/**
 * Dữ liệu đầu vào để tạo hoặc sửa vai trò. Các annotation trên trường kiểm tra dữ liệu trước khi xử lý; ID tự sinh và trường chỉ đọc không nằm trong mẫu ghi.
 * @param code Mã.
 * @param name Tên.
 * Mẫu ghi này được service dùng nội bộ; API vai trò hiện chỉ cho tra cứu.
 */
public record AppRoleWriteDto(
    @NotBlank @Size(max = 40) String code,
    @NotBlank @Size(max = 100) String name
) {}
