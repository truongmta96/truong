package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

@Component
public class InventoryBalanceMapper {
    public InventoryBalanceDto toDto(InventoryBalance entity) {
        if (entity == null) return null;
        return new InventoryBalanceDto(entity.getMaterialId(), entity.getQuantityOnHand(), entity.getInventoryValue(), entity.getVersion(), entity.getUpdatedAt());
    }
}
