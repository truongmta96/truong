package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

@Component
public class StockDocumentMapper {
    public StockDocumentDto toDto(StockDocument entity) {
        if (entity == null) return null;
        return new StockDocumentDto(entity.getId(), entity.getDocumentNo(), entity.getDocumentType(), entity.getStatus(), entity.getDocumentDate(), entity.getSupplierId(), entity.getRecipientId(), entity.getRecipientNameSnapshot(), entity.getRecipientDepartmentSnapshot(), entity.getReason(), entity.getCreatedBy(), entity.getSubmittedAt(), entity.getApprovedBy(), entity.getApprovedAt(), entity.getPostedBy(), entity.getPostedAt(), entity.getRevision(), entity.getCreatedAt(), entity.getUpdatedAt());
    }
}
