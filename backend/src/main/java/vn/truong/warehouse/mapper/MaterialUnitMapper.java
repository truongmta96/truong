package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

/**
 * Chuyển đổi giữa entity và DTO của quy đổi đơn vị. Lớp này chỉ sao chép dữ liệu, không truy vấn database hoặc tự kiểm tra nghiệp vụ.
 */
@Component
public class MaterialUnitMapper {
    /**
     * Chuyển một entity quy đổi đơn vị thành dữ liệu trả về giao diện; trả null nếu đầu vào là null.
     * @param entity Đối tượng lưu trữ đang được đọc hoặc cập nhật trong bộ nhớ.
     */
    public MaterialUnitDto toDto(MaterialUnit entity) {
        if (entity == null) return null;
        return new MaterialUnitDto(entity.getMaterialId(), entity.getUnitId(), entity.getConversionFactor(), entity.getAllowReceipt(), entity.getAllowIssue());
    }
    /**
     * Tạo entity quy đổi đơn vị mới từ dữ liệu ghi. Chưa lưu database; ID tự sinh và trường do database tính không được sao chép từ client.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     */
    public MaterialUnit toEntity(MaterialUnitWriteDto dto) { var entity = new MaterialUnit(); update(dto, entity); entity.setMaterialId(dto.materialId()); entity.setUnitId(dto.unitId()); return entity; }
    /**
     * Chép các trường cho phép sửa từ DTO vào entity quy đổi đơn vị hiện có; giữ nguyên ID và trường chỉ đọc.
     * @param dto Thông tin đầu vào đã được kiểm tra bằng các ràng buộc khai báo trên DTO.
     * @param entity Đối tượng lưu trữ đang được đọc hoặc cập nhật trong bộ nhớ.
     */
    public void update(MaterialUnitWriteDto dto, MaterialUnit entity) {
        entity.setConversionFactor(dto.conversionFactor());
        entity.setAllowReceipt(dto.allowReceipt());
        entity.setAllowIssue(dto.allowIssue());
    }
}
