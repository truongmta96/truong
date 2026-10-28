export async function api(path, options = {}) {
  const response = await fetch(`/api/${path}`, { ...options, headers: { 'Content-Type': 'application/json', ...options.headers } });
  let body;
  try { body = await response.json(); } catch { throw new Error('Không nhận được phản hồi hợp lệ. Vui lòng kiểm tra kết nối máy chủ.'); }
  if (!response.ok) { const error = new Error(body.message || 'Không thể thực hiện yêu cầu. Vui lòng thử lại.'); error.fields = body.fields; throw error; }
  return body;
}
export const labels = {DRAFT:'Bản nháp', PENDING:'Chờ duyệt', APPROVED:'Đã duyệt', REJECTED:'Từ chối', POSTED:'Đã ghi sổ', CANCELLED:'Đã hủy', RECEIPT:'Nhập kho', ISSUE:'Xuất kho', OPENING:'Tồn đầu kỳ', ADJUST_IN:'Điều chỉnh tăng', ADJUST_OUT:'Điều chỉnh giảm', UNSET:'Chưa thiết lập', MOVING_AVERAGE:'Bình quân di động', SPECIFIC:'Đích danh', ON_APPROVAL:'Khi phê duyệt', ON_CONFIRMATION:'Khi xác nhận', SUBMIT:'Gửi duyệt', WITHDRAW:'Rút phiếu', APPROVE:'Phê duyệt', REJECT:'Từ chối', POST:'Ghi sổ', CANCEL:'Hủy phiếu'};
export function format(value, field = {}) {
  if (value === null || value === undefined || value === '') return '—';
  if (typeof value === 'boolean') return field.key === 'isActive' ? (value ? 'Đang dùng' : 'Ngừng dùng') : (value ? 'Có' : 'Không');
  if (field.type === 'datetime') return new Intl.DateTimeFormat('vi-VN', { dateStyle:'short', timeStyle:'short', timeZone:'Asia/Ho_Chi_Minh' }).format(new Date(`${value}Z`));
  if (field.type === 'date') return new Intl.DateTimeFormat('vi-VN').format(new Date(`${value}T00:00:00`));
  if (field.type === 'decimal') return new Intl.NumberFormat('vi-VN', {maximumFractionDigits:6}).format(value);
  if (field.key === 'direction') return value === 1 ? 'Nhập (+)' : 'Xuất (−)';
  return labels[value] || String(value);
}
