package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

/**
 * Chuyển đổi giữa entity và DTO của serial vật tư. Lớp này chỉ sao chép dữ liệu, không truy vấn database hoặc tự kiểm tra nghiệp vụ.
 */
@Component
public class MaterialSerialMapper {
    /**
     * Chuyển một entity serial vật tư thành dữ liệu trả về giao diện; trả null nếu đầu vào là null.
     * @param entity Đối tượng lưu trữ đang được đọc hoặc cập nhật trong bộ nhớ.
     */
    public MaterialSerialDto toDto(MaterialSerial entity) {
        if (entity == null) return null;
        return new MaterialSerialDto(entity.getId(), entity.getMaterialId(), entity.getLotId(), entity.getSerialNumber(), entity.getExpiresOn());
    }
    /**
     * Tạo entity serial vật tư mới từ dữ liệu ghi. Chưa lưu database; ID tự sinh và trường do database tính không được sao chép từ client.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     */
    public MaterialSerial toEntity(MaterialSerialWriteDto dto) { var entity = new MaterialSerial(); update(dto, entity); return entity; }
    /**
     * Chép các trường cho phép sửa từ DTO vào entity serial vật tư hiện có; giữ nguyên ID và trường chỉ đọc.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     * @param entity Đối tượng lưu trữ đang được đọc hoặc cập nhật trong bộ nhớ.
     */
    public void update(MaterialSerialWriteDto dto, MaterialSerial entity) {
        entity.setMaterialId(dto.materialId());
        entity.setLotId(dto.lotId());
        entity.setSerialNumber(dto.serialNumber());
        entity.setExpiresOn(dto.expiresOn());
    }
}
