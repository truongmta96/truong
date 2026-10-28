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
 * Xử lý tra cứu phân bổ lô / serial. Chỉ cung cấp đọc dữ liệu ở lớp này; không có quy trình duyệt, ghi sổ hoặc tự cập nhật tồn kho.
 */
@Service
@Validated
@Transactional(readOnly = true)
public class StockLineAllocationService {
    private final StockLineAllocationRepository repository;
    private final StockLineAllocationMapper mapper;
    /**
     * Nhận các thành phần phụ thuộc qua hàm tạo để Spring kết nối lớp này với tầng xử lý bên dưới.
     */
    public StockLineAllocationService(StockLineAllocationRepository repository, StockLineAllocationMapper mapper) { this.repository = repository; this.mapper = mapper; }
    /**
     * Đọc danh sách phân bổ lô / serial theo trang và chuyển từng entity thành DTO. Không tải toàn bộ bảng cùng lúc.
     * @param pageable Thông tin trang, số dòng và thứ tự sắp xếp do bên gọi cung cấp.
     */
    public Page<StockLineAllocationDto> findAll(Pageable pageable) { return repository.findAll(pageable).map(mapper::toDto); }
    /**
     * Tìm phân bổ lô / serial bằng khóa và trả DTO; báo không tìm thấy nếu ID không tồn tại.
     * @param id Khóa của bản ghi; với bảng liên kết có thể là một khóa gồm nhiều thành phần.
     */
    public StockLineAllocationDto findById(Long id) { return mapper.toDto(require(id)); }
    /**
     * Lấy entity phân bổ lô / serial cần thao tác; phát sinh ResourceNotFoundException nếu khóa không tồn tại.
     * @param id Khóa của bản ghi; với bảng liên kết có thể là một khóa gồm nhiều thành phần.
     */
    private StockLineAllocation require(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("StockLineAllocation", id)); }
}
