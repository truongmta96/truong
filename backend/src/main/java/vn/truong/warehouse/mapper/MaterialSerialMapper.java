package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

@Component
public class MaterialSerialMapper {
    public MaterialSerialDto toDto(MaterialSerial entity) {
        if (entity == null) return null;
        return new MaterialSerialDto(entity.getId(), entity.getMaterialId(), entity.getLotId(), entity.getSerialNumber(), entity.getExpiresOn());
    }
    public MaterialSerial toEntity(MaterialSerialWriteDto dto) { var entity = new MaterialSerial(); update(dto, entity); return entity; }
    public void update(MaterialSerialWriteDto dto, MaterialSerial entity) {
        entity.setMaterialId(dto.materialId());
        entity.setLotId(dto.lotId());
        entity.setSerialNumber(dto.serialNumber());
        entity.setExpiresOn(dto.expiresOn());
    }
}
