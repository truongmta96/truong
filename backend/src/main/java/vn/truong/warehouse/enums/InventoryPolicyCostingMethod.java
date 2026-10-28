package vn.truong.warehouse.enums;

/**
 * Liệt kê phương pháp tính giá vốn được chọn cho hệ thống. Giá trị tiếng Anh là mã được lưu trong database; chú thích từng giá trị giải thích ý nghĩa tiếng Việt.
 */
public enum InventoryPolicyCostingMethod {
    /**
     * Chưa thiết lập.
     */
    UNSET,
    /**
     * Bình quân di động.
     */
    MOVING_AVERAGE,
    /**
     * Đích danh.
     */
    SPECIFIC }
