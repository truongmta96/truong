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
public class InventoryBalanceService {
    private final InventoryBalanceRepository repository;
    private final InventoryBalanceMapper mapper;
    public InventoryBalanceService(InventoryBalanceRepository repository, InventoryBalanceMapper mapper) { this.repository = repository; this.mapper = mapper; }
    public Page<InventoryBalanceDto> findAll(Pageable pageable) { return repository.findAll(pageable).map(mapper::toDto); }
    public InventoryBalanceDto findById(Long id) { return mapper.toDto(require(id)); }
    private InventoryBalance require(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("InventoryBalance", id)); }
}
