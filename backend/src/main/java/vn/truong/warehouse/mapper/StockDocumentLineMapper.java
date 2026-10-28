package vn.truong.warehouse.mapper;

import org.springframework.stereotype.Component;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;

@Component
public class StockDocumentLineMapper {
    public StockDocumentLineDto toDto(StockDocumentLine entity) {
        if (entity == null) return null;
        return new StockDocumentLineDto(entity.getId(), entity.getDocumentId(), entity.getLineNo(), entity.getMaterialId(), entity.getUnitId(), entity.getQuantity(), entity.getConversionFactor(), entity.getBaseQuantity(), entity.getInputUnitPrice(), entity.getNote());
    }
}
