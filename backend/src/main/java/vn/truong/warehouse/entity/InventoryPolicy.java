package vn.truong.warehouse.entity;

import jakarta.persistence.*;
import vn.truong.warehouse.enums.*;

/**
 * Ánh xạ bảng inventory_policy trong MySQL thành đối tượng Java dùng cho chính sách kho. Khóa ngoại được giữ bằng ID; các ràng buộc liên kết được kiểm tra ở database.
 */
@Entity
@Table(name = "inventory_policy")
public class InventoryPolicy {
    /**
     * Lưu id của bản ghi.
     */
    @Id
    @Column(columnDefinition = "tinyint unsigned", name = "id", nullable = false)
    private Integer id;

    /**
     * Lưu phương pháp giá của bản ghi.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "costing_method", nullable = false)
    private InventoryPolicyCostingMethod costingMethod;

    /**
     * Lưu thời điểm ghi sổ của bản ghi.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "posting_mode", nullable = false)
    private InventoryPolicyPostingMode postingMode;

    /**
     * Hàm tạo rỗng phục vụ JPA khôi phục bản ghi.
     */
    public InventoryPolicy() {}
    /**
     * Đọc giá trị id hiện có trong đối tượng Java.
     */
    public Integer getId() { return id; }
    /**
     * Gán giá trị id trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setId(Integer value) { this.id = value; }
    /**
     * Đọc giá trị phương pháp giá hiện có trong đối tượng Java.
     */
    public InventoryPolicyCostingMethod getCostingMethod() { return costingMethod; }
    /**
     * Gán giá trị phương pháp giá trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setCostingMethod(InventoryPolicyCostingMethod value) { this.costingMethod = value; }
    /**
     * Đọc giá trị thời điểm ghi sổ hiện có trong đối tượng Java.
     */
    public InventoryPolicyPostingMode getPostingMode() { return postingMode; }
    /**
     * Gán giá trị thời điểm ghi sổ trong đối tượng Java; bản thân hàm này không ghi database.
     */
    public void setPostingMode(InventoryPolicyPostingMode value) { this.postingMode = value; }
}
