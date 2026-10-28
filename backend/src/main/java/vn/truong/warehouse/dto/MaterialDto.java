package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

/**
 * Dữ liệu vật tư trả về cho giao diện, tách khỏi đối tượng JPA để tránh lộ dữ liệu hoặc quan hệ ngoài ý muốn.
 * @param id Id.
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
 * @param createdAt Ngày tạo.
 */
public record MaterialDto(
    Long id,
    String code,
    String name,
    Long categoryId,
    Long baseUnitId,
    Boolean trackLot,
    Boolean trackSerial,
    Boolean trackExpiry,
    Boolean allowFractionalQuantity,
    BigDecimal minStock,
    Boolean isActive,
    LocalDateTime createdAt
) {}
