package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

@Component
public class MaterialUnitMapper {
    public MaterialUnitDto toDto(MaterialUnit entity) {
        if (entity == null) return null;
        return new MaterialUnitDto(entity.getMaterialId(), entity.getUnitId(), entity.getConversionFactor(), entity.getAllowReceipt(), entity.getAllowIssue());
    }
    public MaterialUnit toEntity(MaterialUnitWriteDto dto) { var entity = new MaterialUnit(); update(dto, entity); entity.setMaterialId(dto.materialId()); entity.setUnitId(dto.unitId()); return entity; }
    public void update(MaterialUnitWriteDto dto, MaterialUnit entity) {
        entity.setConversionFactor(dto.conversionFactor());
        entity.setAllowReceipt(dto.allowReceipt());
        entity.setAllowIssue(dto.allowIssue());
    }
}
