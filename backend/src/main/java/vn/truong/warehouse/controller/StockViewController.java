package vn.truong.warehouse.controller;
import org.springframework.web.bind.annotation.*;
import vn.truong.warehouse.dto.*;
import vn.truong.warehouse.service.StockViewService;
import java.util.List;
/**
 * Nhận yêu cầu HTTP cho các báo cáo tồn kho từ view MySQL và chuyển cho service. Các endpoint của lớp này chỉ phục vụ tra cứu. Quyền truy cập được kiểm tra tập trung tại SecurityConfig.
 */
@RestController
@RequestMapping("/api/stock")
public class StockViewController {
    private final StockViewService service;
    /**
     * Nhận các thành phần phụ thuộc qua hàm tạo để Spring kết nối lớp này với tầng xử lý bên dưới.
     */
    public StockViewController(StockViewService service) { this.service=service; }
    /**
     * Nhận con trỏ after và số dòng limit, trả danh sách tồn tổng hợp theo vật tư.
     * @param after Con trỏ ID của trang trước, dùng cho phân trang theo vật tư.
     * @param limit Số dòng báo cáo tối đa cần đọc.
     */
    @GetMapping("/materials")
    public List<MaterialStockDto> materials(@RequestParam(defaultValue="0") long after, @RequestParam(defaultValue="20") int limit) { return service.findMaterialStock(after, limit); }
    /**
     * Trả danh sách tồn theo lô/serial của ID vật tư trong đường dẫn.
     * @param materialId ID vật tư cần tra cứu.
     */
    @GetMapping("/trace/{materialId}")
    public List<TraceStockDto> trace(@PathVariable long materialId) { return service.findTraceStock(materialId); }
}
