package vn.truong.warehouse.entity;

import jakarta.persistence.*;
import vn.truong.warehouse.enums.*;

@Entity
@Table(name = "inventory_policy")
public class InventoryPolicy {
    @Id
    @Column(columnDefinition = "tinyint unsigned", name = "id", nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "costing_method", nullable = false)
    private InventoryPolicyCostingMethod costingMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "posting_mode", nullable = false)
    private InventoryPolicyPostingMode postingMode;

    public InventoryPolicy() {}
    public Integer getId() { return id; }
    public void setId(Integer value) { this.id = value; }
    public InventoryPolicyCostingMethod getCostingMethod() { return costingMethod; }
    public void setCostingMethod(InventoryPolicyCostingMethod value) { this.costingMethod = value; }
    public InventoryPolicyPostingMode getPostingMode() { return postingMode; }
    public void setPostingMode(InventoryPolicyPostingMode value) { this.postingMode = value; }
}
