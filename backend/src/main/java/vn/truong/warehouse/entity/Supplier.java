package vn.truong.warehouse.entity;

import jakarta.persistence.*;

/**
 * Ánh xạ bảng supplier trong MySQL thành đối tượng Java dùng cho nhà cung cấp. Khóa ngoại được giữ bằng ID; các ràng buộc liên kết được kiểm tra ở database.
 */
@Entity
@Table(name = "supplier")
public class Supplier {
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
     * Lưu tên của bản ghi.
     */
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    /**
     * Lưu điện thoại của bản ghi.
     */
    @Column(name = "phone", length = 30)
    private String phone;

    /**
     * Lưu địa chỉ của bản ghi.
     */
    @Column(name = "address", length = 500)
    private String address;

    /**
     * Lưu mã số thuế của bản ghi.
     */
    @Column(name = "tax_code", length = 30)
    private String taxCode;

    /**
     * Lưu trạng thái của bản ghi.
     */
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    /**
     * Hàm tạo rỗng phục vụ JPA khôi phục bản ghi.
     */
    public Supplier() {}
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
    /**
     * Đọc giá trị điện thoại hiện có trong đối tượng Java.
     */
    public String getPhone() { return phone; }
    /**
     * Gán giá trị điện thoại trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setPhone(String value) { this.phone = value; }
    /**
     * Đọc giá trị địa chỉ hiện có trong đối tượng Java.
     */
    public String getAddress() { return address; }
    /**
     * Gán giá trị địa chỉ trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setAddress(String value) { this.address = value; }
    /**
     * Đọc giá trị mã số thuế hiện có trong đối tượng Java.
     */
    public String getTaxCode() { return taxCode; }
    /**
     * Gán giá trị mã số thuế trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setTaxCode(String value) { this.taxCode = value; }
    /**
     * Đọc giá trị trạng thái hiện có trong đối tượng Java.
     */
    public Boolean getIsActive() { return isActive; }
    /**
     * Gán giá trị trạng thái trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setIsActive(Boolean value) { this.isActive = value; }
}
