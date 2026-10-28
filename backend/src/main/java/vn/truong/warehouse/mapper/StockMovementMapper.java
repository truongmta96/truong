package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

/**
 * Chuyển đổi giữa entity và DTO của sổ biến động. Lớp này chỉ sao chép dữ liệu, không truy vấn database hoặc tự kiểm tra nghiệp vụ.
 */
@Component
public class StockMovementMapper {
    /**
     * Chuyển một entity sổ biến động thành dữ liệu trả về giao diện; trả null nếu đầu vào là null.
     * @param entity Đối tượng lưu trữ đang được đọc hoặc cập nhật trong bộ nhớ.
     */
    public StockMovementDto toDto(StockMovement entity) {
        if (entity == null) return null;
        return new StockMovementDto(entity.getId(), entity.getAllocationId(), entity.getMaterialId(), entity.getDirection(), entity.getBaseQuantity(), entity.getUnitCost(), entity.getValueAmount(), entity.getCostingMethod(), entity.getPostedAt(), entity.getPostedBy());
    }
}
