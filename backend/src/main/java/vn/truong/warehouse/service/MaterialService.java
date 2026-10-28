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
public class MaterialService {
    private final MaterialRepository repository;
    private final MaterialMapper mapper;
    private final InventoryBalanceRepository balances;
    public MaterialService(MaterialRepository repository, MaterialMapper mapper, InventoryBalanceRepository balances) { this.repository = repository; this.mapper = mapper; this.balances = balances; }
    public Page<MaterialDto> findAll(Pageable pageable) { return repository.findAll(pageable).map(mapper::toDto); }
    public MaterialDto findById(Long id) { return mapper.toDto(require(id)); }
    private Material require(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Material", id)); }
    @Transactional
    public MaterialDto create(@Valid MaterialWriteDto dto) { var material = repository.saveAndFlush(mapper.toEntity(dto)); var balance = new InventoryBalance(); balance.setMaterialId(material.getId()); balance.setQuantityOnHand(java.math.BigDecimal.ZERO); balance.setInventoryValue(java.math.BigDecimal.ZERO); balances.saveAndFlush(balance); return mapper.toDto(material); }
    @Transactional
    public MaterialDto update(Long id, @Valid MaterialWriteDto dto) { var entity = require(id); mapper.update(dto, entity); return mapper.toDto(repository.saveAndFlush(entity)); }
}
