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
public class SupplierService {
    private final SupplierRepository repository;
    private final SupplierMapper mapper;
    public SupplierService(SupplierRepository repository, SupplierMapper mapper) { this.repository = repository; this.mapper = mapper; }
    public Page<SupplierDto> findAll(Pageable pageable) { return repository.findAll(pageable).map(mapper::toDto); }
    public SupplierDto findById(Long id) { return mapper.toDto(require(id)); }
    private Supplier require(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier", id)); }
    @Transactional
    public SupplierDto create(@Valid SupplierWriteDto dto) { return mapper.toDto(repository.saveAndFlush(mapper.toEntity(dto))); }
    @Transactional
    public SupplierDto update(Long id, @Valid SupplierWriteDto dto) { var entity = require(id); mapper.update(dto, entity); return mapper.toDto(repository.saveAndFlush(entity)); }
}
