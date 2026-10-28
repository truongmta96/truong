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
 * Xử lý tra cứu lịch sử phiếu. Chỉ cung cấp đọc dữ liệu ở lớp này; không có quy trình duyệt, ghi sổ hoặc tự cập nhật tồn kho.
 */
@Service
@Validated
@Transactional(readOnly = true)
public class DocumentActionService {
    private final DocumentActionRepository repository;
    private final DocumentActionMapper mapper;
    /**
     * Nhận các thành phần phụ thuộc qua hàm tạo để Spring kết nối lớp này với tầng xử lý bên dưới.
     */
    public DocumentActionService(DocumentActionRepository repository, DocumentActionMapper mapper) { this.repository = repository; this.mapper = mapper; }
    /**
     * Đọc danh sách lịch sử phiếu theo trang và chuyển từng entity thành DTO. Không tải toàn bộ bảng cùng lúc.
     * @param pageable Thông tin trang, số dòng và thứ tự sắp xếp do bên gọi cung cấp.
     */
    public Page<DocumentActionDto> findAll(Pageable pageable) { return repository.findAll(pageable).map(mapper::toDto); }
    /**
     * Tìm lịch sử phiếu bằng khóa và trả DTO; báo không tìm thấy nếu ID không tồn tại.
     * @param id Khóa của bản ghi; với bảng liên kết có thể là một khóa gồm nhiều thành phần.
     */
    public DocumentActionDto findById(Long id) { return mapper.toDto(require(id)); }
    /**
     * Lấy entity lịch sử phiếu cần thao tác; phát sinh ResourceNotFoundException nếu khóa không tồn tại.
     * @param id Khóa của bản ghi; với bảng liên kết có thể là một khóa gồm nhiều thành phần.
     */
    private DocumentAction require(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DocumentAction", id)); }
}
