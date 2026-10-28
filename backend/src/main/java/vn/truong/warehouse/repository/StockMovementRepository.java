package vn.truong.warehouse.repository;

import org.springframework.data.jpa.repository.*;
import vn.truong.warehouse.entity.*;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long>, JpaSpecificationExecutor<StockMovement> {}
