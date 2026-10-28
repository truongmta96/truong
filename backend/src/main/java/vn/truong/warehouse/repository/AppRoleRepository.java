package vn.truong.warehouse.repository;

import org.springframework.data.jpa.repository.*;
import vn.truong.warehouse.entity.*;

public interface AppRoleRepository extends JpaRepository<AppRole, Long>, JpaSpecificationExecutor<AppRole> {}
