package vn.truong.warehouse.exception;

/**
 * Báo rằng không có bản ghi tương ứng với ID được yêu cầu. Tầng xử lý lỗi API chuyển ngoại lệ này thành HTTP 404.
 */
public class ResourceNotFoundException extends RuntimeException {
    /**
     * Ghi nhận loại dữ liệu và ID không tìm thấy để tầng xử lý lỗi chuyển thành HTTP 404.
     */
    public ResourceNotFoundException(String type, Object id) { super(type + " not found: " + id); } }
