package vn.truong.warehouse.repository;

import org.springframework.data.jpa.repository.*;
import vn.truong.warehouse.entity.*;

public interface InventoryPolicyRepository extends JpaRepository<InventoryPolicy, Integer>, JpaSpecificationExecutor<InventoryPolicy> {}
