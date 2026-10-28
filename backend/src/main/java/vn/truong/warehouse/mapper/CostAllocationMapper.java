package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

/**
 * Chuyển đổi giữa entity và DTO của phân bổ giá vốn. Lớp này chỉ sao chép dữ liệu, không truy vấn database hoặc tự kiểm tra nghiệp vụ.
 */
@Component
public class CostAllocationMapper {
    /**
     * Chuyển một entity phân bổ giá vốn thành dữ liệu trả về giao diện; trả null nếu đầu vào là null.
     * @param entity Đối tượng lưu trữ đang được đọc hoặc cập nhật trong bộ nhớ.
     */
    public CostAllocationDto toDto(CostAllocation entity) {
        if (entity == null) return null;
        return new CostAllocationDto(entity.getId(), entity.getIssueMovementId(), entity.getReceiptMovementId(), entity.getBaseQuantity(), entity.getValueAmount());
    }
}
