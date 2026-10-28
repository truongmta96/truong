/**
 * Tập trung giao tiếp HTTP và cách hiển thị dữ liệu để các trang không lặp lại xử lý cookie, CSRF, lỗi và định dạng tiếng Việt.
 */
// Token CSRF chỉ giữ trong bộ nhớ tab; cookie phiên do trình duyệt quản lý.
let csrf;
/**
 * Gửi yêu cầu JSON tới backend cùng cookie phiên. Trước thao tác ghi, lấy token CSRF và gửi đúng header; xóa token đã cũ sau khi phiên thay đổi. Lỗi 401 ở API quản lý phát tín hiệu quay về đăng nhập; không tự gửi lại thao tác ghi để tránh ghi trùng.
 */
export async function api(path, options = {}) {
  const method=(options.method || 'GET').toUpperCase();
  const headers={'Content-Type':'application/json',...options.headers};
  if(!['GET','HEAD','OPTIONS'].includes(method)){
    if(!csrf){const res=await fetch('/api/auth/csrf',{credentials:'same-origin'});if(!res.ok)throw new Error('Không thể khởi tạo phiên xác thực. Vui lòng tải lại trang.');csrf=await res.json();}
    headers[csrf.headerName]=csrf.token;
  }
  const response=await fetch(`/api/${path}`,{...options,method,headers,credentials:'same-origin'});
  let body;
  try{body=await response.json();}catch{throw new Error('Không nhận được phản hồi hợp lệ. Vui lòng kiểm tra kết nối máy chủ.');}
  if(!response.ok){
    if(response.status===401&&!path.startsWith('auth/'))window.dispatchEvent(new Event('session-expired'));
    if(response.status===403)csrf=undefined;
    const error=new Error(body.message || 'Không thể thực hiện yêu cầu.');error.fields=body.fields;error.status=response.status;throw error;
  }
  if(['auth/login','auth/logout','auth/password'].includes(path))csrf=undefined;
  return body;
}
// Dịch mã trạng thái/nghiệp vụ lưu bằng tiếng Anh sang nhãn tiếng Việt.
export const labels = {DRAFT:'Bản nháp', PENDING:'Chờ duyệt', APPROVED:'Đã duyệt', REJECTED:'Từ chối', POSTED:'Đã ghi sổ', CANCELLED:'Đã hủy', RECEIPT:'Nhập kho', ISSUE:'Xuất kho', OPENING:'Tồn đầu kỳ', ADJUST_IN:'Điều chỉnh tăng', ADJUST_OUT:'Điều chỉnh giảm', UNSET:'Chưa thiết lập', MOVING_AVERAGE:'Bình quân di động', SPECIFIC:'Đích danh', ON_APPROVAL:'Khi phê duyệt', ON_CONFIRMATION:'Khi xác nhận', SUBMIT:'Gửi duyệt', WITHDRAW:'Rút phiếu', APPROVE:'Phê duyệt', REJECT:'Từ chối', POST:'Ghi sổ', CANCEL:'Hủy phiếu'};
/**
 * Chuyển dữ liệu thành chữ hiển thị tiếng Việt: số thập phân, ngày, trạng thái và đúng/sai. DATETIME không có múi giờ từ backend được hiểu là UTC rồi hiển thị theo giờ Việt Nam.
 */
export function format(value, field = {}) {
  if (value === null || value === undefined || value === '') return '—';
  if (typeof value === 'boolean') return field.key === 'isActive' ? (value ? 'Đang dùng' : 'Ngừng dùng') : (value ? 'Có' : 'Không');
  if (field.type === 'datetime') return new Intl.DateTimeFormat('vi-VN', { dateStyle:'short', timeStyle:'short', timeZone:'Asia/Ho_Chi_Minh' }).format(new Date(`${value}Z`));
  if (field.type === 'date') return new Intl.DateTimeFormat('vi-VN').format(new Date(`${value}T00:00:00`));
  if (field.type === 'decimal') return new Intl.NumberFormat('vi-VN', {maximumFractionDigits:6}).format(value);
  if (field.key === 'direction') return value === 1 ? 'Nhập (+)' : 'Xuất (−)';
  return labels[value] || String(value);
}
