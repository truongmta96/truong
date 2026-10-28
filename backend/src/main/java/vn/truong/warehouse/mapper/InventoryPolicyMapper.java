package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

@Component
public class InventoryPolicyMapper {
    public InventoryPolicyDto toDto(InventoryPolicy entity) {
        if (entity == null) return null;
        return new InventoryPolicyDto(entity.getId(), entity.getCostingMethod(), entity.getPostingMode());
    }
}
