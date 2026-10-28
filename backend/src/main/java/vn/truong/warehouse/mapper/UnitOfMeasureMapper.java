package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

/**
 * Chuyển đổi giữa entity và DTO của đơn vị tính. Lớp này chỉ sao chép dữ liệu, không truy vấn database hoặc tự kiểm tra nghiệp vụ.
 */
@Component
public class UnitOfMeasureMapper {
    /**
     * Chuyển một entity đơn vị tính thành dữ liệu trả về giao diện; trả null nếu đầu vào là null.
     * @param entity Đối tượng lưu trữ đang được đọc hoặc cập nhật trong bộ nhớ.
     */
    public UnitOfMeasureDto toDto(UnitOfMeasure entity) {
        if (entity == null) return null;
        return new UnitOfMeasureDto(entity.getId(), entity.getCode(), entity.getName());
    }
    /**
     * Tạo entity đơn vị tính mới từ dữ liệu ghi. Chưa lưu database; ID tự sinh và trường do database tính không được sao chép từ client.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     */
    public UnitOfMeasure toEntity(UnitOfMeasureWriteDto dto) { var entity = new UnitOfMeasure(); update(dto, entity); return entity; }
    /**
     * Chép các trường cho phép sửa từ DTO vào entity đơn vị tính hiện có; giữ nguyên ID và trường chỉ đọc.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     * @param entity Đối tượng lưu trữ đang được đọc hoặc cập nhật trong bộ nhớ.
     */
    public void update(UnitOfMeasureWriteDto dto, UnitOfMeasure entity) {
        entity.setCode(dto.code());
        entity.setName(dto.name());
    }
}
