package vn.truong.warehouse.repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import vn.truong.warehouse.dto.*;
@Repository
public class StockViewRepository {
    private final JdbcTemplate jdbc;
    public StockViewRepository(JdbcTemplate jdbc) { this.jdbc = jdbc; }
    public List<MaterialStockDto> findMaterialStock(long afterMaterialId, int limit) {
        return jdbc.query("SELECT * FROM v_material_stock WHERE material_id > ? ORDER BY material_id LIMIT ?",
            (r, n) -> new MaterialStockDto(r.getLong("material_id"), r.getString("code"), r.getString("name"), r.getLong("base_unit_id"), r.getBigDecimal("quantity_on_hand"), r.getBigDecimal("inventory_value")), afterMaterialId, limit);
    }
    public List<TraceStockDto> findTraceStock(long materialId) {
        return jdbc.query("SELECT * FROM v_trace_stock WHERE material_id = ? ORDER BY lot_id, serial_id",
            (r, n) -> new TraceStockDto(r.getLong("material_id"), r.getObject("lot_id", Long.class), r.getObject("serial_id", Long.class), r.getBigDecimal("quantity_on_hand")), materialId);
    }
}
