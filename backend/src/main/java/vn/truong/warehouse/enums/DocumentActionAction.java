package vn.truong.warehouse.enums;

/**
 * Liệt kê các thao tác trong lịch sử xử lý phiếu. Giá trị tiếng Anh là mã được lưu trong database; chú thích từng giá trị giải thích ý nghĩa tiếng Việt.
 */
public enum DocumentActionAction {
    /**
     * Gửi duyệt.
     */
    SUBMIT,
    /**
     * Rút phiếu.
     */
    WITHDRAW,
    /**
     * Phê duyệt.
     */
    APPROVE,
    /**
     * Từ chối.
     */
    REJECT,
    /**
     * Ghi sổ.
     */
    POST,
    /**
     * Hủy phiếu.
     */
    CANCEL }
