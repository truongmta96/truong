package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

/**
 * Chuyển đổi giữa entity và DTO của người nhận. Lớp này chỉ sao chép dữ liệu, không truy vấn database hoặc tự kiểm tra nghiệp vụ.
 */
@Component
public class RecipientMapper {
    /**
     * Chuyển một entity người nhận thành dữ liệu trả về giao diện; trả null nếu đầu vào là null.
     * @param entity Đối tượng lưu trữ đang được đọc hoặc cập nhật trong bộ nhớ.
     */
    public RecipientDto toDto(Recipient entity) {
        if (entity == null) return null;
        return new RecipientDto(entity.getId(), entity.getCode(), entity.getFullName(), entity.getDepartment(), entity.getPhone(), entity.getIsActive());
    }
    /**
     * Tạo entity người nhận mới từ dữ liệu ghi. Chưa lưu database; ID tự sinh và trường do database tính không được sao chép từ client.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     */
    public Recipient toEntity(RecipientWriteDto dto) { var entity = new Recipient(); update(dto, entity); return entity; }
    /**
     * Chép các trường cho phép sửa từ DTO vào entity người nhận hiện có; giữ nguyên ID và trường chỉ đọc.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     * @param entity Đối tượng lưu trữ đang được đọc hoặc cập nhật trong bộ nhớ.
     */
    public void update(RecipientWriteDto dto, Recipient entity) {
        entity.setCode(dto.code());
        entity.setFullName(dto.fullName());
        entity.setDepartment(dto.department());
        entity.setPhone(dto.phone());
        entity.setIsActive(dto.isActive());
    }
}
