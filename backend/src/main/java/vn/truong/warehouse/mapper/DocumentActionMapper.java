package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

@Component
public class DocumentActionMapper {
    public DocumentActionDto toDto(DocumentAction entity) {
        if (entity == null) return null;
        return new DocumentActionDto(entity.getId(), entity.getDocumentId(), entity.getRevision(), entity.getAction(), entity.getActorId(), entity.getComment(), entity.getActedAt());
    }
}
