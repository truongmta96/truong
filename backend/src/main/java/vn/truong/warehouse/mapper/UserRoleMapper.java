package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

@Component
public class UserRoleMapper {
    public UserRoleDto toDto(UserRole entity) {
        if (entity == null) return null;
        return new UserRoleDto(entity.getUserId(), entity.getRoleId());
    }
    public UserRole toEntity(UserRoleWriteDto dto) { var entity = new UserRole(); update(dto, entity); entity.setUserId(dto.userId()); entity.setRoleId(dto.roleId()); return entity; }
    public void update(UserRoleWriteDto dto, UserRole entity) {
    }
}
