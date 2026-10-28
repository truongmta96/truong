package vn.truong.warehouse.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.truong.warehouse.dto.*;
import vn.truong.warehouse.repository.StockViewRepository;
import java.util.List;
@Service
@Transactional(readOnly = true)
public class StockViewService {
    private final StockViewRepository repository;
    public StockViewService(StockViewRepository repository) { this.repository = repository; }
    public List<MaterialStockDto> findMaterialStock(long afterMaterialId, int limit) {
        if (afterMaterialId < 0 || limit < 1 || limit > 200) throw new IllegalArgumentException("Invalid cursor or limit (1..200)");
        return repository.findMaterialStock(afterMaterialId, limit);
    }
    public List<TraceStockDto> findTraceStock(long materialId) { return repository.findTraceStock(materialId); }
}
