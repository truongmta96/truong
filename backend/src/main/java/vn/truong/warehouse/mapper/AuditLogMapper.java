package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

@Component
public class AuditLogMapper {
    public AuditLogDto toDto(AuditLog entity) {
        if (entity == null) return null;
        return new AuditLogDto(entity.getId(), entity.getActorId(), entity.getEntityType(), entity.getEntityId(), entity.getAction(), entity.getOldData(), entity.getNewData(), entity.getCreatedAt());
    }
}
