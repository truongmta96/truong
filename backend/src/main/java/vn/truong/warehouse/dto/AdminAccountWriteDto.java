package vn.truong.warehouse.dto;

import jakarta.validation.constraints.*;

/**
 * Những thông tin ADMIN được sửa trên tài khoản không có quyền ADMIN.
 * Không khai báo ID, tên đăng nhập, mật khẩu hay vai trò để tránh sửa vượt phạm vi.
 * @param fullName Họ tên hiển thị mới của người dùng.
 * @param isActive Cho phép hoặc khóa hoạt động của tài khoản.
 */
public record AdminAccountWriteDto(
    @NotBlank @Size(max=200) String fullName,
    @NotNull Boolean isActive
) {}
