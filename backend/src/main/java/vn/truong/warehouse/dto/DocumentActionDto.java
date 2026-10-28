package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

/**
 * Dữ liệu lịch sử phiếu trả về cho giao diện, tách khỏi đối tượng JPA để tránh lộ dữ liệu hoặc quan hệ ngoài ý muốn.
 * @param id Id.
 * @param documentId Phiếu kho.
 * @param revision Phiên bản phiếu.
 * @param action Hành động.
 * @param actorId Người thực hiện.
 * @param comment Nhận xét.
 * @param actedAt Thời điểm.
 */
public record DocumentActionDto(
    Long id,
    Long documentId,
    Long revision,
    DocumentActionAction action,
    Long actorId,
    String comment,
    LocalDateTime actedAt
) {}
