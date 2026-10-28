package vn.truong.warehouse.repository;

import org.springframework.data.jpa.repository.*;
import vn.truong.warehouse.entity.*;

/**
 * Cổng truy cập dữ liệu chính sách kho qua Spring Data JPA. Spring tự triển khai tìm theo ID, phân trang và lưu; service quyết định nghiệp vụ được phép thực hiện. Có phương thức save không đồng nghĩa API cho phép ghi trực tiếp.
 */
public interface InventoryPolicyRepository extends JpaRepository<InventoryPolicy, Integer>, JpaSpecificationExecutor<InventoryPolicy> {}
