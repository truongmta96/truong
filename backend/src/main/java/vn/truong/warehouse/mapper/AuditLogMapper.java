package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

/**
 * Chuyển đổi giữa entity và DTO của nhật ký hệ thống. Lớp này chỉ sao chép dữ liệu, không truy vấn database hoặc tự kiểm tra nghiệp vụ.
 */
@Component
public class AuditLogMapper {
    /**
     * Chuyển một entity nhật ký hệ thống thành dữ liệu trả về giao diện; trả null nếu đầu vào là null.
     * @param entity Đối tượng lưu trữ đang được đọc hoặc cập nhật trong bộ nhớ.
     */
    public AuditLogDto toDto(AuditLog entity) {
        if (entity == null) return null;
        return new AuditLogDto(entity.getId(), entity.getActorId(), entity.getEntityType(), entity.getEntityId(), entity.getAction(), entity.getOldData(), entity.getNewData(), entity.getCreatedAt());
    }
}
