package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

/**
 * Chuyển đổi giữa entity và DTO của người dùng. Lớp này chỉ sao chép dữ liệu, không truy vấn database hoặc tự kiểm tra nghiệp vụ.
 */
@Component
public class AppUserMapper {
    /**
     * Chuyển một entity người dùng thành dữ liệu trả về giao diện; trả null nếu đầu vào là null.
     * @param entity Đối tượng lưu trữ đang được đọc hoặc cập nhật trong bộ nhớ.
     */
    public AppUserDto toDto(AppUser entity) {
        if (entity == null) return null;
        return new AppUserDto(entity.getId(), entity.getUsername(), entity.getFullName(), entity.getIsActive(), entity.getCreatedAt());
    }
}
