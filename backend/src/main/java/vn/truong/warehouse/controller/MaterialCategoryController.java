package vn.truong.warehouse.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.*;
import jakarta.validation.Valid;
import vn.truong.warehouse.dto.*;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.service.*;

@RestController
@RequestMapping("/api/categories")
public class MaterialCategoryController {
    private final MaterialCategoryService service;
    public MaterialCategoryController(MaterialCategoryService service) { this.service = service; }
    @GetMapping
    public PageResponse<MaterialCategoryDto> list(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="20") int size) {
        if (page < 0 || size < 1 || size > 200) throw new IllegalArgumentException("Trang không hợp lệ; kích thước phải từ 1 đến 200.");
        return new PageResponse<>(service.findAll(PageRequest.of(page, size, Sort.by("id"))));
    }
    @GetMapping("/{id}")
    public MaterialCategoryDto get(@PathVariable Long id) { return service.findById(id); }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MaterialCategoryDto create(@Valid @RequestBody MaterialCategoryWriteDto dto) { return service.create(dto); }
    @PutMapping("/{id}")
    public MaterialCategoryDto update(@PathVariable Long id, @Valid @RequestBody MaterialCategoryWriteDto dto) { return service.update(id, dto); }
}
