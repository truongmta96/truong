package vn.truong.warehouse.repository;

import org.springframework.data.jpa.repository.*;
import vn.truong.warehouse.entity.*;

public interface RecipientRepository extends JpaRepository<Recipient, Long>, JpaSpecificationExecutor<Recipient> {}
