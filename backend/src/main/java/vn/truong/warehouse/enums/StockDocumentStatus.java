package vn.truong.warehouse.enums;

/**
 * Liệt kê trạng thái xử lý của phiếu kho. Giá trị tiếng Anh là mã được lưu trong database; chú thích từng giá trị giải thích ý nghĩa tiếng Việt.
 */
public enum StockDocumentStatus {
    /**
     * Bản nháp.
     */
    DRAFT,
    /**
     * Chờ duyệt.
     */
    PENDING,
    /**
     * Đã duyệt.
     */
    APPROVED,
    /**
     * Từ chối.
     */
    REJECTED,
    /**
     * Đã ghi sổ.
     */
    POSTED,
    /**
     * Đã hủy.
     */
    CANCELLED }
