package vn.truong.warehouse.dto;
import java.math.BigDecimal;
public record MaterialStockDto(Long materialId, String code, String name, Long baseUnitId, BigDecimal quantityOnHand, BigDecimal inventoryValue) {}
