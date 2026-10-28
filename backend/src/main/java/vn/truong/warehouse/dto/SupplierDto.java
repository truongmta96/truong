package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

/**
 * Dữ liệu nhà cung cấp trả về cho giao diện, tách khỏi đối tượng JPA để tránh lộ dữ liệu hoặc quan hệ ngoài ý muốn.
 * @param id Id.
 * @param code Mã.
 * @param name Tên.
 * @param phone Điện thoại.
 * @param address Địa chỉ.
 * @param taxCode Mã số thuế.
 * @param isActive Trạng thái.
 */
public record SupplierDto(
    Long id,
    String code,
    String name,
    String phone,
    String address,
    String taxCode,
    Boolean isActive
) {}
