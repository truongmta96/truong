package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;
import jakarta.validation.constraints.*;

/**
 * Dữ liệu đầu vào để tạo hoặc sửa lô vật tư. Các annotation trên trường kiểm tra dữ liệu trước khi xử lý; ID tự sinh và trường chỉ đọc không nằm trong mẫu ghi.
 * @param materialId Vật tư.
 * @param lotCode Mã lô.
 * @param manufacturedOn Ngày sản xuất.
 * @param expiresOn Hạn sử dụng.
 */
public record MaterialLotWriteDto(
    @NotNull Long materialId,
    @NotBlank @Size(max = 100) String lotCode,
    LocalDate manufacturedOn,
    LocalDate expiresOn
) {}
