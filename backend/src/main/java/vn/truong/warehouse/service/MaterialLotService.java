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
public class MaterialLotService {
    private final MaterialLotRepository repository;
    private final MaterialLotMapper mapper;
    public MaterialLotService(MaterialLotRepository repository, MaterialLotMapper mapper) { this.repository = repository; this.mapper = mapper; }
    public Page<MaterialLotDto> findAll(Pageable pageable) { return repository.findAll(pageable).map(mapper::toDto); }
    public MaterialLotDto findById(Long id) { return mapper.toDto(require(id)); }
    private MaterialLot require(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("MaterialLot", id)); }
    @Transactional
    public MaterialLotDto create(@Valid MaterialLotWriteDto dto) { return mapper.toDto(repository.saveAndFlush(mapper.toEntity(dto))); }
    @Transactional
    public MaterialLotDto update(Long id, @Valid MaterialLotWriteDto dto) { var entity = require(id); mapper.update(dto, entity); return mapper.toDto(repository.saveAndFlush(entity)); }
}
