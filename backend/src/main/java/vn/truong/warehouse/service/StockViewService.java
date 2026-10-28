package vn.truong.warehouse.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.truong.warehouse.dto.*;
import vn.truong.warehouse.repository.StockViewRepository;
import java.util.List;
/**
 * Xử lý tra cứu các báo cáo tồn kho từ view MySQL. Chỉ cung cấp đọc dữ liệu ở lớp này; không có quy trình duyệt, ghi sổ hoặc tự cập nhật tồn kho.
 */
@Service
@Transactional(readOnly = true)
public class StockViewService {
    private final StockViewRepository repository;
    /**
     * Nhận các thành phần phụ thuộc qua hàm tạo để Spring kết nối lớp này với tầng xử lý bên dưới.
     */
    public StockViewService(StockViewRepository repository) { this.repository = repository; }
    /**
     * Kiểm tra con trỏ không âm và giới hạn 1..200 trước khi đọc báo cáo tồn tổng hợp.
     * @param afterMaterialId Chỉ lấy vật tư có ID lớn hơn giá trị này.
     * @param limit Số dòng báo cáo tối đa cần đọc.
     */
    public List<MaterialStockDto> findMaterialStock(long afterMaterialId, int limit) {
        if (afterMaterialId < 0 || limit < 1 || limit > 200) throw new IllegalArgumentException("Invalid cursor or limit (1..200)");
        return repository.findMaterialStock(afterMaterialId, limit);
    }
    /**
     * Trả tồn theo lô/serial của vật tư được chỉ định, không tạo hoặc sửa biến động.
     * @param materialId ID vật tư cần tra cứu.
     */
    public List<TraceStockDto> findTraceStock(long materialId) { return repository.findTraceStock(materialId); }
}
