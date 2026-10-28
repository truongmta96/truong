package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

/**
 * Chuyển đổi giữa entity và DTO của nhà cung cấp. Lớp này chỉ sao chép dữ liệu, không truy vấn database hoặc tự kiểm tra nghiệp vụ.
 */
@Component
public class SupplierMapper {
    /**
     * Chuyển một entity nhà cung cấp thành dữ liệu trả về giao diện; trả null nếu đầu vào là null.
     * @param entity Đối tượng lưu trữ đang được đọc hoặc cập nhật trong bộ nhớ.
     */
    public SupplierDto toDto(Supplier entity) {
        if (entity == null) return null;
        return new SupplierDto(entity.getId(), entity.getCode(), entity.getName(), entity.getPhone(), entity.getAddress(), entity.getTaxCode(), entity.getIsActive());
    }
    /**
     * Tạo entity nhà cung cấp mới từ dữ liệu ghi. Chưa lưu database; ID tự sinh và trường do database tính không được sao chép từ client.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     */
    public Supplier toEntity(SupplierWriteDto dto) { var entity = new Supplier(); update(dto, entity); return entity; }
    /**
     * Chép các trường cho phép sửa từ DTO vào entity nhà cung cấp hiện có; giữ nguyên ID và trường chỉ đọc.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     * @param entity Đối tượng lưu trữ đang được đọc hoặc cập nhật trong bộ nhớ.
     */
    public void update(SupplierWriteDto dto, Supplier entity) {
        entity.setCode(dto.code());
        entity.setName(dto.name());
        entity.setPhone(dto.phone());
        entity.setAddress(dto.address());
        entity.setTaxCode(dto.taxCode());
        entity.setIsActive(dto.isActive());
    }
}
