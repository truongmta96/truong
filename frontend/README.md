+# Giao diện quản lý vật tư
+
+React 19 + Vite 7, giao diện tiếng Việt, responsive. Không dùng số liệu mẫu thay database.
+
+## Chạy trong cloud
+
+Terminal backend:
+
+```bash
+docker compose -f /workspace/warehouse-env/mysql-compose.yaml up -d --wait
+source /workspace/warehouse-env/activate-mysql.sh
+cd /workspace/truong/backend
+bash /workspace/warehouse-env/mvn.sh spring-boot:run
+```
+
+Terminal frontend:
+
+```bash
+cd /workspace/truong/frontend
+npm ci
+npm run dev
+```
+
+Vite lắng nghe loopback cổng 5173, chuyển tiếp `/api` tới backend loopback cổng 8080. Môi trường onboarding không cung cấp liên kết preview localhost. Bên ngoài cloud, dùng trình duyệt trên máy có thể truy cập dev server và chạy backend theo backend/README.md. Không cấu hình dịch vụ này công khai khi chưa có xác thực và phân quyền.
+
+## Các màn hình
+
+- Tổng quan: số vật tư, giá trị tồn, phiếu chờ duyệt, vật tư dưới ngưỡng, phiếu gần đây.
+- Danh mục: vật tư, nhóm, đơn vị, quy đổi đơn vị.
+- Đối tác: nhà cung cấp và người nhận.
+- Phiếu kho: phiếu, dòng phiếu, phân bổ lô/serial, lịch sử thao tác (tra cứu).
+- Tồn kho: số dư, sổ biến động, phân bổ giá vốn (tra cứu).
+- Lô và serial: tạo, sửa và xem chi tiết.
+- Quản trị: người dùng, vai trò, phân quyền, chính sách kho và audit (tra cứu).
+
+Các danh mục hỗ trợ thêm/sửa với form validation, gợi ý khóa ngoại theo ID, xem chi tiết, phân trang, tìm/lọc **trên trang hiện tại** và xuất **trang hiện tại** ra CSV. Gợi ý liên kết tối đa 200 bản ghi; có thể nhập ID hợp lệ không có trong gợi ý. Không có hard delete. CSV vô hiệu hóa công thức trong dữ liệu chuỗi. Thời gian DATETIME được hiểu là UTC và hiển thị ở Asia/Ho_Chi_Minh.
+
+Các màn hình nghiệp vụ chỉ đọc để không ghi trực tiếp vào sổ kho. Chưa hỗ trợ lập/duyệt/ghi sổ phiếu, tính giá vốn hoặc chỉnh phân quyền. Chưa có đăng nhập. Không đưa mật khẩu database vào VITE_*; mọi truy cập database qua backend.
+
+## Kiểm thử
+
+```bash
+npm run build
+# Backend và MySQL phải đang chạy; cần Chromium hệ thống.
+npm test
+```
+
+Playwright dùng `/usr/bin/chromium`; có thể đặt CHROMIUM_PATH nếu trình duyệt ở đường dẫn khác. Test kiểm tra toàn bộ 20 trang, luồng tạo/sửa/xem chi tiết nhóm, lọc trang, API lỗi/thử lại và điều hướng/form trên điện thoại. Test tạo nhóm có tiền tố `UI-E2E-` trong database phát triển; chỉ chạy trên database dành cho kiểm thử, dọn các bản ghi do test tạo sau khi xác minh nếu không cần giữ.
+
+## Cấu trúc
+
+`src/resources.js` mô tả 20 màn hình và trường nhập; `App.jsx` chứa layout, dashboard, danh sách, form và modal dùng chung; `api.js` chứa HTTP client/định dạng; `styles.css` chứa giao diện. Hash URL cho phép mở trực tiếp từng trang, ví dụ `#materials`, `#documents`, `#balances`. Không cần server rewrite cho các trang.
