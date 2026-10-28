package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

@Component
public class MaterialMapper {
    public MaterialDto toDto(Material entity) {
        if (entity == null) return null;
        return new MaterialDto(entity.getId(), entity.getCode(), entity.getName(), entity.getCategoryId(), entity.getBaseUnitId(), entity.getTrackLot(), entity.getTrackSerial(), entity.getTrackExpiry(), entity.getAllowFractionalQuantity(), entity.getMinStock(), entity.getIsActive(), entity.getCreatedAt());
    }
    public Material toEntity(MaterialWriteDto dto) { var entity = new Material(); update(dto, entity); return entity; }
    public void update(MaterialWriteDto dto, Material entity) {
        entity.setCode(dto.code());
        entity.setName(dto.name());
        entity.setCategoryId(dto.categoryId());
        entity.setBaseUnitId(dto.baseUnitId());
        entity.setTrackLot(dto.trackLot());
        entity.setTrackSerial(dto.trackSerial());
        entity.setTrackExpiry(dto.trackExpiry());
        entity.setAllowFractionalQuantity(dto.allowFractionalQuantity());
        entity.setMinStock(dto.minStock());
        entity.setIsActive(dto.isActive());
    }
}
