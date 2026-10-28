package vn.truong.warehouse.enums;

/**
 * Liệt kê thời điểm ghi sổ theo chính sách kho. Giá trị tiếng Anh là mã được lưu trong database; chú thích từng giá trị giải thích ý nghĩa tiếng Việt.
 */
public enum InventoryPolicyPostingMode {
    /**
     * Chưa thiết lập.
     */
    UNSET,
    /**
     * Khi phê duyệt.
     */
    ON_APPROVAL,
    /**
     * Khi xác nhận.
     */
    ON_CONFIRMATION }
