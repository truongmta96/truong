package vn.truong.warehouse.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.*;
import jakarta.validation.Valid;
import vn.truong.warehouse.dto.*;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.service.*;

/**
 * Nhận yêu cầu HTTP cho quy đổi đơn vị và chuyển cho service. Hỗ trợ tạo/sửa theo quyền ghi danh mục. Quyền truy cập được kiểm tra tập trung tại SecurityConfig.
 */
@RestController
@RequestMapping("/api/material-units")
public class MaterialUnitController {
    private final MaterialUnitService service;
    /**
     * Nhận các thành phần phụ thuộc qua hàm tạo để Spring kết nối lớp này với tầng xử lý bên dưới.
     */
    public MaterialUnitController(MaterialUnitService service) { this.service = service; }
    /**
     * Kiểm tra tham số trang và kích thước 1..200, rồi trả danh sách quy đổi đơn vị theo thứ tự khóa ổn định.
     * @param page Chỉ số trang bắt đầu từ 0 hoặc kết quả phân trang, tùy kiểu tham số.
     * @param size Số bản ghi tối đa trên trang; API chỉ chấp nhận 1..200.
     */
    @GetMapping
    public PageResponse<MaterialUnitDto> list(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="20") int size) {
        if (page < 0 || size < 1 || size > 200) throw new IllegalArgumentException("Trang không hợp lệ; kích thước phải từ 1 đến 200.");
        return new PageResponse<>(service.findAll(PageRequest.of(page, size, Sort.by("materialId"))));
    }
    /**
     * Đọc giá trị  hiện có trong đối tượng Java.
     */
    @GetMapping("/{materialId}/{unitId}")
    public MaterialUnitDto get(@PathVariable Long materialId, @PathVariable Long unitId) { return service.findById(new MaterialUnitId(materialId, unitId)); }
    /**
     * Nhận DTO đã kiểm tra, gọi service tạo quy đổi đơn vị và trả HTTP 201.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MaterialUnitDto create(@Valid @RequestBody MaterialUnitWriteDto dto) { return service.create(dto); }
    /**
     * Nhận khóa và DTO hợp lệ, chuyển yêu cầu sửa quy đổi đơn vị sang service.
     * @param materialId ID vật tư cần tra cứu.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     */
    @PutMapping("/{materialId}/{unitId}")
    public MaterialUnitDto update(@PathVariable Long materialId, @PathVariable Long unitId, @Valid @RequestBody MaterialUnitWriteDto dto) { return service.update(new MaterialUnitId(materialId, unitId), dto); }
}
