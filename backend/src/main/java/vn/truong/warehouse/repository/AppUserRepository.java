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

    /** Đọc danh sách tài khoản không có vai trò ADMIN, lọc tại database để phân trang chính xác. */
    @Query("select u from AppUser u where not exists (select ur.userId from UserRole ur, AppRole r where ur.userId=u.id and ur.roleId=r.id and r.code='ADMIN')")
    org.springframework.data.domain.Page<AppUser> findNonAdmins(org.springframework.data.domain.Pageable pageable);

    /** Khóa tài khoản khi sửa; luồng cấp quyền trong tương lai cần dùng cùng giao thức khóa này. */
    @Lock(jakarta.persistence.LockModeType.PESSIMISTIC_WRITE)
    @Query("select u from AppUser u where u.id=:id")
    java.util.Optional<AppUser> findForUpdate(@org.springframework.data.repository.query.Param("id") Long id);
}
