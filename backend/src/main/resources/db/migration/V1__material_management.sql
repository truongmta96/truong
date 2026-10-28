-- QUAN LY VAT TU - MySQL 8.0.16+ / 8.4, InnoDB, VND.
-- Chay mot lan tren schema moi. Khong xoa hay ghi de bang da co.
-- Thoi gian DATETIME quy uoc UTC; giao dien doi sang Asia/Ho_Chi_Minh.
-- DDL KHONG tu dong duyet phieu, tinh gia hay cap nhat ton.


CREATE TABLE app_user (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(150) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL COMMENT 'Chi luu hash, khong luu mat khau tho',
  full_name VARCHAR(200) NOT NULL,
  is_active BOOLEAN NOT NULL DEFAULT TRUE,
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
) ENGINE=InnoDB;

CREATE TABLE app_role (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  code VARCHAR(40) NOT NULL UNIQUE,
  name VARCHAR(100) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE user_role (
  user_id BIGINT UNSIGNED NOT NULL,
  role_id BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY(user_id, role_id),
  FOREIGN KEY(user_id) REFERENCES app_user(id),
  FOREIGN KEY(role_id) REFERENCES app_role(id)
) ENGINE=InnoDB;

CREATE TABLE unit_of_measure (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  code VARCHAR(30) NOT NULL UNIQUE,
  name VARCHAR(100) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE material_category (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  code VARCHAR(40) NOT NULL UNIQUE,
  name VARCHAR(150) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE material (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  code VARCHAR(60) NOT NULL UNIQUE,
  name VARCHAR(255) NOT NULL,
  category_id BIGINT UNSIGNED NULL,
  base_unit_id BIGINT UNSIGNED NOT NULL,
  track_lot BOOLEAN NOT NULL DEFAULT FALSE,
  track_serial BOOLEAN NOT NULL DEFAULT FALSE,
  track_expiry BOOLEAN NOT NULL DEFAULT FALSE,
  allow_fractional_quantity BOOLEAN NOT NULL DEFAULT FALSE,
  min_stock DECIMAL(20,6) NOT NULL DEFAULT 0,
  is_active BOOLEAN NOT NULL DEFAULT TRUE,
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  FOREIGN KEY(category_id) REFERENCES material_category(id),
  FOREIGN KEY(base_unit_id) REFERENCES unit_of_measure(id),
  CHECK (min_stock >= 0)
) ENGINE=InnoDB;

CREATE TABLE material_unit (
  material_id BIGINT UNSIGNED NOT NULL,
  unit_id BIGINT UNSIGNED NOT NULL,
  conversion_factor DECIMAL(20,6) NOT NULL COMMENT '1 don vi = N don vi co so',
  allow_receipt BOOLEAN NOT NULL DEFAULT TRUE,
  allow_issue BOOLEAN NOT NULL DEFAULT TRUE,
  PRIMARY KEY(material_id, unit_id),
  FOREIGN KEY(material_id) REFERENCES material(id),
  FOREIGN KEY(unit_id) REFERENCES unit_of_measure(id),
  CHECK (conversion_factor > 0)
) ENGINE=InnoDB;

CREATE TABLE supplier (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  code VARCHAR(60) NOT NULL UNIQUE,
  name VARCHAR(255) NOT NULL,
  phone VARCHAR(30),
  address VARCHAR(500),
  tax_code VARCHAR(30),
  is_active BOOLEAN NOT NULL DEFAULT TRUE
) ENGINE=InnoDB;

CREATE TABLE recipient (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  code VARCHAR(60) NOT NULL UNIQUE,
  full_name VARCHAR(200) NOT NULL,
  department VARCHAR(200),
  phone VARCHAR(30),
  is_active BOOLEAN NOT NULL DEFAULT TRUE
) ENGINE=InnoDB;

CREATE TABLE inventory_policy (
  id TINYINT UNSIGNED PRIMARY KEY,
  costing_method ENUM('UNSET','MOVING_AVERAGE','SPECIFIC') NOT NULL DEFAULT 'UNSET',
  posting_mode ENUM('UNSET','ON_APPROVAL','ON_CONFIRMATION') NOT NULL DEFAULT 'UNSET',
  CHECK(id = 1)
) ENGINE=InnoDB;

CREATE TABLE material_lot (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  material_id BIGINT UNSIGNED NOT NULL,
  lot_code VARCHAR(100) NOT NULL,
  manufactured_on DATE,
  expires_on DATE,
  UNIQUE(material_id, lot_code),
  UNIQUE(id, material_id),
  INDEX idx_lot_expiry(expires_on),
  FOREIGN KEY(material_id) REFERENCES material(id),
  CHECK(manufactured_on IS NULL OR expires_on IS NULL OR expires_on >= manufactured_on)
) ENGINE=InnoDB;

CREATE TABLE material_serial (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  material_id BIGINT UNSIGNED NOT NULL,
  lot_id BIGINT UNSIGNED NULL,
  serial_number VARCHAR(150) COLLATE utf8mb4_0900_as_cs NOT NULL,
  expires_on DATE NULL COMMENT 'Han rieng neu quan ly theo tung chiec',
  UNIQUE(material_id, serial_number),
  UNIQUE(id, material_id),
  FOREIGN KEY(material_id) REFERENCES material(id),
  FOREIGN KEY(lot_id, material_id) REFERENCES material_lot(id, material_id)
) ENGINE=InnoDB;

CREATE TABLE stock_document (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  document_no VARCHAR(60) NOT NULL UNIQUE,
  document_type ENUM('OPENING','RECEIPT','ISSUE','ADJUST_IN','ADJUST_OUT') NOT NULL,
  status ENUM('DRAFT','PENDING','APPROVED','REJECTED','POSTED','CANCELLED') NOT NULL DEFAULT 'DRAFT',
  document_date DATE NOT NULL,
  supplier_id BIGINT UNSIGNED NULL,
  recipient_id BIGINT UNSIGNED NULL,
  recipient_name_snapshot VARCHAR(200) NULL,
  recipient_department_snapshot VARCHAR(200) NULL,
  reason VARCHAR(1000),
  created_by BIGINT UNSIGNED NOT NULL,
  submitted_at DATETIME(6),
  approved_by BIGINT UNSIGNED NULL,
  approved_at DATETIME(6),
  posted_by BIGINT UNSIGNED NULL,
  posted_at DATETIME(6),
  revision INT UNSIGNED NOT NULL DEFAULT 1,
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  INDEX idx_doc_date(document_type, document_date),
  INDEX idx_doc_status(status, created_at),
  FOREIGN KEY(supplier_id) REFERENCES supplier(id),
  FOREIGN KEY(recipient_id) REFERENCES recipient(id),
  FOREIGN KEY(created_by) REFERENCES app_user(id),
  FOREIGN KEY(approved_by) REFERENCES app_user(id),
  FOREIGN KEY(posted_by) REFERENCES app_user(id),
  CHECK(document_type <> 'ISSUE' OR recipient_id IS NOT NULL),
  CHECK(approved_by IS NULL OR approved_by <> created_by),
  CHECK(status NOT IN ('APPROVED','POSTED') OR (approved_by IS NOT NULL AND approved_at IS NOT NULL)),
  CHECK(status <> 'POSTED' OR (posted_by IS NOT NULL AND posted_at IS NOT NULL))
) ENGINE=InnoDB;

CREATE TABLE stock_document_line (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  document_id BIGINT UNSIGNED NOT NULL,
  line_no INT UNSIGNED NOT NULL,
  material_id BIGINT UNSIGNED NOT NULL,
  unit_id BIGINT UNSIGNED NOT NULL,
  quantity DECIMAL(20,6) NOT NULL,
  conversion_factor DECIMAL(20,6) NOT NULL COMMENT 'Ty le chup tai thoi diem lap phieu',
  base_quantity DECIMAL(30,6) GENERATED ALWAYS AS (ROUND(quantity * conversion_factor, 6)) STORED,
  input_unit_price DECIMAL(24,6) NULL COMMENT 'Gia nhap tren don vi giao dich, VND',
  note VARCHAR(500),
  UNIQUE(document_id, line_no),
  UNIQUE(id, material_id),
  FOREIGN KEY(document_id) REFERENCES stock_document(id),
  FOREIGN KEY(material_id, unit_id) REFERENCES material_unit(material_id, unit_id),
  CHECK(line_no > 0),
  CHECK(quantity > 0 AND conversion_factor > 0),
  CHECK(input_unit_price IS NULL OR input_unit_price >= 0)
) ENGINE=InnoDB;

CREATE TABLE stock_line_allocation (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  line_id BIGINT UNSIGNED NOT NULL,
  material_id BIGINT UNSIGNED NOT NULL,
  lot_id BIGINT UNSIGNED NULL,
  serial_id BIGINT UNSIGNED NULL,
  base_quantity DECIMAL(20,6) NOT NULL,
  UNIQUE(id, material_id),
  UNIQUE(line_id, serial_id),
  FOREIGN KEY(line_id, material_id) REFERENCES stock_document_line(id, material_id),
  FOREIGN KEY(lot_id, material_id) REFERENCES material_lot(id, material_id),
  FOREIGN KEY(serial_id, material_id) REFERENCES material_serial(id, material_id),
  CHECK(base_quantity > 0),
  CHECK(serial_id IS NULL OR base_quantity = 1)
) ENGINE=InnoDB;

CREATE TABLE document_action (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  document_id BIGINT UNSIGNED NOT NULL,
  revision INT UNSIGNED NOT NULL,
  action ENUM('SUBMIT','WITHDRAW','APPROVE','REJECT','POST','CANCEL') NOT NULL,
  actor_id BIGINT UNSIGNED NOT NULL,
  comment VARCHAR(1000),
  acted_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  INDEX idx_action_doc(document_id, acted_at),
  FOREIGN KEY(document_id) REFERENCES stock_document(id),
  FOREIGN KEY(actor_id) REFERENCES app_user(id)
) ENGINE=InnoDB;

CREATE TABLE stock_movement (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  allocation_id BIGINT UNSIGNED NOT NULL UNIQUE COMMENT 'Chong ghi so trung cung phan bo',
  material_id BIGINT UNSIGNED NOT NULL,
  direction SMALLINT NOT NULL COMMENT '1 tang, -1 giam',
  base_quantity DECIMAL(20,6) NOT NULL,
  unit_cost DECIMAL(24,6) NOT NULL COMMENT 'Gia tren don vi co so tai luc ghi so',
  value_amount DECIMAL(30,6) NOT NULL COMMENT 'Gia tri da tinh/lam tron theo chinh sach',
  costing_method ENUM('MOVING_AVERAGE','SPECIFIC') NOT NULL,
  posted_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  posted_by BIGINT UNSIGNED NOT NULL,
  INDEX idx_movement_material(material_id, posted_at),
  FOREIGN KEY(allocation_id, material_id) REFERENCES stock_line_allocation(id, material_id),
  FOREIGN KEY(posted_by) REFERENCES app_user(id),
  CHECK(direction IN (-1,1)),
  CHECK(base_quantity > 0 AND unit_cost >= 0 AND value_amount >= 0)
) ENGINE=InnoDB;

CREATE TABLE inventory_balance (
  material_id BIGINT UNSIGNED PRIMARY KEY,
  quantity_on_hand DECIMAL(24,6) NOT NULL DEFAULT 0,
  inventory_value DECIMAL(30,6) NOT NULL DEFAULT 0,
  version BIGINT UNSIGNED NOT NULL DEFAULT 0,
  updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  FOREIGN KEY(material_id) REFERENCES material(id),
  CHECK(quantity_on_hand >= 0 AND inventory_value >= 0),
  CHECK(quantity_on_hand <> 0 OR inventory_value = 0)
) ENGINE=InnoDB;

CREATE TABLE cost_allocation (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  issue_movement_id BIGINT UNSIGNED NOT NULL,
  receipt_movement_id BIGINT UNSIGNED NOT NULL,
  base_quantity DECIMAL(20,6) NOT NULL,
  value_amount DECIMAL(30,6) NOT NULL,
  UNIQUE(issue_movement_id, receipt_movement_id),
  FOREIGN KEY(issue_movement_id) REFERENCES stock_movement(id),
  FOREIGN KEY(receipt_movement_id) REFERENCES stock_movement(id),
  CHECK(issue_movement_id <> receipt_movement_id),
  CHECK(base_quantity > 0 AND value_amount >= 0)
) ENGINE=InnoDB;

CREATE TABLE audit_log (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  actor_id BIGINT UNSIGNED NULL,
  entity_type VARCHAR(80) NOT NULL,
  entity_id BIGINT UNSIGNED NOT NULL,
  action VARCHAR(40) NOT NULL,
  old_data JSON,
  new_data JSON,
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  INDEX idx_audit_entity(entity_type, entity_id, created_at),
  FOREIGN KEY(actor_id) REFERENCES app_user(id)
) ENGINE=InnoDB;

-- Ton theo vat tu: lay tu so bien dong da ghi so, khong lay phieu nhap.
CREATE VIEW v_material_stock AS
SELECT m.id AS material_id, m.code, m.name, m.base_unit_id,
       COALESCE(SUM(s.direction * s.base_quantity), 0) AS quantity_on_hand,
       COALESCE(SUM(s.direction * s.value_amount), 0) AS inventory_value
FROM material m LEFT JOIN stock_movement s ON s.material_id = m.id
GROUP BY m.id, m.code, m.name, m.base_unit_id;

CREATE VIEW v_trace_stock AS
SELECT s.material_id, a.lot_id, a.serial_id,
       SUM(s.direction * s.base_quantity) AS quantity_on_hand
FROM stock_movement s
JOIN stock_line_allocation a ON a.id = s.allocation_id
GROUP BY s.material_id, a.lot_id, a.serial_id;

INSERT INTO inventory_policy(id) VALUES (1);
INSERT INTO app_role(code, name) VALUES
 ('ADMIN', 'Quan tri'), ('CLERK', 'Lap phieu'),
 ('APPROVER', 'Phe duyet'), ('VIEWER', 'Chi xem');
INSERT INTO unit_of_measure(code, name) VALUES ('CHIEC', 'Chiec'), ('THUNG', 'Thung');

-- BAT BUOC XU LY TRONG SERVICE PYTHON (khong duoc coi DDL la day du nghiep vu):
-- 1. Co so = 1 trong material_unit; serial dung don vi co so la chiec.
-- 2. Tong allocation = base_quantity cua line; co it nhat 1 allocation/line.
--    Vat tu khong lo/serial van co allocation voi lot_id/serial_id NULL.
-- 3. Kiem tra flag lot/serial/expiry; lot cua serial phai khop lot allocation.
--    track_expiry: han hieu luc = serial.expires_on hoac lot.expires_on.
-- 4. Phan quyen o server; nguoi lap khong tu duyet; kiem tra chuyen trang thai.
--    Chi DRAFT duoc sua, revision tang khi sua va huy hieu luc phe duyet cu.
-- 5. UNSET khong duoc ghi so. Chot phuong phap gia truoc giao dich dau tien.
-- 6. Transaction ghi so: khoa document FOR UPDATE, sau do khoa inventory_balance
--    cua cac material theo thu tu id tang dan. Tao dong balance 0 khi tao material.
--    Kiem tra POSTED, ton theo lo/serial va tong ton; tinh gia; ghi movement;
--    cap nhat balance, action va document POSTED; COMMIT cung mot transaction.
--    Moi luong ghi so phai dung cung giao thuc khoa; unique allocation chong lap.
-- 7. Receipt/opening/adjust_in direction=1; issue/adjust_out direction=-1.
--    So luong movement = allocation. Gia nhap bat buoc voi phieu tang.
--    Serial ton chi 0 hoac 1; khong xuat serial chua nhap/da xuat/hang het han.
-- 8. SPECIFIC: cost_allocation noi lan xuat voi lan nhap cung material/lo/serial;
--    khong phan bo vuot so luong da nhap; tong gia tri khop movement xuat.
--    MOVING_AVERAGE: dung tong gia tri/tong luong, xu ly sai so khi xuat het.
-- 9. Khong sua/xoa movement hoac phieu POSTED; sai thi lap phieu bu tru duoc duyet.
-- 10. Khong luu mat khau/token trong audit JSON. Khong hard delete du lieu da dung.
-- 11. Gia nhap trong file nay la gia von VND; quy tac VAT/chi phi mua phai chot rieng.
-- 12. ON_APPROVAL: duyet va ghi so cung transaction. ON_CONFIRMATION: duyet truoc,
--     chi ghi so luc xac nhan giao/nhan; kiem tra lai ton va quyen o buoc nay.
-- 13. Chua co giu cho cho phieu PENDING/APPROVED. Ton kha dung = ton thuc te.
-- 14. Django: neu dung auth.User/AbstractUser, dung bang auth cua Django thay app_user,
--     app_role/user_role; dieu chinh FK qua migrations, khong tao 2 he dang nhap.
