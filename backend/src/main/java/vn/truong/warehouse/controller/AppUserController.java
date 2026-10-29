package vn.truong.warehouse.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.*;
import jakarta.validation.Valid;
import vn.truong.warehouse.dto.*;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.service.*;

/**
 * Nhận yêu cầu HTTP cho người dùng và chuyển cho service. Chỉ quản lý tài khoản không có quyền ADMIN; quản trị viên sửa hồ sơ cá nhân qua API /auth/me. Quyền truy cập được kiểm tra tập trung tại SecurityConfig.
 */
@RestController
@RequestMapping("/api/users")
public class AppUserController {
    private final AdminAccountService service;
    /**
     * Nhận các thành phần phụ thuộc qua hàm tạo để Spring kết nối lớp này với tầng xử lý bên dưới.
     */
    public AppUserController(AdminAccountService service) { this.service = service; }
    /**
     * Kiểm tra tham số trang và kích thước 1..200, rồi trả danh sách người dùng theo thứ tự khóa ổn định.
     * @param page Chỉ số trang bắt đầu từ 0 hoặc kết quả phân trang, tùy kiểu tham số.
     * @param size Số bản ghi tối đa trên trang; API chỉ chấp nhận 1..200.
     */
    @GetMapping
    public PageResponse<AppUserDto> list(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="20") int size) {
        if (page < 0 || size < 1 || size > 200) throw new IllegalArgumentException("Trang không hợp lệ; kích thước phải từ 1 đến 200.");
        return new PageResponse<>(service.findAll(PageRequest.of(page, size, Sort.by("id"))));
    }
    /**
     * Đọc chi tiết tài khoản không có quyền ADMIN; truy cập trực tiếp ID của ADMIN sẽ bị từ chối.
     */
    @GetMapping("/{id}")
    public AppUserDto get(@PathVariable Long id) { return service.findById(id); }
    /** Sửa họ tên và trạng thái tài khoản thường; không nhận thay đổi mật khẩu, tên đăng nhập hoặc quyền. */
    @PutMapping("/{id}")
    public AppUserDto update(@PathVariable Long id, @Valid @RequestBody AdminAccountWriteDto dto) {
        return service.update(id, dto);
    }
}
