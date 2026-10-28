package vn.truong.warehouse.entity;

import jakarta.persistence.*;

/**
 * Ánh xạ bảng recipient trong MySQL thành đối tượng Java dùng cho người nhận. Khóa ngoại được giữ bằng ID; các ràng buộc liên kết được kiểm tra ở database.
 */
@Entity
@Table(name = "recipient")
public class Recipient {
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
    @Column(name = "code", nullable = false, length = 60)
    private String code;

    /**
     * Lưu họ và tên của bản ghi.
     */
    @Column(name = "full_name", nullable = false, length = 200)
    private String fullName;

    /**
     * Lưu bộ phận của bản ghi.
     */
    @Column(name = "department", length = 200)
    private String department;

    /**
     * Lưu điện thoại của bản ghi.
     */
    @Column(name = "phone", length = 30)
    private String phone;

    /**
     * Lưu trạng thái của bản ghi.
     */
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    /**
     * Hàm tạo rỗng phục vụ JPA khôi phục bản ghi.
     */
    public Recipient() {}
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
     * Đọc giá trị họ và tên hiện có trong đối tượng Java.
     */
    public String getFullName() { return fullName; }
    /**
     * Gán giá trị họ và tên trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setFullName(String value) { this.fullName = value; }
    /**
     * Đọc giá trị bộ phận hiện có trong đối tượng Java.
     */
    public String getDepartment() { return department; }
    /**
     * Gán giá trị bộ phận trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setDepartment(String value) { this.department = value; }
    /**
     * Đọc giá trị điện thoại hiện có trong đối tượng Java.
     */
    public String getPhone() { return phone; }
    /**
     * Gán giá trị điện thoại trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setPhone(String value) { this.phone = value; }
    /**
     * Đọc giá trị trạng thái hiện có trong đối tượng Java.
     */
    public Boolean getIsActive() { return isActive; }
    /**
     * Gán giá trị trạng thái trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setIsActive(Boolean value) { this.isActive = value; }
}
