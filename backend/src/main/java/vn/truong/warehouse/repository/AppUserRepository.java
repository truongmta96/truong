package vn.truong.warehouse.repository;

import org.springframework.data.jpa.repository.*;
import vn.truong.warehouse.entity.*;

/**
 * Cổng truy cập dữ liệu người dùng qua Spring Data JPA. Spring tự triển khai tìm theo ID, phân trang và lưu; service quyết định nghiệp vụ được phép thực hiện. Có phương thức save không đồng nghĩa API cho phép ghi trực tiếp.
 */
public interface AppUserRepository extends JpaRepository<AppUser, Long>, JpaSpecificationExecutor<AppUser> {
    /**
     * Tìm tài khoản theo tên đăng nhập; trả Optional rỗng nếu không tồn tại. Việc so sánh tên theo collation của MySQL.
     * @param username Tên đăng nhập cần kiểm tra hoặc tạo mới.
     */
    java.util.Optional<AppUser> findByUsername(String username);
}
