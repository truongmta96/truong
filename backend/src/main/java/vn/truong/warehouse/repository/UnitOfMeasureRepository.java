package vn.truong.warehouse.repository;

import org.springframework.data.jpa.repository.*;
import vn.truong.warehouse.entity.*;

public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, Long>, JpaSpecificationExecutor<UnitOfMeasure> {}
