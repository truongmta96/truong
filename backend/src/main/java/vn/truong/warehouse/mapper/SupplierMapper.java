package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

@Component
public class SupplierMapper {
    public SupplierDto toDto(Supplier entity) {
        if (entity == null) return null;
        return new SupplierDto(entity.getId(), entity.getCode(), entity.getName(), entity.getPhone(), entity.getAddress(), entity.getTaxCode(), entity.getIsActive());
    }
    public Supplier toEntity(SupplierWriteDto dto) { var entity = new Supplier(); update(dto, entity); return entity; }
    public void update(SupplierWriteDto dto, Supplier entity) {
        entity.setCode(dto.code());
        entity.setName(dto.name());
        entity.setPhone(dto.phone());
        entity.setAddress(dto.address());
        entity.setTaxCode(dto.taxCode());
        entity.setIsActive(dto.isActive());
    }
}
