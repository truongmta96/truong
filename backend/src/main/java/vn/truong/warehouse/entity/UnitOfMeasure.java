package vn.truong.warehouse.entity;

import jakarta.persistence.*;

/**
 * Ánh xạ bảng unit_of_measure trong MySQL thành đối tượng Java dùng cho đơn vị tính. Khóa ngoại được giữ bằng ID; các ràng buộc liên kết được kiểm tra ở database.
 */
@Entity
@Table(name = "unit_of_measure")
public class UnitOfMeasure {
    /**
     * Lưu id của bản ghi.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned", name = "id", nullable = false)
    private Long id;

    /**
     * Lưu mã của bản ghi.
     */
    @Column(name = "code", nullable = false, length = 30)
    private String code;

    /**
     * Lưu tên của bản ghi.
     */
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    /**
     * Hàm tạo rỗng phục vụ JPA khôi phục bản ghi.
     */
    public UnitOfMeasure() {}
    /**
     * Đọc giá trị id hiện có trong đối tượng Java.
     */
    public Long getId() { return id; }
    /**
     * Gán giá trị id trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setId(Long value) { this.id = value; }
    /**
     * Đọc giá trị mã hiện có trong đối tượng Java.
     */
    public String getCode() { return code; }
    /**
     * Gán giá trị mã trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setCode(String value) { this.code = value; }
    /**
     * Đọc giá trị tên hiện có trong đối tượng Java.
     */
    public String getName() { return name; }
    /**
     * Gán giá trị tên trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setName(String value) { this.name = value; }
}
