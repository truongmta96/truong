package vn.truong.warehouse.dto;

import java.math.BigDecimal;
import java.time.*;
import com.fasterxml.jackson.databind.JsonNode;
import vn.truong.warehouse.enums.*;

/**
 * Dữ liệu phiếu kho trả về cho giao diện, tách khỏi đối tượng JPA để tránh lộ dữ liệu hoặc quan hệ ngoài ý muốn.
 * @param id Id.
 * @param documentNo Số phiếu.
 * @param documentType Loại phiếu.
 * @param status Trạng thái.
 * @param documentDate Ngày phiếu.
 * @param supplierId Nhà cung cấp.
 * @param recipientId Người nhận.
 * @param recipientNameSnapshot Tên người nhận trên phiếu.
 * @param recipientDepartmentSnapshot Bộ phận trên phiếu.
 * @param reason Lý do.
 * @param createdBy Người tạo.
 * @param submittedAt Ngày gửi duyệt.
 * @param approvedBy Người duyệt.
 * @param approvedAt Ngày duyệt.
 * @param postedBy Người ghi sổ.
 * @param postedAt Ngày ghi sổ.
 * @param revision Phiên bản phiếu.
 * @param createdAt Ngày tạo.
 * @param updatedAt Cập nhật.
 */
public record StockDocumentDto(
    Long id,
    String documentNo,
    StockDocumentDocumentType documentType,
    StockDocumentStatus status,
    LocalDate documentDate,
    Long supplierId,
    Long recipientId,
    String recipientNameSnapshot,
    String recipientDepartmentSnapshot,
    String reason,
    Long createdBy,
    LocalDateTime submittedAt,
    Long approvedBy,
    LocalDateTime approvedAt,
    Long postedBy,
    LocalDateTime postedAt,
    Long revision,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
