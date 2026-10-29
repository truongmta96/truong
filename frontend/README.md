# Giao diện quản lý vật tư

React 19 + Vite 7, giao diện tiếng Việt, responsive. Không dùng số liệu mẫu thay database.

## Chạy trong cloud

Terminal backend:

```bash
docker compose -f /workspace/warehouse-env/mysql-compose.yaml up -d --wait
source /workspace/warehouse-env/activate-mysql.sh
cd /workspace/truong/backend
bash /workspace/warehouse-env/mvn.sh spring-boot:run
```

Terminal frontend:

```bash
cd /workspace/truong/frontend
npm ci
npm run dev
```

Vite lắng nghe loopback cổng 5173, chuyển tiếp `/api` tới backend loopback cổng 8080. Môi trường onboarding không cung cấp liên kết preview localhost. Bên ngoài cloud, dùng trình duyệt trên máy có thể truy cập dev server và chạy backend theo backend/README.md. Để triển khai công khai cần HTTPS, cookie Secure, kiểm soát truy cập mạng và giới hạn tần suất đăng nhập/đăng ký ở reverse proxy.

## Các màn hình

- Tổng quan: số vật tư, giá trị tồn, phiếu chờ duyệt, vật tư dưới ngưỡng, phiếu gần đây.
- Danh mục: vật tư, nhóm, đơn vị, quy đổi đơn vị.
- Đối tác: nhà cung cấp và người nhận.
- Phiếu kho: phiếu, dòng phiếu, phân bổ lô/serial, lịch sử thao tác (tra cứu).
- Tồn kho: số dư, sổ biến động, phân bổ giá vốn (tra cứu).
- Lô và serial: tạo, sửa và xem chi tiết.
- Quản trị: xem/sửa tài khoản thường; vai trò, phân quyền, chính sách kho và audit chỉ tra cứu.

Các danh mục hỗ trợ thêm/sửa với form validation, gợi ý khóa ngoại theo ID, xem chi tiết, phân trang, tìm/lọc **trên trang hiện tại** và xuất **trang hiện tại** ra CSV. Gợi ý liên kết tối đa 200 bản ghi; có thể nhập ID hợp lệ không có trong gợi ý. Không có hard delete. CSV vô hiệu hóa công thức trong dữ liệu chuỗi. Thời gian DATETIME được hiểu là UTC và hiển thị ở Asia/Ho_Chi_Minh.

Các màn hình nghiệp vụ chỉ đọc để không ghi trực tiếp vào sổ kho. Chưa hỗ trợ lập/duyệt/ghi sổ phiếu, tính giá vốn hoặc chỉnh phân quyền. Đã có đăng nhập và đăng ký, chỉnh họ tên, đổi mật khẩu. Không đưa mật khẩu database vào VITE_*; mọi truy cập database qua backend.

## Kiểm thử

```bash
npm run build
# Backend và MySQL phải đang chạy; cần Chromium hệ thống.
npm test
```

Playwright dùng `/usr/bin/chromium`; có thể đặt CHROMIUM_PATH nếu trình duyệt ở đường dẫn khác. Test kiểm tra toàn bộ 20 trang, luồng tạo/sửa/xem chi tiết nhóm, lọc trang, API lỗi/thử lại và điều hướng/form trên điện thoại. Test tạo nhóm có tiền tố `UI-E2E-` trong database phát triển; chỉ chạy trên database dành cho kiểm thử, dọn các bản ghi do test tạo sau khi xác minh nếu không cần giữ.

## Cấu trúc

`src/resources.js` mô tả 20 màn hình và trường nhập; `App.jsx` chứa layout, dashboard, danh sách, form và modal dùng chung; `api.js` chứa HTTP client/định dạng; `styles.css` chứa giao diện. Hash URL cho phép mở trực tiếp từng trang, ví dụ `#materials`, `#documents`, `#balances`. Không cần server rewrite cho các trang.

## Đăng nhập, đăng ký và hồ sơ
+
+Khi mở ứng dụng sẽ hiển thị màn hình đăng nhập. Chọn Đăng ký để tạo tài khoản với tên đăng nhập (3..150 ký tự), họ tên và mật khẩu ít nhất 12 ký tự/tối đa 72 byte UTF-8. Đăng ký xong cần đăng nhập. Tài khoản mới mặc định VIEWER nên chỉ tra cứu; quyền sửa danh mục do CLERK/ADMIN đảm nhiệm. Không có admin hay mật khẩu mặc định.
+
+Bấm avatar góc trên phải hoặc tên tài khoản ở sidebar để vào Tài khoản cá nhân: sửa họ tên, xem vai trò, đổi mật khẩu và đăng xuất. Tên đăng nhập giữ cố định. Đổi mật khẩu yêu cầu mật khẩu hiện tại và đăng nhập lại bằng mật khẩu mới. Trang quản trị bị ẩn với tài khoản không phải ADMIN; kiểm tra quyền thực sự vẫn diễn ra ở API.
+
+Cookie HttpOnly do backend giữ phiên; không lưu token/mật khẩu trong localStorage. Backend phải chạy trước frontend. Phiên hết hạn sẽ quay về đăng nhập. Khi backend báo lỗi kết nối, trang hiển thị lỗi/thử lại, không dùng dữ liệu tài khoản giả.
+
+Trên Windows: cài Node.js 24 LTS, mở terminal tại frontend và chạy `npm.cmd ci`, `npm.cmd run dev`; chạy WarehouseApplication bằng JDK 21 trong IntelliJ. Giữ lại cấu hình database của máy bạn. Nếu dùng schema có sẵn và đã tắt Flyway theo hướng dẫn trước đó, chức năng tài khoản không yêu cầu bật lại Flyway hay tạo thêm bảng; cần seed VIEWER trong app_role và mật khẩu người dùng ở dạng BCrypt.
+
+Test mới trong auth.spec.js kiểm tra đăng ký, mật khẩu sai, đăng nhập, giữ phiên sau reload, chỉnh hồ sơ, đổi mật khẩu/đăng xuất, quyền VIEWER và form mobile. Các test tạo tài khoản e2e-auth-* với quyền VIEWER, chỉ chạy ở database kiểm thử. Bốn test quản lý cũ cần tài khoản ADMIN kiểm thử được cung cấp qua WAREHOUSE_E2E_USERNAME/WAREHOUSE_E2E_PASSWORD, nếu không chúng được ghi nhận skipped; test không tự cấp ADMIN. Các tài khoản test không phải tài khoản demo để dùng thực tế.

## Hướng dẫn đọc source bằng tiếng Việt

Xem [luồng xử lý, ý nghĩa từng tầng và các thuật ngữ trong source](../docs/HUONG_DAN_DOC_SOURCE.md). Các class/method và hàm giao diện đã có chú thích tiếng Việt ngay trong mã.

## Sửa tài khoản người dùng khác

Đăng nhập ADMIN → Quản trị → Người dùng → bấm biểu tượng bút ở tài khoản cần sửa. Form chỉ có họ tên và ô Đang sử dụng. Bỏ chọn ô này để khóa; chọn lại để mở tài khoản. Không có nút tạo mới ở trang này.

Các tài khoản có quyền ADMIN được backend loại khỏi danh sách và chặn truy cập chi tiết/cập nhật qua ID. Hồ sơ ADMIN của chính bạn vẫn sửa tại avatar → Tài khoản cá nhân. Tên đăng nhập, mật khẩu và vai trò của người khác không thay đổi tại form quản trị.

admin-accounts.spec.js kiểm tra form với API giả lập, không cần tài khoản ADMIN thực hoặc thay đổi đặc quyền trong database. Phần bảo vệ quyền thực tế được kiểm tra trên MySQL bằng AdminAccountTest.
