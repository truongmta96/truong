package vn.truong.warehouse.enums;

/**
 * Liệt kê loại phiếu kho, xác định nghiệp vụ nhập, xuất hoặc điều chỉnh. Giá trị tiếng Anh là mã được lưu trong database; chú thích từng giá trị giải thích ý nghĩa tiếng Việt.
 */
public enum StockDocumentDocumentType {
    /**
     * Tồn đầu kỳ.
     */
    OPENING,
    /**
     * Nhập kho.
     */
    RECEIPT,
    /**
     * Xuất kho.
     */
    ISSUE,
    /**
     * Điều chỉnh tăng.
     */
    ADJUST_IN,
    /**
     * Điều chỉnh giảm.
     */
    ADJUST_OUT }
