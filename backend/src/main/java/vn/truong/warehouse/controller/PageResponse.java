package vn.truong.warehouse.controller;
import java.util.List;
import org.springframework.data.domain.Page;
/**
 * Cấu trúc phân trang JSON ổn định để frontend đọc danh sách và điều hướng. Chỉ số trang bắt đầu từ 0.
 */
public record PageResponse<T>(List<T> content, int number, int totalPages, long totalElements) {
    /**
     * Lấy danh sách và các thông tin phân trang từ kết quả Spring Data, giữ cấu trúc JSON nhất quán cho frontend.
     */
    public PageResponse(Page<T> page) { this(page.getContent(), page.getNumber(), page.getTotalPages(), page.getTotalElements()); }
}
