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
public class RecipientService {
    private final RecipientRepository repository;
    private final RecipientMapper mapper;
    public RecipientService(RecipientRepository repository, RecipientMapper mapper) { this.repository = repository; this.mapper = mapper; }
    public Page<RecipientDto> findAll(Pageable pageable) { return repository.findAll(pageable).map(mapper::toDto); }
    public RecipientDto findById(Long id) { return mapper.toDto(require(id)); }
    private Recipient require(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recipient", id)); }
    @Transactional
    public RecipientDto create(@Valid RecipientWriteDto dto) { return mapper.toDto(repository.saveAndFlush(mapper.toEntity(dto))); }
    @Transactional
    public RecipientDto update(Long id, @Valid RecipientWriteDto dto) { var entity = require(id); mapper.update(dto, entity); return mapper.toDto(repository.saveAndFlush(entity)); }
}
