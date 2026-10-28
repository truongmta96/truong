package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

@Component
public class StockMovementMapper {
    public StockMovementDto toDto(StockMovement entity) {
        if (entity == null) return null;
        return new StockMovementDto(entity.getId(), entity.getAllocationId(), entity.getMaterialId(), entity.getDirection(), entity.getBaseQuantity(), entity.getUnitCost(), entity.getValueAmount(), entity.getCostingMethod(), entity.getPostedAt(), entity.getPostedBy());
    }
}
