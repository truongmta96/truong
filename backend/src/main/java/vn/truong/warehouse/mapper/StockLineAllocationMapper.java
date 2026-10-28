package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

@Component
public class StockLineAllocationMapper {
    public StockLineAllocationDto toDto(StockLineAllocation entity) {
        if (entity == null) return null;
        return new StockLineAllocationDto(entity.getId(), entity.getLineId(), entity.getMaterialId(), entity.getLotId(), entity.getSerialId(), entity.getBaseQuantity());
    }
}
