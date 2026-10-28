package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

/**
 * Chuyển đổi giữa entity và DTO của vật tư. Lớp này chỉ sao chép dữ liệu, không truy vấn database hoặc tự kiểm tra nghiệp vụ.
 */
@Component
public class MaterialMapper {
    /**
     * Chuyển một entity vật tư thành dữ liệu trả về giao diện; trả null nếu đầu vào là null.
     * @param entity Đối tượng lưu trữ đang được đọc hoặc cập nhật trong bộ nhớ.
     */
    public MaterialDto toDto(Material entity) {
        if (entity == null) return null;
        return new MaterialDto(entity.getId(), entity.getCode(), entity.getName(), entity.getCategoryId(), entity.getBaseUnitId(), entity.getTrackLot(), entity.getTrackSerial(), entity.getTrackExpiry(), entity.getAllowFractionalQuantity(), entity.getMinStock(), entity.getIsActive(), entity.getCreatedAt());
    }
    /**
     * Tạo entity vật tư mới từ dữ liệu ghi. Chưa lưu database; ID tự sinh và trường do database tính không được sao chép từ client.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     */
    public Material toEntity(MaterialWriteDto dto) { var entity = new Material(); update(dto, entity); return entity; }
    /**
     * Chép các trường cho phép sửa từ DTO vào entity vật tư hiện có; giữ nguyên ID và trường chỉ đọc.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     * @param entity Đối tượng lưu trữ đang được đọc hoặc cập nhật trong bộ nhớ.
     */
    public void update(MaterialWriteDto dto, Material entity) {
        entity.setCode(dto.code());
        entity.setName(dto.name());
        entity.setCategoryId(dto.categoryId());
        entity.setBaseUnitId(dto.baseUnitId());
        entity.setTrackLot(dto.trackLot());
        entity.setTrackSerial(dto.trackSerial());
        entity.setTrackExpiry(dto.trackExpiry());
        entity.setAllowFractionalQuantity(dto.allowFractionalQuantity());
        entity.setMinStock(dto.minStock());
        entity.setIsActive(dto.isActive());
    }
}
