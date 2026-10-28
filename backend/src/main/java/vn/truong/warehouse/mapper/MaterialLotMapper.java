package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

/**
 * Chuyển đổi giữa entity và DTO của lô vật tư. Lớp này chỉ sao chép dữ liệu, không truy vấn database hoặc tự kiểm tra nghiệp vụ.
 */
@Component
public class MaterialLotMapper {
    /**
     * Chuyển một entity lô vật tư thành dữ liệu trả về giao diện; trả null nếu đầu vào là null.
     * @param entity Đối tượng lưu trữ đang được đọc hoặc cập nhật trong bộ nhớ.
     */
    public MaterialLotDto toDto(MaterialLot entity) {
        if (entity == null) return null;
        return new MaterialLotDto(entity.getId(), entity.getMaterialId(), entity.getLotCode(), entity.getManufacturedOn(), entity.getExpiresOn());
    }
    /**
     * Tạo entity lô vật tư mới từ dữ liệu ghi. Chưa lưu database; ID tự sinh và trường do database tính không được sao chép từ client.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     */
    public MaterialLot toEntity(MaterialLotWriteDto dto) { var entity = new MaterialLot(); update(dto, entity); return entity; }
    /**
     * Chép các trường cho phép sửa từ DTO vào entity lô vật tư hiện có; giữ nguyên ID và trường chỉ đọc.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     * @param entity Đối tượng lưu trữ đang được đọc hoặc cập nhật trong bộ nhớ.
     */
    public void update(MaterialLotWriteDto dto, MaterialLot entity) {
        entity.setMaterialId(dto.materialId());
        entity.setLotCode(dto.lotCode());
        entity.setManufacturedOn(dto.manufacturedOn());
        entity.setExpiresOn(dto.expiresOn());
    }
}
