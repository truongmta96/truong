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
 * Xử lý tra cứu và tạo/sửa đơn vị tính. Các thao tác ghi chạy trong transaction và chuyển kết quả qua mapper.
 */
@Service
@Validated
@Transactional(readOnly = true)
public class UnitOfMeasureService {
    private final UnitOfMeasureRepository repository;
    private final UnitOfMeasureMapper mapper;
    /**
     * Nhận các thành phần phụ thuộc qua hàm tạo để Spring kết nối lớp này với tầng xử lý bên dưới.
     */
    public UnitOfMeasureService(UnitOfMeasureRepository repository, UnitOfMeasureMapper mapper) { this.repository = repository; this.mapper = mapper; }
    /**
     * Đọc danh sách đơn vị tính theo trang và chuyển từng entity thành DTO. Không tải toàn bộ bảng cùng lúc.
     * @param pageable Thông tin trang, số dòng và thứ tự sắp xếp do bên gọi cung cấp.
     */
    public Page<UnitOfMeasureDto> findAll(Pageable pageable) { return repository.findAll(pageable).map(mapper::toDto); }
    /**
     * Tìm đơn vị tính bằng khóa và trả DTO; báo không tìm thấy nếu ID không tồn tại.
     * @param id Khóa của bản ghi; với bảng liên kết có thể là một khóa gồm nhiều thành phần.
     */
    public UnitOfMeasureDto findById(Long id) { return mapper.toDto(require(id)); }
    /**
     * Lấy entity đơn vị tính cần thao tác; phát sinh ResourceNotFoundException nếu khóa không tồn tại.
     * @param id Khóa của bản ghi; với bảng liên kết có thể là một khóa gồm nhiều thành phần.
     */
    private UnitOfMeasure require(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("UnitOfMeasure", id)); }
    /**
     * Tạo đơn vị tính từ DTO, lưu và đẩy lệnh ghi xuống database để kiểm tra các ràng buộc trước khi trả DTO.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     */
    @Transactional
    public UnitOfMeasureDto create(@Valid UnitOfMeasureWriteDto dto) { return mapper.toDto(repository.saveAndFlush(mapper.toEntity(dto))); }
    /**
     * Tìm đơn vị tính hiện có, chép các trường được phép sửa và lưu trong transaction; không tự tạo bản ghi nếu ID sai.
     * @param id Khóa của bản ghi; với bảng liên kết có thể là một khóa gồm nhiều thành phần.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     */
    @Transactional
    public UnitOfMeasureDto update(Long id, @Valid UnitOfMeasureWriteDto dto) { var entity = require(id); mapper.update(dto, entity); return mapper.toDto(repository.saveAndFlush(entity)); }
}
