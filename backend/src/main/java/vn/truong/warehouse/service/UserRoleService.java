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

/**
 * Xử lý tra cứu và tạo/sửa phân quyền. Các thao tác ghi chạy trong transaction và chuyển kết quả qua mapper.
 */
@Service
@Validated
@Transactional(readOnly = true)
public class UserRoleService {
    private final UserRoleRepository repository;
    private final UserRoleMapper mapper;
    /**
     * Nhận các thành phần phụ thuộc qua hàm tạo để Spring kết nối lớp này với tầng xử lý bên dưới.
     */
    public UserRoleService(UserRoleRepository repository, UserRoleMapper mapper) { this.repository = repository; this.mapper = mapper; }
    /**
     * Đọc danh sách phân quyền theo trang và chuyển từng entity thành DTO. Không tải toàn bộ bảng cùng lúc.
     * @param pageable Thông tin trang, số dòng và thứ tự sắp xếp do bên gọi cung cấp.
     */
    public Page<UserRoleDto> findAll(Pageable pageable) { return repository.findAll(pageable).map(mapper::toDto); }
    /**
     * Tìm phân quyền bằng khóa và trả DTO; báo không tìm thấy nếu ID không tồn tại.
     * @param id Khóa của bản ghi; với bảng liên kết có thể là một khóa gồm nhiều thành phần.
     */
    public UserRoleDto findById(UserRoleId id) { return mapper.toDto(require(id)); }
    /**
     * Lấy entity phân quyền cần thao tác; phát sinh ResourceNotFoundException nếu khóa không tồn tại.
     * @param id Khóa của bản ghi; với bảng liên kết có thể là một khóa gồm nhiều thành phần.
     */
    private UserRole require(UserRoleId id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("UserRole", id)); }
    /**
     * Tạo phân quyền từ DTO, lưu và đẩy lệnh ghi xuống database để kiểm tra các ràng buộc trước khi trả DTO. Từ chối tạo nếu khóa ghép đã tồn tại.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     */
    @Transactional
    public UserRoleDto create(@Valid UserRoleWriteDto dto) { var key = new UserRoleId(dto.userId(), dto.roleId()); if (repository.existsById(key)) throw new IllegalArgumentException("Record already exists"); return mapper.toDto(repository.saveAndFlush(mapper.toEntity(dto))); }
    /**
     * Tìm phân quyền hiện có, chép các trường được phép sửa và lưu trong transaction; không tự tạo bản ghi nếu ID sai. Không cho đổi các thành phần khóa ghép.
     * @param id Khóa của bản ghi; với bảng liên kết có thể là một khóa gồm nhiều thành phần.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     */
    @Transactional
    public UserRoleDto update(UserRoleId id, @Valid UserRoleWriteDto dto) { if (!java.util.Objects.equals(id.userId, dto.userId()) || !java.util.Objects.equals(id.roleId, dto.roleId())) throw new IllegalArgumentException("Composite key cannot be changed"); var entity = require(id); mapper.update(dto, entity); return mapper.toDto(repository.saveAndFlush(entity)); }
}
