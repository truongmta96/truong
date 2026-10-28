package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

/**
 * Chuyển đổi giữa entity và DTO của chi tiết phiếu. Lớp này chỉ sao chép dữ liệu, không truy vấn database hoặc tự kiểm tra nghiệp vụ.
 */
@Component
public class StockDocumentLineMapper {
    /**
     * Chuyển một entity chi tiết phiếu thành dữ liệu trả về giao diện; trả null nếu đầu vào là null.
     * @param entity Đối tượng lưu trữ đang được đọc hoặc cập nhật trong bộ nhớ.
     */
    public StockDocumentLineDto toDto(StockDocumentLine entity) {
        if (entity == null) return null;
        return new StockDocumentLineDto(entity.getId(), entity.getDocumentId(), entity.getLineNo(), entity.getMaterialId(), entity.getUnitId(), entity.getQuantity(), entity.getConversionFactor(), entity.getBaseQuantity(), entity.getInputUnitPrice(), entity.getNote());
    }
}
