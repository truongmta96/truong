package vn.truong.warehouse.controller;
import org.springframework.web.bind.annotation.*;
import vn.truong.warehouse.dto.*;
import vn.truong.warehouse.service.StockViewService;
import java.util.List;
@RestController
@RequestMapping("/api/stock")
public class StockViewController {
    private final StockViewService service;
    public StockViewController(StockViewService service) { this.service=service; }
    @GetMapping("/materials")
    public List<MaterialStockDto> materials(@RequestParam(defaultValue="0") long after, @RequestParam(defaultValue="20") int limit) { return service.findMaterialStock(after, limit); }
    @GetMapping("/trace/{materialId}")
    public List<TraceStockDto> trace(@PathVariable long materialId) { return service.findTraceStock(materialId); }
}
