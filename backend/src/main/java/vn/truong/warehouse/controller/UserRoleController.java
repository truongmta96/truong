package vn.truong.warehouse.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.*;
import jakarta.validation.Valid;
import vn.truong.warehouse.dto.*;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.service.*;

/**
 * Nhận yêu cầu HTTP cho phân quyền và chuyển cho service. Các endpoint của lớp này chỉ phục vụ tra cứu. Quyền truy cập được kiểm tra tập trung tại SecurityConfig.
 */
@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {
    private final UserRoleService service;
    /**
     * Nhận các thành phần phụ thuộc qua hàm tạo để Spring kết nối lớp này với tầng xử lý bên dưới.
     */
    public UserRoleController(UserRoleService service) { this.service = service; }
    /**
     * Kiểm tra tham số trang và kích thước 1..200, rồi trả danh sách phân quyền theo thứ tự khóa ổn định.
     * @param page Chỉ số trang bắt đầu từ 0 hoặc kết quả phân trang, tùy kiểu tham số.
     * @param size Số bản ghi tối đa trên trang; API chỉ chấp nhận 1..200.
     */
    @GetMapping
    public PageResponse<UserRoleDto> list(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="20") int size) {
        if (page < 0 || size < 1 || size > 200) throw new IllegalArgumentException("Trang không hợp lệ; kích thước phải từ 1 đến 200.");
        return new PageResponse<>(service.findAll(PageRequest.of(page, size, Sort.by("userId"))));
    }
    /**
     * Đọc giá trị  hiện có trong đối tượng Java.
     */
    @GetMapping("/{userId}/{roleId}")
    public UserRoleDto get(@PathVariable Long userId, @PathVariable Long roleId) { return service.findById(new UserRoleId(userId, roleId)); }
}
