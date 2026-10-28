package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;
import jakarta.validation.constraints.*;

/**
 * Dữ liệu đầu vào để tạo hoặc sửa đơn vị tính. Các annotation trên trường kiểm tra dữ liệu trước khi xử lý; ID tự sinh và trường chỉ đọc không nằm trong mẫu ghi.
 * @param code Mã.
 * @param name Tên.
 */
public record UnitOfMeasureWriteDto(
    @NotBlank @Size(max = 30) String code,
    @NotBlank @Size(max = 100) String name
) {}
