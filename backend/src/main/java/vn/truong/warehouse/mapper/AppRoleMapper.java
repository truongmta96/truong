package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

@Component
public class AppRoleMapper {
    public AppRoleDto toDto(AppRole entity) {
        if (entity == null) return null;
        return new AppRoleDto(entity.getId(), entity.getCode(), entity.getName());
    }
    public AppRole toEntity(AppRoleWriteDto dto) { var entity = new AppRole(); update(dto, entity); return entity; }
    public void update(AppRoleWriteDto dto, AppRole entity) {
        entity.setCode(dto.code());
        entity.setName(dto.name());
    }
}
