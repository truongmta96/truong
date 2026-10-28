package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;
import jakarta.validation.constraints.*;

/**
 * Dữ liệu đầu vào để tạo hoặc sửa serial vật tư. Các annotation trên trường kiểm tra dữ liệu trước khi xử lý; ID tự sinh và trường chỉ đọc không nằm trong mẫu ghi.
 * @param materialId Vật tư.
 * @param lotId Lô vật tư.
 * @param serialNumber Số serial.
 * @param expiresOn Hạn sử dụng.
 */
public record MaterialSerialWriteDto(
    @NotNull Long materialId,
    Long lotId,
    @NotBlank @Size(max = 150) String serialNumber,
    LocalDate expiresOn
) {}
