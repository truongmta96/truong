package vn.truong.warehouse.enums;

/**
 * Liệt kê phương pháp tính giá đã dùng cho một biến động. Giá trị tiếng Anh là mã được lưu trong database; chú thích từng giá trị giải thích ý nghĩa tiếng Việt.
 */
public enum StockMovementCostingMethod {
    /**
     * Bình quân di động.
     */
    MOVING_AVERAGE,
    /**
     * Đích danh.
     */
    SPECIFIC }
