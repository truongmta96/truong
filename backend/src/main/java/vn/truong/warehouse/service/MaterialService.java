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
 * Xử lý tra cứu và tạo/sửa vật tư. Các thao tác ghi chạy trong transaction và chuyển kết quả qua mapper. Tạo vật tư đồng thời khởi tạo số dư bằng 0 trong cùng transaction.
 */
@Service
@Validated
@Transactional(readOnly = true)
public class MaterialService {
    private final MaterialRepository repository;
    private final MaterialMapper mapper;
    private final InventoryBalanceRepository balances;
    /**
     * Nhận các thành phần phụ thuộc qua hàm tạo để Spring kết nối lớp này với tầng xử lý bên dưới.
     */
    public MaterialService(MaterialRepository repository, MaterialMapper mapper, InventoryBalanceRepository balances) { this.repository = repository; this.mapper = mapper; this.balances = balances; }
    /**
     * Đọc danh sách vật tư theo trang và chuyển từng entity thành DTO. Không tải toàn bộ bảng cùng lúc.
     * @param pageable Thông tin trang, số dòng và thứ tự sắp xếp do bên gọi cung cấp.
     */
    public Page<MaterialDto> findAll(Pageable pageable) { return repository.findAll(pageable).map(mapper::toDto); }
    /**
     * Tìm vật tư bằng khóa và trả DTO; báo không tìm thấy nếu ID không tồn tại.
     * @param id Khóa của bản ghi; với bảng liên kết có thể là một khóa gồm nhiều thành phần.
     */
    public MaterialDto findById(Long id) { return mapper.toDto(require(id)); }
    /**
     * Lấy entity vật tư cần thao tác; phát sinh ResourceNotFoundException nếu khóa không tồn tại.
     * @param id Khóa của bản ghi; với bảng liên kết có thể là một khóa gồm nhiều thành phần.
     */
    private Material require(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Material", id)); }
    /**
     * Tạo vật tư từ DTO, lưu và đẩy lệnh ghi xuống database để kiểm tra các ràng buộc trước khi trả DTO. Khởi tạo InventoryBalance bằng 0 cùng transaction; không tạo phiếu hay biến động kho.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     */
    @Transactional
    public MaterialDto create(@Valid MaterialWriteDto dto) { var material = repository.saveAndFlush(mapper.toEntity(dto)); var balance = new InventoryBalance(); balance.setMaterialId(material.getId()); balance.setQuantityOnHand(java.math.BigDecimal.ZERO); balance.setInventoryValue(java.math.BigDecimal.ZERO); balances.saveAndFlush(balance); return mapper.toDto(material); }
    /**
     * Tìm vật tư hiện có, chép các trường được phép sửa và lưu trong transaction; không tự tạo bản ghi nếu ID sai.
     * @param id Khóa của bản ghi; với bảng liên kết có thể là một khóa gồm nhiều thành phần.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     */
    @Transactional
    public MaterialDto update(Long id, @Valid MaterialWriteDto dto) { var entity = require(id); mapper.update(dto, entity); return mapper.toDto(repository.saveAndFlush(entity)); }
}
