package vn.truong.warehouse.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.*;
import jakarta.validation.Valid;
import vn.truong.warehouse.dto.*;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.service.*;

@RestController
@RequestMapping("/api/material-units")
public class MaterialUnitController {
    private final MaterialUnitService service;
    public MaterialUnitController(MaterialUnitService service) { this.service = service; }
    @GetMapping
    public PageResponse<MaterialUnitDto> list(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="20") int size) {
        if (page < 0 || size < 1 || size > 200) throw new IllegalArgumentException("Trang không hợp lệ; kích thước phải từ 1 đến 200.");
        return new PageResponse<>(service.findAll(PageRequest.of(page, size, Sort.by("materialId"))));
    }
    @GetMapping("/{materialId}/{unitId}")
    public MaterialUnitDto get(@PathVariable Long materialId, @PathVariable Long unitId) { return service.findById(new MaterialUnitId(materialId, unitId)); }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MaterialUnitDto create(@Valid @RequestBody MaterialUnitWriteDto dto) { return service.create(dto); }
    @PutMapping("/{materialId}/{unitId}")
    public MaterialUnitDto update(@PathVariable Long materialId, @PathVariable Long unitId, @Valid @RequestBody MaterialUnitWriteDto dto) { return service.update(new MaterialUnitId(materialId, unitId), dto); }
}
