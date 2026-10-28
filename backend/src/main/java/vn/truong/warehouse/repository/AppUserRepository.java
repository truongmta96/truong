package vn.truong.warehouse.repository;

import org.springframework.data.jpa.repository.*;
import vn.truong.warehouse.entity.*;

public interface AppUserRepository extends JpaRepository<AppUser, Long>, JpaSpecificationExecutor<AppUser> {
    java.util.Optional<AppUser> findByUsername(String username);
}
