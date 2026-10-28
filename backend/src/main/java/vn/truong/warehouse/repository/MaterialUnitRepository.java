package vn.truong.warehouse.repository;

import org.springframework.data.jpa.repository.*;
import vn.truong.warehouse.entity.*;

public interface MaterialUnitRepository extends JpaRepository<MaterialUnit, MaterialUnitId>, JpaSpecificationExecutor<MaterialUnit> {}
