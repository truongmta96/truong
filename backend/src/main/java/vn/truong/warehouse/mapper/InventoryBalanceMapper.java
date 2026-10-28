package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

/**
 * Chuyển đổi giữa entity và DTO của tồn kho. Lớp này chỉ sao chép dữ liệu, không truy vấn database hoặc tự kiểm tra nghiệp vụ.
 */
@Component
public class InventoryBalanceMapper {
    /**
     * Chuyển một entity tồn kho thành dữ liệu trả về giao diện; trả null nếu đầu vào là null.
     * @param entity Đối tượng lưu trữ đang được đọc hoặc cập nhật trong bộ nhớ.
     */
    public InventoryBalanceDto toDto(InventoryBalance entity) {
        if (entity == null) return null;
        return new InventoryBalanceDto(entity.getMaterialId(), entity.getQuantityOnHand(), entity.getInventoryValue(), entity.getVersion(), entity.getUpdatedAt());
    }
}
