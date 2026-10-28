package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

/**
 * Chuyển đổi giữa entity và DTO của lịch sử phiếu. Lớp này chỉ sao chép dữ liệu, không truy vấn database hoặc tự kiểm tra nghiệp vụ.
 */
@Component
public class DocumentActionMapper {
    /**
     * Chuyển một entity lịch sử phiếu thành dữ liệu trả về giao diện; trả null nếu đầu vào là null.
     * @param entity Đối tượng lưu trữ đang được đọc hoặc cập nhật trong bộ nhớ.
     */
    public DocumentActionDto toDto(DocumentAction entity) {
        if (entity == null) return null;
        return new DocumentActionDto(entity.getId(), entity.getDocumentId(), entity.getRevision(), entity.getAction(), entity.getActorId(), entity.getComment(), entity.getActedAt());
    }
}
