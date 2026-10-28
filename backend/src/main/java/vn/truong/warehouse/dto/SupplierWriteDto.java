package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;
import jakarta.validation.constraints.*;

/**
 * Dữ liệu đầu vào để tạo hoặc sửa nhà cung cấp. Các annotation trên trường kiểm tra dữ liệu trước khi xử lý; ID tự sinh và trường chỉ đọc không nằm trong mẫu ghi.
 * @param code Mã.
 * @param name Tên.
 * @param phone Điện thoại.
 * @param address Địa chỉ.
 * @param taxCode Mã số thuế.
 * @param isActive Trạng thái.
 */
public record SupplierWriteDto(
    @NotBlank @Size(max = 60) String code,
    @NotBlank @Size(max = 255) String name,
    @Size(max = 30) String phone,
    @Size(max = 500) String address,
    @Size(max = 30) String taxCode,
    @NotNull Boolean isActive
) {}
