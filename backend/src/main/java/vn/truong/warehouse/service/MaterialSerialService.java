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
public class MaterialSerialService {
    private final MaterialSerialRepository repository;
    private final MaterialSerialMapper mapper;
    public MaterialSerialService(MaterialSerialRepository repository, MaterialSerialMapper mapper) { this.repository = repository; this.mapper = mapper; }
    public Page<MaterialSerialDto> findAll(Pageable pageable) { return repository.findAll(pageable).map(mapper::toDto); }
    public MaterialSerialDto findById(Long id) { return mapper.toDto(require(id)); }
    private MaterialSerial require(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("MaterialSerial", id)); }
    @Transactional
    public MaterialSerialDto create(@Valid MaterialSerialWriteDto dto) { return mapper.toDto(repository.saveAndFlush(mapper.toEntity(dto))); }
    @Transactional
    public MaterialSerialDto update(Long id, @Valid MaterialSerialWriteDto dto) { var entity = require(id); mapper.update(dto, entity); return mapper.toDto(repository.saveAndFlush(entity)); }
}
