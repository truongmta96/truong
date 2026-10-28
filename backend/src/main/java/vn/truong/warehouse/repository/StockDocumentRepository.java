package vn.truong.warehouse.repository;

import org.springframework.data.jpa.repository.*;
import vn.truong.warehouse.entity.*;

public interface StockDocumentRepository extends JpaRepository<StockDocument, Long>, JpaSpecificationExecutor<StockDocument> {}
