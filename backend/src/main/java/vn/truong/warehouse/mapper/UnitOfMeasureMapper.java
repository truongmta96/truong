package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

@Component
public class UnitOfMeasureMapper {
    public UnitOfMeasureDto toDto(UnitOfMeasure entity) {
        if (entity == null) return null;
        return new UnitOfMeasureDto(entity.getId(), entity.getCode(), entity.getName());
    }
    public UnitOfMeasure toEntity(UnitOfMeasureWriteDto dto) { var entity = new UnitOfMeasure(); update(dto, entity); return entity; }
    public void update(UnitOfMeasureWriteDto dto, UnitOfMeasure entity) {
        entity.setCode(dto.code());
        entity.setName(dto.name());
    }
}
