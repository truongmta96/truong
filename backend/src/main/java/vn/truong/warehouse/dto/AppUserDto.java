package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

/**
 * Dữ liệu người dùng trả về cho giao diện, tách khỏi đối tượng JPA để tránh lộ dữ liệu hoặc quan hệ ngoài ý muốn.
 * @param id Id.
 * @param username Tên đăng nhập.
 * @param fullName Họ và tên.
 * @param isActive Trạng thái.
 * @param createdAt Ngày tạo.
 * Mẫu trả về này cố ý không có trường passwordHash.
 */
public record AppUserDto(
    Long id,
    String username,
    String fullName,
    Boolean isActive,
    LocalDateTime createdAt
) {}
