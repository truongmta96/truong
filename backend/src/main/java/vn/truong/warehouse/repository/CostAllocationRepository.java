package vn.truong.warehouse.repository;

import org.springframework.data.jpa.repository.*;
import vn.truong.warehouse.entity.*;

public interface CostAllocationRepository extends JpaRepository<CostAllocation, Long>, JpaSpecificationExecutor<CostAllocation> {}
