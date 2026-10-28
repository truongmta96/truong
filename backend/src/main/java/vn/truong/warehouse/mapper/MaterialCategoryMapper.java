package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

@Component
public class MaterialCategoryMapper {
    public MaterialCategoryDto toDto(MaterialCategory entity) {
        if (entity == null) return null;
        return new MaterialCategoryDto(entity.getId(), entity.getCode(), entity.getName());
    }
    public MaterialCategory toEntity(MaterialCategoryWriteDto dto) { var entity = new MaterialCategory(); update(dto, entity); return entity; }
    public void update(MaterialCategoryWriteDto dto, MaterialCategory entity) {
        entity.setCode(dto.code());
        entity.setName(dto.name());
    }
}
