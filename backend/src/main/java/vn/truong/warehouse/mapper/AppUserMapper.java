package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

@Component
public class AppUserMapper {
    public AppUserDto toDto(AppUser entity) {
        if (entity == null) return null;
        return new AppUserDto(entity.getId(), entity.getUsername(), entity.getFullName(), entity.getIsActive(), entity.getCreatedAt());
    }
}
