package vn.truong.warehouse.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final JdbcTemplate jdbc;
    public DashboardController(JdbcTemplate jdbc) { this.jdbc = jdbc; }
    @GetMapping
    @Transactional(readOnly=true)
    public Map<String,Object> summary() {
        return Map.of(
            "materials", jdbc.queryForObject("SELECT count(*) FROM material", Long.class),
            "suppliers", jdbc.queryForObject("SELECT count(*) FROM supplier", Long.class),
            "pending", jdbc.queryForObject("SELECT count(*) FROM stock_document WHERE status='PENDING'", Long.class),
            "lowStock", jdbc.queryForObject("SELECT count(*) FROM material m JOIN v_material_stock s ON m.id=s.material_id WHERE m.is_active=1 AND s.quantity_on_hand < m.min_stock", Long.class),
            "inventoryValue", jdbc.queryForObject("SELECT COALESCE(SUM(inventory_value),0) FROM v_material_stock", java.math.BigDecimal.class),
            "recentDocuments", jdbc.queryForList("SELECT id, document_no, document_type, status, document_date FROM stock_document ORDER BY created_at DESC, id DESC LIMIT 5"));
    }
}
