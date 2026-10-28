package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

/**
 * Chuyển đổi giữa entity và DTO của chính sách kho. Lớp này chỉ sao chép dữ liệu, không truy vấn database hoặc tự kiểm tra nghiệp vụ.
 */
@Component
public class InventoryPolicyMapper {
    /**
     * Chuyển một entity chính sách kho thành dữ liệu trả về giao diện; trả null nếu đầu vào là null.
     * @param entity Đối tượng lưu trữ đang được đọc hoặc cập nhật trong bộ nhớ.
     */
    public InventoryPolicyDto toDto(InventoryPolicy entity) {
        if (entity == null) return null;
        return new InventoryPolicyDto(entity.getId(), entity.getCostingMethod(), entity.getPostingMode());
    }
}
