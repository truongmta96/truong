package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

@Component
public class RecipientMapper {
    public RecipientDto toDto(Recipient entity) {
        if (entity == null) return null;
        return new RecipientDto(entity.getId(), entity.getCode(), entity.getFullName(), entity.getDepartment(), entity.getPhone(), entity.getIsActive());
    }
    public Recipient toEntity(RecipientWriteDto dto) { var entity = new Recipient(); update(dto, entity); return entity; }
    public void update(RecipientWriteDto dto, Recipient entity) {
        entity.setCode(dto.code());
        entity.setFullName(dto.fullName());
        entity.setDepartment(dto.department());
        entity.setPhone(dto.phone());
        entity.setIsActive(dto.isActive());
    }
}
