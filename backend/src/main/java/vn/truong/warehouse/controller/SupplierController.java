package vn.truong.warehouse.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.*;
import jakarta.validation.Valid;
import vn.truong.warehouse.dto.*;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.service.*;

/**
 * Nhận yêu cầu HTTP cho nhà cung cấp và chuyển cho service. Hỗ trợ tạo/sửa theo quyền ghi danh mục. Quyền truy cập được kiểm tra tập trung tại SecurityConfig.
 */
@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    private final SupplierService service;
    /**
     * Nhận các thành phần phụ thuộc qua hàm tạo để Spring kết nối lớp này với tầng xử lý bên dưới.
     */
    public SupplierController(SupplierService service) { this.service = service; }
    /**
     * Kiểm tra tham số trang và kích thước 1..200, rồi trả danh sách nhà cung cấp theo thứ tự khóa ổn định.
     * @param page Chỉ số trang bắt đầu từ 0 hoặc kết quả phân trang, tùy kiểu tham số.
     * @param size Số bản ghi tối đa trên trang; API chỉ chấp nhận 1..200.
     */
    @GetMapping
    public PageResponse<SupplierDto> list(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="20") int size) {
        if (page < 0 || size < 1 || size > 200) throw new IllegalArgumentException("Trang không hợp lệ; kích thước phải từ 1 đến 200.");
        return new PageResponse<>(service.findAll(PageRequest.of(page, size, Sort.by("id"))));
    }
    /**
     * Đọc giá trị  hiện có trong đối tượng Java.
     */
    @GetMapping("/{id}")
    public SupplierDto get(@PathVariable Long id) { return service.findById(id); }
    /**
     * Nhận DTO đã kiểm tra, gọi service tạo nhà cung cấp và trả HTTP 201.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SupplierDto create(@Valid @RequestBody SupplierWriteDto dto) { return service.create(dto); }
    /**
     * Nhận khóa và DTO hợp lệ, chuyển yêu cầu sửa nhà cung cấp sang service.
     * @param id Khóa của bản ghi; với bảng liên kết có thể là một khóa gồm nhiều thành phần.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     */
    @PutMapping("/{id}")
    public SupplierDto update(@PathVariable Long id, @Valid @RequestBody SupplierWriteDto dto) { return service.update(id, dto); }
}
