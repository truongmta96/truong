package vn.truong.warehouse.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.*;
import jakarta.validation.Valid;
import vn.truong.warehouse.dto.*;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.service.*;

@RestController
@RequestMapping("/api/audit-logs")
public class AuditLogController {
    private final AuditLogService service;
    public AuditLogController(AuditLogService service) { this.service = service; }
    @GetMapping
    public PageResponse<AuditLogDto> list(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="20") int size) {
        if (page < 0 || size < 1 || size > 200) throw new IllegalArgumentException("Trang không hợp lệ; kích thước phải từ 1 đến 200.");
        return new PageResponse<>(service.findAll(PageRequest.of(page, size, Sort.by("id"))).map(this::redact));
    }
    @GetMapping("/{id}")
    public AuditLogDto get(@PathVariable Long id) { return redact(service.findById(id)); }
    private AuditLogDto redact(AuditLogDto row) {
        return new AuditLogDto(row.id(), row.actorId(), row.entityType(), row.entityId(), row.action(), null, null, row.createdAt());
    }
}
