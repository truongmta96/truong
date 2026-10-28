package vn.truong.warehouse.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import vn.truong.warehouse.exception.ResourceNotFoundException;
import jakarta.validation.ConstraintViolationException;
import java.util.Map;
@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> missing(ResourceNotFoundException ex) { return error(404, "Không tìm thấy bản ghi."); }
    @ExceptionHandler({IllegalArgumentException.class, ConstraintViolationException.class, org.springframework.http.converter.HttpMessageNotReadableException.class, org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class})
    public ResponseEntity<?> invalid(Exception ex) { return error(400, "Dữ liệu không hợp lệ. Vui lòng kiểm tra các trường và mã liên kết."); }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validation(MethodArgumentNotValidException ex) {
        var fields = new java.util.LinkedHashMap<String, String>();
        ex.getBindingResult().getFieldErrors().forEach(e -> fields.put(e.getField(), e.getDefaultMessage()));
        return ResponseEntity.badRequest().body(Map.of("message", "Vui lòng kiểm tra thông tin đã nhập.", "fields", fields));
    }
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<?> conflict(DataAccessException ex) { return error(409, "Không thể lưu: mã có thể đã tồn tại, dữ liệu liên kết không hợp lệ hoặc vi phạm ràng buộc."); }
    private ResponseEntity<?> error(int code, String message) { return ResponseEntity.status(code).body(Map.of("message", message)); }
}
