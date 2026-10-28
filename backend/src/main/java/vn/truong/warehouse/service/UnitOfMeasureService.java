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
public class UnitOfMeasureService {
    private final UnitOfMeasureRepository repository;
    private final UnitOfMeasureMapper mapper;
    public UnitOfMeasureService(UnitOfMeasureRepository repository, UnitOfMeasureMapper mapper) { this.repository = repository; this.mapper = mapper; }
    public Page<UnitOfMeasureDto> findAll(Pageable pageable) { return repository.findAll(pageable).map(mapper::toDto); }
    public UnitOfMeasureDto findById(Long id) { return mapper.toDto(require(id)); }
    private UnitOfMeasure require(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("UnitOfMeasure", id)); }
    @Transactional
    public UnitOfMeasureDto create(@Valid UnitOfMeasureWriteDto dto) { return mapper.toDto(repository.saveAndFlush(mapper.toEntity(dto))); }
    @Transactional
    public UnitOfMeasureDto update(Long id, @Valid UnitOfMeasureWriteDto dto) { var entity = require(id); mapper.update(dto, entity); return mapper.toDto(repository.saveAndFlush(entity)); }
}
