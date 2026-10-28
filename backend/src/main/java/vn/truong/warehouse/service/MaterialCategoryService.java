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
public class MaterialCategoryService {
    private final MaterialCategoryRepository repository;
    private final MaterialCategoryMapper mapper;
    public MaterialCategoryService(MaterialCategoryRepository repository, MaterialCategoryMapper mapper) { this.repository = repository; this.mapper = mapper; }
    public Page<MaterialCategoryDto> findAll(Pageable pageable) { return repository.findAll(pageable).map(mapper::toDto); }
    public MaterialCategoryDto findById(Long id) { return mapper.toDto(require(id)); }
    private MaterialCategory require(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("MaterialCategory", id)); }
    @Transactional
    public MaterialCategoryDto create(@Valid MaterialCategoryWriteDto dto) { return mapper.toDto(repository.saveAndFlush(mapper.toEntity(dto))); }
    @Transactional
    public MaterialCategoryDto update(Long id, @Valid MaterialCategoryWriteDto dto) { var entity = require(id); mapper.update(dto, entity); return mapper.toDto(repository.saveAndFlush(entity)); }
}
