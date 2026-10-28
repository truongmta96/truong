package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

/**
 * Chuyển đổi giữa entity và DTO của phiếu kho. Lớp này chỉ sao chép dữ liệu, không truy vấn database hoặc tự kiểm tra nghiệp vụ.
 */
@Component
public class StockDocumentMapper {
    /**
     * Chuyển một entity phiếu kho thành dữ liệu trả về giao diện; trả null nếu đầu vào là null.
     * @param entity Đối tượng lưu trữ đang được đọc hoặc cập nhật trong bộ nhớ.
     */
    public StockDocumentDto toDto(StockDocument entity) {
        if (entity == null) return null;
        return new StockDocumentDto(entity.getId(), entity.getDocumentNo(), entity.getDocumentType(), entity.getStatus(), entity.getDocumentDate(), entity.getSupplierId(), entity.getRecipientId(), entity.getRecipientNameSnapshot(), entity.getRecipientDepartmentSnapshot(), entity.getReason(), entity.getCreatedBy(), entity.getSubmittedAt(), entity.getApprovedBy(), entity.getApprovedAt(), entity.getPostedBy(), entity.getPostedAt(), entity.getRevision(), entity.getCreatedAt(), entity.getUpdatedAt());
    }
}
