package vn.truong.warehouse.repository;

import org.springframework.data.jpa.repository.*;
import vn.truong.warehouse.entity.*;

public interface StockLineAllocationRepository extends JpaRepository<StockLineAllocation, Long>, JpaSpecificationExecutor<StockLineAllocation> {}
