package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

/**
 * Chuyển đổi giữa entity và DTO của nhóm vật tư. Lớp này chỉ sao chép dữ liệu, không truy vấn database hoặc tự kiểm tra nghiệp vụ.
 */
@Component
public class MaterialCategoryMapper {
    /**
     * Chuyển một entity nhóm vật tư thành dữ liệu trả về giao diện; trả null nếu đầu vào là null.
     * @param entity Đối tượng lưu trữ đang được đọc hoặc cập nhật trong bộ nhớ.
     */
    public MaterialCategoryDto toDto(MaterialCategory entity) {
        if (entity == null) return null;
        return new MaterialCategoryDto(entity.getId(), entity.getCode(), entity.getName());
    }
    /**
     * Tạo entity nhóm vật tư mới từ dữ liệu ghi. Chưa lưu database; ID tự sinh và trường do database tính không được sao chép từ client.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     */
    public MaterialCategory toEntity(MaterialCategoryWriteDto dto) { var entity = new MaterialCategory(); update(dto, entity); return entity; }
    /**
     * Chép các trường cho phép sửa từ DTO vào entity nhóm vật tư hiện có; giữ nguyên ID và trường chỉ đọc.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     * @param entity Đối tượng lưu trữ đang được đọc hoặc cập nhật trong bộ nhớ.
     */
    public void update(MaterialCategoryWriteDto dto, MaterialCategory entity) {
        entity.setCode(dto.code());
        entity.setName(dto.name());
    }
}
