package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

/**
 * Chuyển đổi giữa entity và DTO của phân quyền. Lớp này chỉ sao chép dữ liệu, không truy vấn database hoặc tự kiểm tra nghiệp vụ.
 */
@Component
public class UserRoleMapper {
    /**
     * Chuyển một entity phân quyền thành dữ liệu trả về giao diện; trả null nếu đầu vào là null.
     * @param entity Đối tượng lưu trữ đang được đọc hoặc cập nhật trong bộ nhớ.
     */
    public UserRoleDto toDto(UserRole entity) {
        if (entity == null) return null;
        return new UserRoleDto(entity.getUserId(), entity.getRoleId());
    }
    /**
     * Tạo entity phân quyền mới từ dữ liệu ghi. Chưa lưu database; ID tự sinh và trường do database tính không được sao chép từ client.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     */
    public UserRole toEntity(UserRoleWriteDto dto) { var entity = new UserRole(); update(dto, entity); entity.setUserId(dto.userId()); entity.setRoleId(dto.roleId()); return entity; }
    /**
     * Chép các trường cho phép sửa từ DTO vào entity phân quyền hiện có; giữ nguyên ID và trường chỉ đọc.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     * @param entity Đối tượng lưu trữ đang được đọc hoặc cập nhật trong bộ nhớ.
     */
    public void update(UserRoleWriteDto dto, UserRole entity) {
    }
}
