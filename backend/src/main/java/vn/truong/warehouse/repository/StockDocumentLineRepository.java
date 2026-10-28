package vn.truong.warehouse.repository;

import org.springframework.data.jpa.repository.*;
import vn.truong.warehouse.entity.*;

public interface StockDocumentLineRepository extends JpaRepository<StockDocumentLine, Long>, JpaSpecificationExecutor<StockDocumentLine> {}
