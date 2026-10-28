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
public class MaterialUnitService {
    private final MaterialUnitRepository repository;
    private final MaterialUnitMapper mapper;
    public MaterialUnitService(MaterialUnitRepository repository, MaterialUnitMapper mapper) { this.repository = repository; this.mapper = mapper; }
    public Page<MaterialUnitDto> findAll(Pageable pageable) { return repository.findAll(pageable).map(mapper::toDto); }
    public MaterialUnitDto findById(MaterialUnitId id) { return mapper.toDto(require(id)); }
    private MaterialUnit require(MaterialUnitId id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("MaterialUnit", id)); }
    @Transactional
    public MaterialUnitDto create(@Valid MaterialUnitWriteDto dto) { var key = new MaterialUnitId(dto.materialId(), dto.unitId()); if (repository.existsById(key)) throw new IllegalArgumentException("Record already exists"); return mapper.toDto(repository.saveAndFlush(mapper.toEntity(dto))); }
    @Transactional
    public MaterialUnitDto update(MaterialUnitId id, @Valid MaterialUnitWriteDto dto) { if (!java.util.Objects.equals(id.materialId, dto.materialId()) || !java.util.Objects.equals(id.unitId, dto.unitId())) throw new IllegalArgumentException("Composite key cannot be changed"); var entity = require(id); mapper.update(dto, entity); return mapper.toDto(repository.saveAndFlush(entity)); }
}
