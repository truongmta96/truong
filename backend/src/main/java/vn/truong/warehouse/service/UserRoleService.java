package vn.truong.warehouse.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;
import vn.truong.warehouse.repository.*;
import vn.truong.warehouse.mapper.*;
import vn.truong.warehouse.exception.ResourceNotFoundException;

@Service
@Validated
@Transactional(readOnly = true)
public class UserRoleService {
    private final UserRoleRepository repository;
    private final UserRoleMapper mapper;
    public UserRoleService(UserRoleRepository repository, UserRoleMapper mapper) { this.repository = repository; this.mapper = mapper; }
    public Page<UserRoleDto> findAll(Pageable pageable) { return repository.findAll(pageable).map(mapper::toDto); }
    public UserRoleDto findById(UserRoleId id) { return mapper.toDto(require(id)); }
    private UserRole require(UserRoleId id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("UserRole", id)); }
    @Transactional
    public UserRoleDto create(@Valid UserRoleWriteDto dto) { var key = new UserRoleId(dto.userId(), dto.roleId()); if (repository.existsById(key)) throw new IllegalArgumentException("Record already exists"); return mapper.toDto(repository.saveAndFlush(mapper.toEntity(dto))); }
    @Transactional
    public UserRoleDto update(UserRoleId id, @Valid UserRoleWriteDto dto) { if (!java.util.Objects.equals(id.userId, dto.userId()) || !java.util.Objects.equals(id.roleId, dto.roleId())) throw new IllegalArgumentException("Composite key cannot be changed"); var entity = require(id); mapper.update(dto, entity); return mapper.toDto(repository.saveAndFlush(entity)); }
}
