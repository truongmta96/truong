package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;
import jakarta.validation.constraints.*;

/**
 * Dữ liệu đầu vào để tạo hoặc sửa phân quyền. Các annotation trên trường kiểm tra dữ liệu trước khi xử lý; ID tự sinh và trường chỉ đọc không nằm trong mẫu ghi.
 * @param userId Người dùng.
 * @param roleId Vai trò.
 * Mẫu ghi này được service dùng nội bộ; API phân quyền hiện chỉ cho tra cứu, không cho client tự cấp quyền.
 */
public record UserRoleWriteDto(
    @NotNull Long userId,
    @NotNull Long roleId
) {}
