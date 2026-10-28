package vn.truong.warehouse.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import vn.truong.warehouse.exception.ResourceNotFoundException;
import jakarta.validation.ConstraintViolationException;
import java.util.Map;
/**
 * Đổi lỗi phát sinh từ API thành mã HTTP và thông báo an toàn cho giao diện; không đưa câu SQL hay thông tin nội bộ ra phản hồi.
 */
@RestControllerAdvice
public class ApiExceptionHandler {
    /**
     * Trả HTTP 401 với thông báo chung cho đăng nhập sai hoặc tài khoản bị khóa; không tiết lộ tài khoản nào tồn tại.
     */
    @ExceptionHandler(org.springframework.security.core.AuthenticationException.class)
    public ResponseEntity<?> unauthorized(Exception ex) { return error(401, "Tên đăng nhập hoặc mật khẩu không đúng, hoặc tài khoản đã bị khóa."); }
    /**
     * Trả HTTP 404 khi không tìm thấy bản ghi.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> missing(ResourceNotFoundException ex) { return error(404, "Không tìm thấy bản ghi."); }
    /**
     * Trả HTTP 400 khi kiểu dữ liệu, giá trị hoặc tham số gửi lên không hợp lệ.
     */
    @ExceptionHandler({IllegalArgumentException.class, ConstraintViolationException.class, org.springframework.http.converter.HttpMessageNotReadableException.class, org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class})
    public ResponseEntity<?> invalid(Exception ex) { return error(400, "Dữ liệu không hợp lệ. Vui lòng kiểm tra các trường và mã liên kết."); }
    /**
     * Gom lỗi kiểm tra theo tên trường để giao diện hiển thị cạnh ô nhập tương ứng.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validation(MethodArgumentNotValidException ex) {
        var fields = new java.util.LinkedHashMap<String, String>();
        ex.getBindingResult().getFieldErrors().forEach(e -> fields.put(e.getField(), e.getDefaultMessage()));
        return ResponseEntity.badRequest().body(Map.of("message", "Vui lòng kiểm tra thông tin đã nhập.", "fields", fields));
    }
    /**
     * Trả HTTP 409 cho lỗi ràng buộc dữ liệu như mã trùng hoặc khóa ngoại không hợp lệ, không để lộ SQL.
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<?> conflict(DataAccessException ex) { return error(409, "Không thể lưu: mã có thể đã tồn tại, dữ liệu liên kết không hợp lệ hoặc vi phạm ràng buộc."); }
    /**
     * Tạo phản hồi JSON có mã HTTP và một trường message dùng thống nhất cho lỗi API.
     */
    private ResponseEntity<?> error(int code, String message) { return ResponseEntity.status(code).body(Map.of("message", message)); }
}
