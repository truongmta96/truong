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
public class AppRoleService {
    private final AppRoleRepository repository;
    private final AppRoleMapper mapper;
    public AppRoleService(AppRoleRepository repository, AppRoleMapper mapper) { this.repository = repository; this.mapper = mapper; }
    public Page<AppRoleDto> findAll(Pageable pageable) { return repository.findAll(pageable).map(mapper::toDto); }
    public AppRoleDto findById(Long id) { return mapper.toDto(require(id)); }
    private AppRole require(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("AppRole", id)); }
    @Transactional
    public AppRoleDto create(@Valid AppRoleWriteDto dto) { return mapper.toDto(repository.saveAndFlush(mapper.toEntity(dto))); }
    @Transactional
    public AppRoleDto update(Long id, @Valid AppRoleWriteDto dto) { var entity = require(id); mapper.update(dto, entity); return mapper.toDto(repository.saveAndFlush(entity)); }
}
