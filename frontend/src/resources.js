/**
 * Bản mô tả 20 trang quản lý dùng bởi ResourcePage/Editor. key là đường dẫn API và tên trang sau dấu #; group là nhóm menu; columns là các cột hiển thị; fields mô tả dữ liệu đọc; writeFields là các trường được phép nhập; ids là các thành phần khóa. ref chỉ danh mục gợi ý cho khóa ngoại; required/max/min mô tả giới hạn form. Việc cấu hình nút/form không thay thế kiểm tra quyền và ràng buộc ở backend.
 */
export const resources = [
  {
    "key": "materials",
    "cls": "Material",
    "title": "Vật tư",
    "description": "Danh sách vật tư, đơn vị cơ sở và quy tắc theo dõi.",
    "group": "catalog",
    "columns": [
      "code",
      "name",
      "categoryId",
      "baseUnitId",
      "minStock",
      "isActive"
    ],
    "fields": [
      {
        "key": "id",
        "label": "ID",
        "type": "number",
        "required": false
      },
      {
        "key": "code",
        "label": "Mã",
        "type": "text",
        "required": false
      },
      {
        "key": "name",
        "label": "Tên",
        "type": "text",
        "required": false
      },
      {
        "key": "categoryId",
        "label": "Nhóm vật tư",
        "type": "number",
        "required": false,
        "ref": "categories"
      },
      {
        "key": "baseUnitId",
        "label": "Đơn vị cơ sở",
        "type": "number",
        "required": false,
        "ref": "units"
      },
      {
        "key": "trackLot",
        "label": "Theo dõi lô",
        "type": "boolean",
        "required": false
      },
      {
        "key": "trackSerial",
        "label": "Theo dõi serial",
        "type": "boolean",
        "required": false
      },
      {
        "key": "trackExpiry",
        "label": "Theo dõi hạn dùng",
        "type": "boolean",
        "required": false
      },
      {
        "key": "allowFractionalQuantity",
        "label": "Cho phép số lẻ",
        "type": "boolean",
        "required": false
      },
      {
        "key": "minStock",
        "label": "Tồn tối thiểu",
        "type": "decimal",
        "required": false,
        "min": "0"
      },
      {
        "key": "isActive",
        "label": "Trạng thái",
        "type": "boolean",
        "required": false
      },
      {
        "key": "createdAt",
        "label": "Ngày tạo",
        "type": "datetime",
        "required": false
      }
    ],
    "writeFields": [
      {
        "key": "code",
        "label": "Mã",
        "type": "text",
        "required": true,
        "max": 60
      },
      {
        "key": "name",
        "label": "Tên",
        "type": "text",
        "required": true,
        "max": 255
      },
      {
        "key": "categoryId",
        "label": "Nhóm vật tư",
        "type": "number",
        "required": false,
        "ref": "categories"
      },
      {
        "key": "baseUnitId",
        "label": "Đơn vị cơ sở",
        "type": "number",
        "required": true,
        "ref": "units"
      },
      {
        "key": "trackLot",
        "label": "Theo dõi lô",
        "type": "boolean",
        "required": true
      },
      {
        "key": "trackSerial",
        "label": "Theo dõi serial",
        "type": "boolean",
        "required": true
      },
      {
        "key": "trackExpiry",
        "label": "Theo dõi hạn dùng",
        "type": "boolean",
        "required": true
      },
      {
        "key": "allowFractionalQuantity",
        "label": "Cho phép số lẻ",
        "type": "boolean",
        "required": true
      },
      {
        "key": "minStock",
        "label": "Tồn tối thiểu",
        "type": "decimal",
        "required": true,
        "min": "0"
      },
      {
        "key": "isActive",
        "label": "Trạng thái",
        "type": "boolean",
        "required": true
      }
    ],
    "ids": [
      "id"
    ]
  },
  {
    "key": "categories",
    "cls": "MaterialCategory",
    "title": "Nhóm vật tư",
    "description": "Phân loại vật tư để tổ chức danh mục rõ ràng.",
    "group": "catalog",
    "columns": [
      "code",
      "name"
    ],
    "fields": [
      {
        "key": "id",
        "label": "ID",
        "type": "number",
        "required": false
      },
      {
        "key": "code",
        "label": "Mã",
        "type": "text",
        "required": false
      },
      {
        "key": "name",
        "label": "Tên",
        "type": "text",
        "required": false
      }
    ],
    "writeFields": [
      {
        "key": "code",
        "label": "Mã",
        "type": "text",
        "required": true,
        "max": 40
      },
      {
        "key": "name",
        "label": "Tên",
        "type": "text",
        "required": true,
        "max": 150
      }
    ],
    "ids": [
      "id"
    ]
  },
  {
    "key": "units",
    "cls": "UnitOfMeasure",
    "title": "Đơn vị tính",
    "description": "Quản lý các đơn vị sử dụng trong kho.",
    "group": "catalog",
    "columns": [
      "code",
      "name"
    ],
    "fields": [
      {
        "key": "id",
        "label": "ID",
        "type": "number",
        "required": false
      },
      {
        "key": "code",
        "label": "Mã",
        "type": "text",
        "required": false
      },
      {
        "key": "name",
        "label": "Tên",
        "type": "text",
        "required": false
      }
    ],
    "writeFields": [
      {
        "key": "code",
        "label": "Mã",
        "type": "text",
        "required": true,
        "max": 30
      },
      {
        "key": "name",
        "label": "Tên",
        "type": "text",
        "required": true,
        "max": 100
      }
    ],
    "ids": [
      "id"
    ]
  },
  {
    "key": "material-units",
    "cls": "MaterialUnit",
    "title": "Quy đổi đơn vị",
    "description": "Thiết lập hệ số quy đổi theo từng vật tư.",
    "group": "catalog",
    "columns": [
      "materialId",
      "unitId",
      "conversionFactor",
      "allowReceipt",
      "allowIssue"
    ],
    "fields": [
      {
        "key": "materialId",
        "label": "Vật tư",
        "type": "number",
        "required": false,
        "ref": "materials"
      },
      {
        "key": "unitId",
        "label": "Đơn vị tính",
        "type": "number",
        "required": false,
        "ref": "units"
      },
      {
        "key": "conversionFactor",
        "label": "Hệ số quy đổi",
        "type": "decimal",
        "required": false,
        "min": "0.000001"
      },
      {
        "key": "allowReceipt",
        "label": "Cho phép nhập",
        "type": "boolean",
        "required": false
      },
      {
        "key": "allowIssue",
        "label": "Cho phép xuất",
        "type": "boolean",
        "required": false
      }
    ],
    "writeFields": [
      {
        "key": "materialId",
        "label": "Vật tư",
        "type": "number",
        "required": true,
        "ref": "materials"
      },
      {
        "key": "unitId",
        "label": "Đơn vị tính",
        "type": "number",
        "required": true,
        "ref": "units"
      },
      {
        "key": "conversionFactor",
        "label": "Hệ số quy đổi",
        "type": "decimal",
        "required": true,
        "min": "0.000001"
      },
      {
        "key": "allowReceipt",
        "label": "Cho phép nhập",
        "type": "boolean",
        "required": true
      },
      {
        "key": "allowIssue",
        "label": "Cho phép xuất",
        "type": "boolean",
        "required": true
      }
    ],
    "ids": [
      "materialId",
      "unitId"
    ]
  },
  {
    "key": "suppliers",
    "cls": "Supplier",
    "title": "Nhà cung cấp",
    "description": "Thông tin đối tác cung ứng vật tư.",
    "group": "partners",
    "columns": [
      "code",
      "name",
      "phone",
      "taxCode",
      "isActive"
    ],
    "fields": [
      {
        "key": "id",
        "label": "ID",
        "type": "number",
        "required": false
      },
      {
        "key": "code",
        "label": "Mã",
        "type": "text",
        "required": false
      },
      {
        "key": "name",
        "label": "Tên",
        "type": "text",
        "required": false
      },
      {
        "key": "phone",
        "label": "Điện thoại",
        "type": "text",
        "required": false
      },
      {
        "key": "address",
        "label": "Địa chỉ",
        "type": "text",
        "required": false
      },
      {
        "key": "taxCode",
        "label": "Mã số thuế",
        "type": "text",
        "required": false
      },
      {
        "key": "isActive",
        "label": "Trạng thái",
        "type": "boolean",
        "required": false
      }
    ],
    "writeFields": [
      {
        "key": "code",
        "label": "Mã",
        "type": "text",
        "required": true,
        "max": 60
      },
      {
        "key": "name",
        "label": "Tên",
        "type": "text",
        "required": true,
        "max": 255
      },
      {
        "key": "phone",
        "label": "Điện thoại",
        "type": "text",
        "required": false,
        "max": 30
      },
      {
        "key": "address",
        "label": "Địa chỉ",
        "type": "text",
        "required": false,
        "max": 500
      },
      {
        "key": "taxCode",
        "label": "Mã số thuế",
        "type": "text",
        "required": false,
        "max": 30
      },
      {
        "key": "isActive",
        "label": "Trạng thái",
        "type": "boolean",
        "required": true
      }
    ],
    "ids": [
      "id"
    ]
  },
  {
    "key": "recipients",
    "cls": "Recipient",
    "title": "Người nhận",
    "description": "Người nhận vật tư và bộ phận sử dụng.",
    "group": "partners",
    "columns": [
      "code",
      "fullName",
      "department",
      "phone",
      "isActive"
    ],
    "fields": [
      {
        "key": "id",
        "label": "ID",
        "type": "number",
        "required": false
      },
      {
        "key": "code",
        "label": "Mã",
        "type": "text",
        "required": false
      },
      {
        "key": "fullName",
        "label": "Họ và tên",
        "type": "text",
        "required": false
      },
      {
        "key": "department",
        "label": "Bộ phận",
        "type": "text",
        "required": false
      },
      {
        "key": "phone",
        "label": "Điện thoại",
        "type": "text",
        "required": false
      },
      {
        "key": "isActive",
        "label": "Trạng thái",
        "type": "boolean",
        "required": false
      }
    ],
    "writeFields": [
      {
        "key": "code",
        "label": "Mã",
        "type": "text",
        "required": true,
        "max": 60
      },
      {
        "key": "fullName",
        "label": "Họ và tên",
        "type": "text",
        "required": true,
        "max": 200
      },
      {
        "key": "department",
        "label": "Bộ phận",
        "type": "text",
        "required": false,
        "max": 200
      },
      {
        "key": "phone",
        "label": "Điện thoại",
        "type": "text",
        "required": false,
        "max": 30
      },
      {
        "key": "isActive",
        "label": "Trạng thái",
        "type": "boolean",
        "required": true
      }
    ],
    "ids": [
      "id"
    ]
  },
  {
    "key": "documents",
    "cls": "StockDocument",
    "title": "Phiếu kho",
    "description": "Tra cứu phiếu nhập, xuất và điều chỉnh.",
    "group": "documents",
    "columns": [
      "documentNo",
      "documentType",
      "documentDate",
      "status",
      "createdBy"
    ],
    "fields": [
      {
        "key": "id",
        "label": "ID",
        "type": "number",
        "required": false
      },
      {
        "key": "documentNo",
        "label": "Số phiếu",
        "type": "text",
        "required": false
      },
      {
        "key": "documentType",
        "label": "Loại phiếu",
        "type": "text",
        "required": false
      },
      {
        "key": "status",
        "label": "Trạng thái",
        "type": "text",
        "required": false
      },
      {
        "key": "documentDate",
        "label": "Ngày phiếu",
        "type": "date",
        "required": false
      },
      {
        "key": "supplierId",
        "label": "Nhà cung cấp",
        "type": "number",
        "required": false,
        "ref": "suppliers"
      },
      {
        "key": "recipientId",
        "label": "Người nhận",
        "type": "number",
        "required": false,
        "ref": "recipients"
      },
      {
        "key": "recipientNameSnapshot",
        "label": "Tên người nhận trên phiếu",
        "type": "text",
        "required": false
      },
      {
        "key": "recipientDepartmentSnapshot",
        "label": "Bộ phận trên phiếu",
        "type": "text",
        "required": false
      },
      {
        "key": "reason",
        "label": "Lý do",
        "type": "text",
        "required": false
      },
      {
        "key": "createdBy",
        "label": "Người tạo",
        "type": "number",
        "required": false,
        "ref": "users"
      },
      {
        "key": "submittedAt",
        "label": "Ngày gửi duyệt",
        "type": "datetime",
        "required": false
      },
      {
        "key": "approvedBy",
        "label": "Người duyệt",
        "type": "number",
        "required": false,
        "ref": "users"
      },
      {
        "key": "approvedAt",
        "label": "Ngày duyệt",
        "type": "datetime",
        "required": false
      },
      {
        "key": "postedBy",
        "label": "Người ghi sổ",
        "type": "number",
        "required": false,
        "ref": "users"
      },
      {
        "key": "postedAt",
        "label": "Ngày ghi sổ",
        "type": "datetime",
        "required": false
      },
      {
        "key": "revision",
        "label": "Phiên bản phiếu",
        "type": "number",
        "required": false
      },
      {
        "key": "createdAt",
        "label": "Ngày tạo",
        "type": "datetime",
        "required": false
      },
      {
        "key": "updatedAt",
        "label": "Cập nhật",
        "type": "datetime",
        "required": false
      }
    ],
    "writeFields": [],
    "ids": [
      "id"
    ]
  },
  {
    "key": "document-lines",
    "cls": "StockDocumentLine",
    "title": "Chi tiết phiếu",
    "description": "Các dòng vật tư trên phiếu và số lượng quy đổi.",
    "group": "documents",
    "columns": [
      "documentId",
      "lineNo",
      "materialId",
      "quantity",
      "baseQuantity"
    ],
    "fields": [
      {
        "key": "id",
        "label": "ID",
        "type": "number",
        "required": false
      },
      {
        "key": "documentId",
        "label": "Phiếu kho",
        "type": "number",
        "required": false,
        "ref": "documents"
      },
      {
        "key": "lineNo",
        "label": "Dòng",
        "type": "number",
        "required": false
      },
      {
        "key": "materialId",
        "label": "Vật tư",
        "type": "number",
        "required": false,
        "ref": "materials"
      },
      {
        "key": "unitId",
        "label": "Đơn vị tính",
        "type": "number",
        "required": false,
        "ref": "units"
      },
      {
        "key": "quantity",
        "label": "Số lượng",
        "type": "decimal",
        "required": false
      },
      {
        "key": "conversionFactor",
        "label": "Hệ số quy đổi",
        "type": "decimal",
        "required": false,
        "min": "0.000001"
      },
      {
        "key": "baseQuantity",
        "label": "Số lượng cơ sở",
        "type": "decimal",
        "required": false
      },
      {
        "key": "inputUnitPrice",
        "label": "Đơn giá nhập",
        "type": "decimal",
        "required": false
      },
      {
        "key": "note",
        "label": "Ghi chú",
        "type": "text",
        "required": false
      }
    ],
    "writeFields": [],
    "ids": [
      "id"
    ]
  },
  {
    "key": "line-allocations",
    "cls": "StockLineAllocation",
    "title": "Phân bổ lô / serial",
    "description": "Chi tiết lô, serial tương ứng với từng dòng phiếu.",
    "group": "documents",
    "columns": [
      "lineId",
      "materialId",
      "lotId",
      "serialId",
      "baseQuantity"
    ],
    "fields": [
      {
        "key": "id",
        "label": "ID",
        "type": "number",
        "required": false
      },
      {
        "key": "lineId",
        "label": "Dòng phiếu",
        "type": "number",
        "required": false,
        "ref": "document-lines"
      },
      {
        "key": "materialId",
        "label": "Vật tư",
        "type": "number",
        "required": false,
        "ref": "materials"
      },
      {
        "key": "lotId",
        "label": "Lô vật tư",
        "type": "number",
        "required": false,
        "ref": "lots"
      },
      {
        "key": "serialId",
        "label": "Serial",
        "type": "number",
        "required": false,
        "ref": "serials"
      },
      {
        "key": "baseQuantity",
        "label": "Số lượng cơ sở",
        "type": "decimal",
        "required": false
      }
    ],
    "writeFields": [],
    "ids": [
      "id"
    ]
  },
  {
    "key": "document-actions",
    "cls": "DocumentAction",
    "title": "Lịch sử phiếu",
    "description": "Theo dõi các lần gửi duyệt, phê duyệt và ghi sổ.",
    "group": "documents",
    "columns": [
      "documentId",
      "action",
      "actorId",
      "actedAt"
    ],
    "fields": [
      {
        "key": "id",
        "label": "ID",
        "type": "number",
        "required": false
      },
      {
        "key": "documentId",
        "label": "Phiếu kho",
        "type": "number",
        "required": false,
        "ref": "documents"
      },
      {
        "key": "revision",
        "label": "Phiên bản phiếu",
        "type": "number",
        "required": false
      },
      {
        "key": "action",
        "label": "Hành động",
        "type": "text",
        "required": false
      },
      {
        "key": "actorId",
        "label": "Người thực hiện",
        "type": "number",
        "required": false,
        "ref": "users"
      },
      {
        "key": "comment",
        "label": "Nhận xét",
        "type": "text",
        "required": false
      },
      {
        "key": "actedAt",
        "label": "Thời điểm",
        "type": "datetime",
        "required": false
      }
    ],
    "writeFields": [],
    "ids": [
      "id"
    ]
  },
  {
    "key": "balances",
    "cls": "InventoryBalance",
    "title": "Tồn kho",
    "description": "Số dư tồn kho và giá trị theo từng vật tư.",
    "group": "inventory",
    "columns": [
      "materialId",
      "quantityOnHand",
      "inventoryValue",
      "updatedAt"
    ],
    "fields": [
      {
        "key": "materialId",
        "label": "Vật tư",
        "type": "number",
        "required": false,
        "ref": "materials"
      },
      {
        "key": "quantityOnHand",
        "label": "Tồn hiện tại",
        "type": "decimal",
        "required": false
      },
      {
        "key": "inventoryValue",
        "label": "Giá trị tồn",
        "type": "decimal",
        "required": false
      },
      {
        "key": "version",
        "label": "Phiên bản",
        "type": "number",
        "required": false
      },
      {
        "key": "updatedAt",
        "label": "Cập nhật",
        "type": "datetime",
        "required": false
      }
    ],
    "writeFields": [],
    "ids": [
      "materialId"
    ]
  },
  {
    "key": "movements",
    "cls": "StockMovement",
    "title": "Sổ biến động",
    "description": "Biến động tăng, giảm đã được ghi sổ.",
    "group": "inventory",
    "columns": [
      "materialId",
      "direction",
      "baseQuantity",
      "unitCost",
      "valueAmount",
      "postedAt"
    ],
    "fields": [
      {
        "key": "id",
        "label": "ID",
        "type": "number",
        "required": false
      },
      {
        "key": "allocationId",
        "label": "Phân bổ",
        "type": "number",
        "required": false,
        "ref": "line-allocations"
      },
      {
        "key": "materialId",
        "label": "Vật tư",
        "type": "number",
        "required": false,
        "ref": "materials"
      },
      {
        "key": "direction",
        "label": "Chiều biến động",
        "type": "number",
        "required": false
      },
      {
        "key": "baseQuantity",
        "label": "Số lượng cơ sở",
        "type": "decimal",
        "required": false
      },
      {
        "key": "unitCost",
        "label": "Giá vốn đơn vị",
        "type": "decimal",
        "required": false
      },
      {
        "key": "valueAmount",
        "label": "Giá trị",
        "type": "decimal",
        "required": false
      },
      {
        "key": "costingMethod",
        "label": "Phương pháp giá",
        "type": "text",
        "required": false
      },
      {
        "key": "postedAt",
        "label": "Ngày ghi sổ",
        "type": "datetime",
        "required": false
      },
      {
        "key": "postedBy",
        "label": "Người ghi sổ",
        "type": "number",
        "required": false,
        "ref": "users"
      }
    ],
    "writeFields": [],
    "ids": [
      "id"
    ]
  },
  {
    "key": "cost-allocations",
    "cls": "CostAllocation",
    "title": "Phân bổ giá vốn",
    "description": "Liên kết giá vốn giữa biến động xuất và nhập.",
    "group": "inventory",
    "columns": [
      "issueMovementId",
      "receiptMovementId",
      "baseQuantity",
      "valueAmount"
    ],
    "fields": [
      {
        "key": "id",
        "label": "ID",
        "type": "number",
        "required": false
      },
      {
        "key": "issueMovementId",
        "label": "Biến động xuất",
        "type": "number",
        "required": false,
        "ref": "movements"
      },
      {
        "key": "receiptMovementId",
        "label": "Biến động nhập",
        "type": "number",
        "required": false,
        "ref": "movements"
      },
      {
        "key": "baseQuantity",
        "label": "Số lượng cơ sở",
        "type": "decimal",
        "required": false
      },
      {
        "key": "valueAmount",
        "label": "Giá trị",
        "type": "decimal",
        "required": false
      }
    ],
    "writeFields": [],
    "ids": [
      "id"
    ]
  },
  {
    "key": "lots",
    "cls": "MaterialLot",
    "title": "Lô vật tư",
    "description": "Theo dõi mã lô, ngày sản xuất và hạn sử dụng.",
    "group": "trace",
    "columns": [
      "materialId",
      "lotCode",
      "manufacturedOn",
      "expiresOn"
    ],
    "fields": [
      {
        "key": "id",
        "label": "ID",
        "type": "number",
        "required": false
      },
      {
        "key": "materialId",
        "label": "Vật tư",
        "type": "number",
        "required": false,
        "ref": "materials"
      },
      {
        "key": "lotCode",
        "label": "Mã lô",
        "type": "text",
        "required": false
      },
      {
        "key": "manufacturedOn",
        "label": "Ngày sản xuất",
        "type": "date",
        "required": false
      },
      {
        "key": "expiresOn",
        "label": "Hạn sử dụng",
        "type": "date",
        "required": false
      }
    ],
    "writeFields": [
      {
        "key": "materialId",
        "label": "Vật tư",
        "type": "number",
        "required": true,
        "ref": "materials"
      },
      {
        "key": "lotCode",
        "label": "Mã lô",
        "type": "text",
        "required": true,
        "max": 100
      },
      {
        "key": "manufacturedOn",
        "label": "Ngày sản xuất",
        "type": "date",
        "required": false
      },
      {
        "key": "expiresOn",
        "label": "Hạn sử dụng",
        "type": "date",
        "required": false
      }
    ],
    "ids": [
      "id"
    ]
  },
  {
    "key": "serials",
    "cls": "MaterialSerial",
    "title": "Serial vật tư",
    "description": "Tra cứu từng vật tư theo số serial.",
    "group": "trace",
    "columns": [
      "materialId",
      "lotId",
      "serialNumber",
      "expiresOn"
    ],
    "fields": [
      {
        "key": "id",
        "label": "ID",
        "type": "number",
        "required": false
      },
      {
        "key": "materialId",
        "label": "Vật tư",
        "type": "number",
        "required": false,
        "ref": "materials"
      },
      {
        "key": "lotId",
        "label": "Lô vật tư",
        "type": "number",
        "required": false,
        "ref": "lots"
      },
      {
        "key": "serialNumber",
        "label": "Số serial",
        "type": "text",
        "required": false
      },
      {
        "key": "expiresOn",
        "label": "Hạn sử dụng",
        "type": "date",
        "required": false
      }
    ],
    "writeFields": [
      {
        "key": "materialId",
        "label": "Vật tư",
        "type": "number",
        "required": true,
        "ref": "materials"
      },
      {
        "key": "lotId",
        "label": "Lô vật tư",
        "type": "number",
        "required": false,
        "ref": "lots"
      },
      {
        "key": "serialNumber",
        "label": "Số serial",
        "type": "text",
        "required": true,
        "max": 150
      },
      {
        "key": "expiresOn",
        "label": "Hạn sử dụng",
        "type": "date",
        "required": false
      }
    ],
    "ids": [
      "id"
    ]
  },
  {
    "key": "users",
    "cls": "AppUser",
    "title": "Người dùng",
    "description": "Xem và sửa họ tên, trạng thái của tài khoản không có quyền ADMIN.",
    "group": "admin",
    "columns": [
      "username",
      "fullName",
      "isActive",
      "createdAt"
    ],
    "fields": [
      {
        "key": "id",
        "label": "ID",
        "type": "number",
        "required": false
      },
      {
        "key": "username",
        "label": "Tên đăng nhập",
        "type": "text",
        "required": false
      },
      {
        "key": "fullName",
        "label": "Họ và tên",
        "type": "text",
        "required": false
      },
      {
        "key": "isActive",
        "label": "Trạng thái",
        "type": "boolean",
        "required": false
      },
      {
        "key": "createdAt",
        "label": "Ngày tạo",
        "type": "datetime",
        "required": false
      }
    ],
    "writeFields": [
      {
        "key": "fullName",
        "label": "Họ và tên",
        "type": "text",
        "required": true,
        "max": 200
      },
      {
        "key": "isActive",
        "label": "Đang sử dụng",
        "type": "boolean",
        "required": true
      }
    ],
    "ids": [
      "id"
    ],
    "editOnly": true
  },
  {
    "key": "roles",
    "cls": "AppRole",
    "title": "Vai trò",
    "description": "Các vai trò được định nghĩa trong hệ thống.",
    "group": "admin",
    "columns": [
      "code",
      "name"
    ],
    "fields": [
      {
        "key": "id",
        "label": "ID",
        "type": "number",
        "required": false
      },
      {
        "key": "code",
        "label": "Mã",
        "type": "text",
        "required": false
      },
      {
        "key": "name",
        "label": "Tên",
        "type": "text",
        "required": false
      }
    ],
    "writeFields": [],
    "ids": [
      "id"
    ]
  },
  {
    "key": "user-roles",
    "cls": "UserRole",
    "title": "Phân quyền",
    "description": "Tra cứu vai trò được gán cho từng tài khoản.",
    "group": "admin",
    "columns": [
      "userId",
      "roleId"
    ],
    "fields": [
      {
        "key": "userId",
        "label": "Người dùng",
        "type": "number",
        "required": false,
        "ref": "users"
      },
      {
        "key": "roleId",
        "label": "Vai trò",
        "type": "number",
        "required": false,
        "ref": "roles"
      }
    ],
    "writeFields": [],
    "ids": [
      "userId",
      "roleId"
    ]
  },
  {
    "key": "inventory-policies",
    "cls": "InventoryPolicy",
    "title": "Chính sách kho",
    "description": "Phương pháp tính giá và thời điểm ghi sổ.",
    "group": "admin",
    "columns": [
      "costingMethod",
      "postingMode"
    ],
    "fields": [
      {
        "key": "id",
        "label": "ID",
        "type": "number",
        "required": false
      },
      {
        "key": "costingMethod",
        "label": "Phương pháp giá",
        "type": "text",
        "required": false
      },
      {
        "key": "postingMode",
        "label": "Thời điểm ghi sổ",
        "type": "text",
        "required": false
      }
    ],
    "writeFields": [],
    "ids": [
      "id"
    ]
  },
  {
    "key": "audit-logs",
    "cls": "AuditLog",
    "title": "Nhật ký hệ thống",
    "description": "Tra cứu người thực hiện và lịch sử thay đổi.",
    "group": "admin",
    "columns": [
      "entityType",
      "entityId",
      "action",
      "actorId",
      "createdAt"
    ],
    "fields": [
      {
        "key": "id",
        "label": "ID",
        "type": "number",
        "required": false
      },
      {
        "key": "actorId",
        "label": "Người thực hiện",
        "type": "number",
        "required": false,
        "ref": "users"
      },
      {
        "key": "entityType",
        "label": "Đối tượng",
        "type": "text",
        "required": false
      },
      {
        "key": "entityId",
        "label": "ID đối tượng",
        "type": "number",
        "required": false
      },
      {
        "key": "action",
        "label": "Hành động",
        "type": "text",
        "required": false
      },
      {
        "key": "createdAt",
        "label": "Ngày tạo",
        "type": "datetime",
        "required": false
      }
    ],
    "writeFields": [],
    "ids": [
      "id"
    ]
  }
];
