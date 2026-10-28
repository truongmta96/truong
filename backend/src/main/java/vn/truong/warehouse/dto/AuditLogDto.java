package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

/**
 * Dữ liệu nhật ký hệ thống trả về cho giao diện, tách khỏi đối tượng JPA để tránh lộ dữ liệu hoặc quan hệ ngoài ý muốn.
 * @param id Id.
 * @param actorId Người thực hiện.
 * @param entityType Đối tượng.
 * @param entityId Id đối tượng.
 * @param action Hành động.
 * @param oldData Dữ liệu json trước thay đổi.
 * @param newData Dữ liệu json sau thay đổi.
 * @param createdAt Ngày tạo.
 */
public record AuditLogDto(
    Long id,
    Long actorId,
    String entityType,
    Long entityId,
    String action,
    JsonNode oldData,
    JsonNode newData,
    LocalDateTime createdAt
) {}
