package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

@Component
public class CostAllocationMapper {
    public CostAllocationDto toDto(CostAllocation entity) {
        if (entity == null) return null;
        return new CostAllocationDto(entity.getId(), entity.getIssueMovementId(), entity.getReceiptMovementId(), entity.getBaseQuantity(), entity.getValueAmount());
    }
}
