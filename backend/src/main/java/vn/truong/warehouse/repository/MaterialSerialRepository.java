package vn.truong.warehouse.repository;

import org.springframework.data.jpa.repository.*;
import vn.truong.warehouse.entity.*;

public interface MaterialSerialRepository extends JpaRepository<MaterialSerial, Long>, JpaSpecificationExecutor<MaterialSerial> {}
