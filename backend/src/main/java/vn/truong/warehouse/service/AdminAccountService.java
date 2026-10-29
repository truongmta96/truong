package vn.truong.warehouse.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.data.domain.*;
import vn.truong.warehouse.auth.AuthService;
import vn.truong.warehouse.repository.AppUserRepository;
import vn.truong.warehouse.mapper.AppUserMapper;
import vn.truong.warehouse.dto.*;
import vn.truong.warehouse.entity.AppUser;
import vn.truong.warehouse.exception.ResourceNotFoundException;

/**
 * Cho ADMIN xem và chỉnh sửa tài khoản không có quyền ADMIN.
 * Kiểm tra cả ở tầng service để không phụ thuộc vào việc giao diện có ẩn nút hay không.
 * Tài khoản ADMIN (kể cả chính người đang đăng nhập) dùng trang cá nhân cho việc tự sửa hồ sơ.
 */
@Service
@PreAuthorize("hasRole('ADMIN')")
@Transactional(readOnly=true)
public class AdminAccountService {
    private final AppUserRepository users;
    private final AppUserMapper mapper;
    private final AuthService accounts;

    /** Nhận các thành phần truy cập dữ liệu, chuyển đổi DTO và kiểm tra vai trò. */
    public AdminAccountService(AppUserRepository users, AppUserMapper mapper, AuthService accounts) {
        this.users=users;this.mapper=mapper;this.accounts=accounts;
    }

    /** Lọc ADMIN ngay trong truy vấn, giữ tổng số bản ghi và phân trang nhất quán. */
    public Page<AppUserDto> findAll(Pageable pageable) {
        return users.findNonAdmins(pageable).map(mapper::toDto);
    }

    /** Kiểm tra vai trò của tài khoản đích, kể cả khi tài khoản có nhiều vai trò hoặc đang bị khóa. */
    private void ensureManageable(AppUser user) {
        if(accounts.roles(user.getId()).contains("ADMIN")) throw new AccessDeniedException("Protected admin account");
    }

    /** Đọc chi tiết tài khoản thường; không cho lách kiểm tra bằng cách nhập trực tiếp ID của ADMIN. */
    public AppUserDto findById(Long id) {
        var user=users.findById(id).orElseThrow(()->new ResourceNotFoundException("AppUser",id));
        ensureManageable(user);
        return mapper.toDto(user);
    }

    /**
     * Khóa dòng tài khoản và kiểm tra lại vai trò trước khi ghi trong cùng transaction.
     * Chỉ đổi họ tên/trạng thái; giữ nguyên username, passwordHash và liên kết user_role.
     * Bộ lọc phiên hiện có sẽ từ chối các phiên của tài khoản bị khóa ở yêu cầu tiếp theo.
     */
    @Transactional
    public AppUserDto update(Long id, AdminAccountWriteDto dto) {
        var user=users.findForUpdate(id).orElseThrow(()->new ResourceNotFoundException("AppUser",id));
        ensureManageable(user);
        user.setFullName(dto.fullName().strip());
        user.setIsActive(dto.isActive());
        return mapper.toDto(users.saveAndFlush(user));
    }
}
