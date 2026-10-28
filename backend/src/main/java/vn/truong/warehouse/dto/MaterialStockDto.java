package vn.truong.warehouse.dto;
import java.math.BigDecimal;
/**
 * Dữ liệu tồn kho tổng hợp từ sổ biến động trả về cho giao diện, tách khỏi đối tượng JPA để tránh lộ dữ liệu hoặc quan hệ ngoài ý muốn.
 * @param materialId Vật tư.
 * @param code Mã.
 * @param name Tên.
 * @param baseUnitId Đơn vị cơ sở.
 * @param quantityOnHand Tồn hiện tại.
 * @param inventoryValue Giá trị tồn.
 */
public record MaterialStockDto(Long materialId, String code, String name, Long baseUnitId, BigDecimal quantityOnHand, BigDecimal inventoryValue) {}
