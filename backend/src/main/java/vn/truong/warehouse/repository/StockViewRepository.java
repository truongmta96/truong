package vn.truong.warehouse.repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import vn.truong.warehouse.dto.*;
/**
 * Đọc hai view báo cáo tồn kho bằng JDBC; không gán ID giả cho các dòng có lô/serial NULL.
 */
@Repository
public class StockViewRepository {
    private final JdbcTemplate jdbc;
    /**
     * Nhận các thành phần phụ thuộc qua hàm tạo để Spring kết nối lớp này với tầng xử lý bên dưới.
     */
    public StockViewRepository(JdbcTemplate jdbc) { this.jdbc = jdbc; }
    /**
     * Đọc view v_material_stock với điều kiện ID vật tư lớn hơn con trỏ và giới hạn số dòng; đây là phân trang theo ID, không theo số trang.
     * @param afterMaterialId Chỉ lấy vật tư có ID lớn hơn giá trị này.
     * @param limit Số dòng báo cáo tối đa cần đọc.
     */
    public List<MaterialStockDto> findMaterialStock(long afterMaterialId, int limit) {
        return jdbc.query("SELECT * FROM v_material_stock WHERE material_id > ? ORDER BY material_id LIMIT ?",
            (r, n) -> new MaterialStockDto(r.getLong("material_id"), r.getString("code"), r.getString("name"), r.getLong("base_unit_id"), r.getBigDecimal("quantity_on_hand"), r.getBigDecimal("inventory_value")), afterMaterialId, limit);
    }
    /**
     * Đọc view v_trace_stock cho một vật tư, giữ giá trị NULL của lô/serial vì vật tư có thể không theo dõi hai thuộc tính này.
     * @param materialId ID vật tư cần tra cứu.
     */
    public List<TraceStockDto> findTraceStock(long materialId) {
        return jdbc.query("SELECT * FROM v_trace_stock WHERE material_id = ? ORDER BY lot_id, serial_id",
            (r, n) -> new TraceStockDto(r.getLong("material_id"), r.getObject("lot_id", Long.class), r.getObject("serial_id", Long.class), r.getBigDecimal("quantity_on_hand")), materialId);
    }
}
