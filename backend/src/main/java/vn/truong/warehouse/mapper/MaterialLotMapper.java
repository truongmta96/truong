package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

@Component
public class MaterialLotMapper {
    public MaterialLotDto toDto(MaterialLot entity) {
        if (entity == null) return null;
        return new MaterialLotDto(entity.getId(), entity.getMaterialId(), entity.getLotCode(), entity.getManufacturedOn(), entity.getExpiresOn());
    }
    public MaterialLot toEntity(MaterialLotWriteDto dto) { var entity = new MaterialLot(); update(dto, entity); return entity; }
    public void update(MaterialLotWriteDto dto, MaterialLot entity) {
        entity.setMaterialId(dto.materialId());
        entity.setLotCode(dto.lotCode());
        entity.setManufacturedOn(dto.manufacturedOn());
        entity.setExpiresOn(dto.expiresOn());
    }
}
