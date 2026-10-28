package vn.truong.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Điểm khởi động backend Spring Boot. Spring quét các thành phần trong gói này, kết nối MySQL và đăng ký API quản lý vật tư.
 */
@SpringBootApplication
public class WarehouseApplication {
    /**
     * Khởi động Spring Boot; từ đây hệ thống tạo kết nối database, lớp xử lý và máy chủ API.
     * @param args Tham số dòng lệnh khi khởi động ứng dụng.
     */
    public static void main(String[] args) { SpringApplication.run(WarehouseApplication.class, args); } }
