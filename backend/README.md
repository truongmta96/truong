# Backend quản lý vật tư

Java 21, Spring Boot 3.5.7, Spring Data JPA, MySQL 8.0.16+/8.4, Redis. Schema lấy từ `quan_ly_vat_tu_mysql.sql` người dùng cung cấp: **20 bảng, 2 view**. Dùng MySQL cho backend này; PostgreSQL từ cấu hình môi trường ban đầu không tương thích trực tiếp với DDL MySQL.

## Các lớp đã tạo

Gói Java gốc: `vn.truong.warehouse`.

| SQL | Entity / tiền tố DTO, Repository, Service, Mapper |
| --- | --- |
| app_user | AppUser |
| app_role | AppRole |
| user_role | UserRole |
| unit_of_measure | UnitOfMeasure |
| material_category | MaterialCategory |
| material | Material |
| material_unit | MaterialUnit |
| supplier | Supplier |
| recipient | Recipient |
| inventory_policy | InventoryPolicy |
| material_lot | MaterialLot |
| material_serial | MaterialSerial |
| stock_document | StockDocument |
| stock_document_line | StockDocumentLine |
| stock_line_allocation | StockLineAllocation |
| document_action | DocumentAction |
| stock_movement | StockMovement |
| inventory_balance | InventoryBalance |
| cost_allocation | CostAllocation |
| audit_log | AuditLog |

Mỗi bảng có Entity, response DTO, Mapper, JpaRepository và Service đọc phân trang/tìm theo ID. Các danh mục và bảng liên kết có thêm WriteDto với Bean Validation, create/update trong transaction. Không cung cấp hard delete. Mapper viết tường minh, không cần Lombok hay annotation processor.

Service có create/update: AppRole, UserRole, UnitOfMeasure, MaterialCategory, Material, MaterialUnit, Supplier, Recipient, MaterialLot, MaterialSerial. Tạo Material khởi tạo InventoryBalance bằng 0 cùng transaction. Khóa ghép được giữ nguyên khi update; create khóa ghép đã có bị từ chối.

Service AppUser, InventoryPolicy, StockDocument, StockDocumentLine, StockLineAllocation, DocumentAction, StockMovement, InventoryBalance, CostAllocation, AuditLog chỉ đọc. Đã có API REST cho giao diện quản lý (xem phần bên dưới). Chưa có xác thực/phân quyền, đăng ký/đổi mật khẩu, quy trình duyệt/ghi sổ hay tính giá. Đây là tầng persistence và service nền tảng, chưa phải hệ thống kho hoàn chỉnh. Repository là API nội bộ; controller chỉ công khai các thao tác được liệt kê; không gọi save trực tiếp để bỏ qua quy trình nghiệp vụ.

## Quy tắc mapping

- FK giữ dạng trường ID (`materialId`, `lotId`, ...). Các ràng buộc FK, bao gồm FK nhiều cột cùng material, do migration tạo trong database. Không tạo đồ thị association/cascade tự lưu hoặc xóa; DTO không gây vòng lặp JSON hay lazy loading ngầm.
- `UserRoleId`, `MaterialUnitId` dùng `@IdClass` với equals/hashCode; `InventoryBalance` dùng materialId làm ID và `@Version` cho optimistic locking.
- DECIMAL dùng BigDecimal đúng precision/scale; DATE dùng LocalDate, DATETIME dùng LocalDateTime quy ước UTC. Enum lưu chuỗi; JSON dùng JsonNode.
- BIGINT UNSIGNED được biểu diễn bằng Long: ứng dụng hỗ trợ phạm vi 0..Long.MAX_VALUE, không hỗ trợ nửa trên của phạm vi unsigned MySQL. INT UNSIGNED dùng Long. Không thay SQL để che giới hạn này.
- base_quantity của StockDocumentLine chỉ đọc vì database sinh. Timestamp tự sinh/cập nhật trong database cũng không ghi từ ứng dụng; reload entity khi cần giá trị mới ngay sau insert/update.
- Password hash không có trong response DTO và bị JsonIgnore ở entity. Không có write DTO mật khẩu. JSON audit giữ cấu trúc gốc; tầng nghiệp vụ sau này phải lọc bí mật trước khi ghi/đọc audit.
- Hai view được đọc qua StockViewRepository/StockViewService và MaterialStockDto/TraceStockDto. Không gán ID giả cho view có lot/serial NULL; truy vấn trace lọc theo materialId, danh mục tồn dùng cursor materialId và limit tối đa 200.

## Tạo bảng và chạy trong cloud hiện tại

Migration `src/main/resources/db/migration/V1__material_management.sql` giữ DDL, index, FK, CHECK, view và seed từ file gốc. Chỉ bỏ CREATE DATABASE/USE: database phải được tạo trước, Flyway quản lý schema đích. Không tự xóa bảng hay ghi đè schema cũ; schema đã có bảng cần kế hoạch baseline/migration riêng.

```bash
docker compose -f /workspace/warehouse-env/mysql-compose.yaml up -d --wait
source /workspace/warehouse-env/activate-mysql.sh
cd /workspace/truong/backend
bash /workspace/warehouse-env/mvn.sh verify
bash /workspace/warehouse-env/mvn.sh spring-boot:run
```

Redis đã chạy bằng `/workspace/warehouse-env/compose.yaml`; cấu hình ứng dụng sẵn sàng kết nối nhưng các service nền tảng chưa cache tồn kho. Không dùng activate.sh PostgreSQL hoặc application-local.yaml ngoài repo cho backend MySQL này; bỏ SPRING_PROFILES_ACTIVE/SPRING_DATASOURCE_* cũ nếu shell đã nạp chúng.

Ngoài cloud, cài JDK 21/Maven và MySQL 8.4, tạo database rỗng `material_management` với utf8mb4_0900_ai_ci, đặt WAREHOUSE_DB_URL, WAREHOUSE_DB_USER, WAREHOUSE_DB_PASSWORD bằng cơ chế bí mật của môi trường, rồi chạy `mvn verify` / `mvn spring-boot:run`. Không commit mật khẩu. Flyway cần quyền tạo bảng/index/view trong database đích. Không cần tài khoản root cho ứng dụng.

## Kiểm thử

WarehouseIntegrationTest dùng MySQL thật và transaction rollback dữ liệu kiểm thử. Không dùng H2 thay MySQL vì generated column, enum, CHECK và unsigned khác nhau. Các ca kiểm tra migration/20 mapping/seed, tạo vật tư và balance, khóa ghép, DTO validation, cột base_quantity tính đúng, CHECK chặn min_stock âm, đọc view và ẩn password hash. Chạy trên database phát triển, không chạy migration/test trên production.

## Phần nghiệp vụ cần triển khai tiếp

Các ghi chú trong SQL về Python/Django là ngữ cảnh tài liệu; backend này triển khai bằng Java. DDL không thực thi nghiệp vụ. Chưa triển khai kiểm tra đơn vị cơ sở/hệ số 1, tổng allocation, flag lô/serial/hạn dùng, chuyển trạng thái, phân quyền, chốt chính sách giá, khóa document/balance theo thứ tự, chống ghi sổ lặp và phân bổ giá vốn. Service ghi sổ sau này cần thực hiện toàn bộ trong một transaction; chỉ mở thao tác ghi cho phiếu/sổ sau khi các điều kiện đó có kiểm thử. Kiểm thử hiện tại không chứng minh quy trình nhập/xuất kho hoàn chỉnh.

## API phục vụ giao diện
+
+API mặc định chỉ lắng nghe `127.0.0.1:8080`. Chưa có đăng nhập/phân quyền; chỉ dùng cục bộ để phát triển. Không mở bind address ra mạng công cộng. Vite chuyển tiếp `/api` tới backend, không cần mở CORS toàn cục.
+
+- GET `/api/dashboard`: số vật tư, giá trị tồn, phiếu chờ duyệt, tồn dưới ngưỡng và phiếu gần đây từ database.
+- GET `/api/{resource}?page=0&size=20`: phân trang ổn định gồm content, number, totalPages, totalElements; size 1..200, sort theo khóa.
+- GET `/api/{resource}/{id}`: chi tiết. material-units dùng `/{materialId}/{unitId}`; user-roles dùng `/{userId}/{roleId}`.
+- POST `/api/{resource}`, PUT `/api/{resource}/{id}`: áp dụng cho materials, categories, units, material-units, suppliers, recipients, lots, serials.
+- users, roles, user-roles, documents, document-lines, line-allocations, document-actions, balances, movements, cost-allocations, inventory-policies, audit-logs chỉ GET. Không cung cấp DELETE.
+- GET `/api/stock/materials?after=0&limit=20` và `/api/stock/trace/{materialId}` đọc hai view.
+
+Audit API chỉ trả metadata; oldData/newData được bỏ nội dung để tránh công khai dữ liệu nhạy cảm từng được ghi vào JSON. Lỗi trả message và fields (validation) thay vì SQL hoặc exception chi tiết.
+
+`WarehouseApiTest` kiểm tra API tạo/sửa, dữ liệu không hợp lệ, mã trùng, phân trang, bản ghi không tồn tại, endpoint chỉ đọc và lọc audit JSON. Kết hợp với 5 kiểm thử persistence hiện có thành 8 ca tích hợp.
