package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

/**
 * Dữ liệu người nhận trả về cho giao diện, tách khỏi đối tượng JPA để tránh lộ dữ liệu hoặc quan hệ ngoài ý muốn.
 * @param id Id.
 * @param code Mã.
 * @param fullName Họ và tên.
 * @param department Bộ phận.
 * @param phone Điện thoại.
 * @param isActive Trạng thái.
 */
public record RecipientDto(
    Long id,
    String code,
    String fullName,
    String department,
    String phone,
    Boolean isActive
) {}
