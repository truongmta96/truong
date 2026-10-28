package vn.truong.warehouse.dto;
import java.math.BigDecimal;
public record TraceStockDto(Long materialId, Long lotId, Long serialId, BigDecimal quantityOnHand) {}
