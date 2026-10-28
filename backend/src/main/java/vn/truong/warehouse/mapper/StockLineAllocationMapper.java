package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

/**
 * Chuyển đổi giữa entity và DTO của phân bổ lô / serial. Lớp này chỉ sao chép dữ liệu, không truy vấn database hoặc tự kiểm tra nghiệp vụ.
 */
@Component
public class StockLineAllocationMapper {
    /**
     * Chuyển một entity phân bổ lô / serial thành dữ liệu trả về giao diện; trả null nếu đầu vào là null.
     * @param entity Đối tượng lưu trữ đang được đọc hoặc cập nhật trong bộ nhớ.
     */
    public StockLineAllocationDto toDto(StockLineAllocation entity) {
        if (entity == null) return null;
        return new StockLineAllocationDto(entity.getId(), entity.getLineId(), entity.getMaterialId(), entity.getLotId(), entity.getSerialId(), entity.getBaseQuantity());
    }
}
