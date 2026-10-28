package vn.truong.warehouse.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.*;
import jakarta.validation.Valid;
import vn.truong.warehouse.dto.*;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.service.*;

@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {
    private final UserRoleService service;
    public UserRoleController(UserRoleService service) { this.service = service; }
    @GetMapping
    public PageResponse<UserRoleDto> list(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="20") int size) {
        if (page < 0 || size < 1 || size > 200) throw new IllegalArgumentException("Trang không hợp lệ; kích thước phải từ 1 đến 200.");
        return new PageResponse<>(service.findAll(PageRequest.of(page, size, Sort.by("userId"))));
    }
    @GetMapping("/{userId}/{roleId}")
    public UserRoleDto get(@PathVariable Long userId, @PathVariable Long roleId) { return service.findById(new UserRoleId(userId, roleId)); }
}
