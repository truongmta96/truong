package vn.truong.warehouse.repository;

import org.springframework.data.jpa.repository.*;
import vn.truong.warehouse.entity.*;

public interface MaterialCategoryRepository extends JpaRepository<MaterialCategory, Long>, JpaSpecificationExecutor<MaterialCategory> {}
