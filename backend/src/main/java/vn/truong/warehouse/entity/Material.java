package vn.truong.warehouse.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.*;

/**
 * Ánh xạ bảng material trong MySQL thành đối tượng Java dùng cho vật tư. Khóa ngoại được giữ bằng ID; các ràng buộc liên kết được kiểm tra ở database.
 */
@Entity
@Table(name = "material")
public class Material {
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
     * Lưu nhóm vật tư của bản ghi.
     */
    @Column(columnDefinition = "bigint unsigned", name = "category_id")
    private Long categoryId;

    /**
     * Lưu đơn vị cơ sở của bản ghi.
     */
    @Column(columnDefinition = "bigint unsigned", name = "base_unit_id", nullable = false)
    private Long baseUnitId;

    /**
     * Lưu theo dõi lô của bản ghi.
     */
    @Column(name = "track_lot", nullable = false)
    private Boolean trackLot;

    /**
     * Lưu theo dõi serial của bản ghi.
     */
    @Column(name = "track_serial", nullable = false)
    private Boolean trackSerial;

    /**
     * Lưu theo dõi hạn dùng của bản ghi.
     */
    @Column(name = "track_expiry", nullable = false)
    private Boolean trackExpiry;

    /**
     * Lưu cho phép số lẻ của bản ghi.
     */
    @Column(name = "allow_fractional_quantity", nullable = false)
    private Boolean allowFractionalQuantity;

    /**
     * Lưu tồn tối thiểu của bản ghi.
     */
    @Column(name = "min_stock", nullable = false, precision = 20, scale = 6)
    private BigDecimal minStock;

    /**
     * Lưu trạng thái của bản ghi.
     */
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    /**
     * Lưu ngày tạo của bản ghi.
     */
    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Hàm tạo rỗng phục vụ JPA khôi phục bản ghi.
     */
    public Material() {}
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
     * Đọc giá trị nhóm vật tư hiện có trong đối tượng Java.
     */
    public Long getCategoryId() { return categoryId; }
    /**
     * Gán giá trị nhóm vật tư trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setCategoryId(Long value) { this.categoryId = value; }
    /**
     * Đọc giá trị đơn vị cơ sở hiện có trong đối tượng Java.
     */
    public Long getBaseUnitId() { return baseUnitId; }
    /**
     * Gán giá trị đơn vị cơ sở trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setBaseUnitId(Long value) { this.baseUnitId = value; }
    /**
     * Đọc giá trị theo dõi lô hiện có trong đối tượng Java.
     */
    public Boolean getTrackLot() { return trackLot; }
    /**
     * Gán giá trị theo dõi lô trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setTrackLot(Boolean value) { this.trackLot = value; }
    /**
     * Đọc giá trị theo dõi serial hiện có trong đối tượng Java.
     */
    public Boolean getTrackSerial() { return trackSerial; }
    /**
     * Gán giá trị theo dõi serial trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setTrackSerial(Boolean value) { this.trackSerial = value; }
    /**
     * Đọc giá trị theo dõi hạn dùng hiện có trong đối tượng Java.
     */
    public Boolean getTrackExpiry() { return trackExpiry; }
    /**
     * Gán giá trị theo dõi hạn dùng trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setTrackExpiry(Boolean value) { this.trackExpiry = value; }
    /**
     * Đọc giá trị cho phép số lẻ hiện có trong đối tượng Java.
     */
    public Boolean getAllowFractionalQuantity() { return allowFractionalQuantity; }
    /**
     * Gán giá trị cho phép số lẻ trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setAllowFractionalQuantity(Boolean value) { this.allowFractionalQuantity = value; }
    /**
     * Đọc giá trị tồn tối thiểu hiện có trong đối tượng Java.
     */
    public BigDecimal getMinStock() { return minStock; }
    /**
     * Gán giá trị tồn tối thiểu trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setMinStock(BigDecimal value) { this.minStock = value; }
    /**
     * Đọc giá trị trạng thái hiện có trong đối tượng Java.
     */
    public Boolean getIsActive() { return isActive; }
    /**
     * Gán giá trị trạng thái trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setIsActive(Boolean value) { this.isActive = value; }
    /**
     * Đọc giá trị ngày tạo hiện có trong đối tượng Java.
     */
    public LocalDateTime getCreatedAt() { return createdAt; }
    /**
     * Gán giá trị ngày tạo trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setCreatedAt(LocalDateTime value) { this.createdAt = value; }
}
