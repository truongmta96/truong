/**
 * Các màn hình tài khoản. Giao diện chỉ thu thập và hiển thị thông tin; xác minh mật khẩu, gán quyền và lưu dữ liệu đều do backend thực hiện.
 */
import React,{useState} from 'react';
import {Boxes,ArrowRight,LockKeyhole,UserRound,ShieldCheck,Check,LogOut,RotateCw,AlertCircle} from 'lucide-react';
import {api} from './api';

/**
 * Màn hình trước đăng nhập, chuyển giữa đăng nhập và đăng ký. Đăng ký tạo tài khoản VIEWER rồi yêu cầu đăng nhập; không lưu mật khẩu vào localStorage.
 */
export function AuthScreen({onLogin,initialMessage}){
 const [mode,setMode]=useState('login'),[busy,setBusy]=useState(false),[error,setError]=useState(''),[message,setMessage]=useState(initialMessage||'');
 const [values,setValues]=useState({username:'',password:'',fullName:'',confirm:''});
 /**
  * Cập nhật giá trị ô nhập trong bộ nhớ component theo tên trường, chưa gửi tới máy chủ.
  */
 function change(e){setValues(v=>({...v,[e.target.name]:e.target.value}));}
 /**
  * Chuyển giữa form đăng nhập và đăng ký; xóa thông báo và dữ liệu cũ, kể cả mật khẩu đang nhập.
  */
 function switchMode(next){setMode(next);setError('');setMessage('');setValues({username:'',password:'',fullName:'',confirm:''});}
 /**
  * Kiểm tra mật khẩu xác nhận khi đăng ký; gọi API tương ứng và chỉ chuyển vào hệ thống khi đăng nhập thành công.
  */
 async function submit(e){e.preventDefault();setBusy(true);setError('');setMessage('');try{
   if(mode==='register'){
     if(values.password!==values.confirm)throw new Error('Mật khẩu xác nhận chưa khớp.');
     await api('auth/register',{method:'POST',body:JSON.stringify({username:values.username,fullName:values.fullName,password:values.password})});
     setMode('login');setValues(v=>({...v,password:'',confirm:''}));setMessage('Đăng ký thành công. Bạn có thể đăng nhập với quyền xem dữ liệu.');
   }else onLogin(await api('auth/login',{method:'POST',body:JSON.stringify({username:values.username,password:values.password})}));
 }catch(e){setError(e.message);}finally{setBusy(false);}}
 return <div className="auth-layout"><aside className="auth-story"><a className="auth-brand" href="#dashboard"><Boxes size={30}/>vật tư<span>WORKSPACE</span></a><div><p className="auth-eyebrow">KẾT NỐI MỌI HOẠT ĐỘNG KHO</p><h1>Một không gian.<br/>Quản lý trọn vẹn.</h1><p>Từ danh mục vật tư đến từng biến động.<br/>Thông tin nhất quán, phối hợp dễ dàng.</p><div className="auth-art"><Boxes size={95} strokeWidth={.8}/><span><Check size={15}/>Có tổ chức. Có kiểm soát.</span></div></div><small><ShieldCheck size={15}/>Tài khoản riêng · Quyền truy cập rõ ràng</small></aside><main className="auth-main"><div className="auth-card"><span className="auth-icon">{mode==='login'?<LockKeyhole size={23}/>:<UserRound size={23}/>}</span><p className="eyebrow">VẬT TƯ WORKSPACE</p><h2>{mode==='login'?'Chào mừng trở lại.':'Bắt đầu cùng Vật Tư.'}</h2><p className="auth-subtitle">{mode==='login'?'Đăng nhập để tiếp tục công việc của bạn.':'Tạo tài khoản để truy cập không gian quản lý.'}</p><div className="auth-tabs"><button onClick={()=>switchMode('login')} disabled={busy} className={mode==='login'?'selected':''}>Đăng nhập</button><button onClick={()=>switchMode('register')} disabled={busy} className={mode==='register'?'selected':''}>Đăng ký</button></div>
 {error&&<div className="error-box" role="alert"><AlertCircle size={18}/>{error}</div>}{message&&<div className="info-box" role="status"><Check size={18}/>{message}</div>}
 <form onSubmit={submit} className="auth-form">
 {mode==='register'&&<label className="form-field"><span>Họ và tên</span><input required maxLength={200} name="fullName" autoComplete="name" value={values.fullName} onChange={change} placeholder="Nguyễn Văn An"/></label>}
 <label className="form-field"><span>Tên đăng nhập</span><input required name="username" aria-label="Tên đăng nhập" minLength={mode==='register'?3:1} maxLength={150} pattern={mode==='register'?'[A-Za-z0-9._\\-]+':undefined} autoComplete="username" value={values.username} onChange={change} placeholder="Nhập tên đăng nhập"/>{mode==='register'&&<small>Từ 3 ký tự: chữ không dấu, số, dấu chấm, gạch dưới hoặc gạch ngang.</small>}</label>
 <label className="form-field"><span>Mật khẩu</span><input required type="password" name="password" minLength={mode==='register'?12:1} maxLength={72} autoComplete={mode==='register'?'new-password':'current-password'} value={values.password} onChange={change} placeholder={mode==='register'?'Ít nhất 12 ký tự':'Nhập mật khẩu'}/></label>
 {mode==='register'&&<label className="form-field"><span>Xác nhận mật khẩu</span><input required type="password" name="confirm" maxLength={72} autoComplete="new-password" value={values.confirm} onChange={change} placeholder="Nhập lại mật khẩu"/></label>}
 <button className="primary auth-submit" disabled={busy}>{busy?<RotateCw size={17} className="spin"/>:<ArrowRight size={17}/>} {busy?'Đang xử lý…':mode==='login'?'Đăng nhập':'Tạo tài khoản'}</button></form><p className="auth-footnote">{mode==='register'?'Tài khoản mới có quyền xem. Quản trị viên sẽ cấp thêm quyền khi cần.':'Phiên đăng nhập tự hết hạn sau 30 phút không hoạt động.'}</p></div><small className="auth-copyright">Vật Tư Workspace · Làm việc gọn gàng hơn mỗi ngày.</small></main></div>;
}

/**
 * Trang tài khoản cá nhân: sửa họ tên, xem vai trò, đổi mật khẩu và đăng xuất. Tên đăng nhập giữ cố định; không có ô tự gán quyền.
 */
export function AccountPage({user,onUpdate,onLogout}){
 const [fullName,setFullName]=useState(user.fullName),[passwords,setPasswords]=useState({currentPassword:'',newPassword:'',confirm:''}),[busy,setBusy]=useState(''),[error,setError]=useState(''),[message,setMessage]=useState('');
 /**
  * Gửi họ tên mới đến API của chính tài khoản trong phiên, rồi cập nhật tên hiển thị trong giao diện.
  */
 async function profile(e){e.preventDefault();setBusy('profile');setError('');setMessage('');try{const account=await api('auth/me',{method:'PUT',body:JSON.stringify({fullName})});onUpdate(account);setMessage('Đã cập nhật thông tin tài khoản.');}catch(e){setError(e.message);}finally{setBusy('');}}
 /**
  * Kiểm tra mật khẩu mới và xác nhận trùng nhau, gửi mật khẩu hiện tại để backend xác minh; thành công thì quay lại đăng nhập.
  */
 async function password(e){e.preventDefault();setBusy('password');setError('');setMessage('');try{if(passwords.newPassword!==passwords.confirm)throw new Error('Mật khẩu xác nhận chưa khớp.');await api('auth/password',{method:'PUT',body:JSON.stringify({currentPassword:passwords.currentPassword,newPassword:passwords.newPassword})});onLogout('Đã đổi mật khẩu. Vui lòng đăng nhập lại.');}catch(e){setError(e.message);}finally{setBusy('');}}
 return <><div className="page-heading"><div><div className="eyebrow"><span/>KHÔNG GIAN CÁ NHÂN</div><h1>Tài khoản của bạn<span className="heading-dot">.</span></h1><p>Quản lý thông tin cá nhân và giữ tài khoản an toàn.</p></div><button className="secondary" onClick={()=>onLogout()}><LogOut size={16}/>Đăng xuất</button></div>{error&&<div className="error-box" role="alert"><AlertCircle size={18}/>{error}</div>}{message&&<div className="info-box" role="status"><Check size={18}/>{message}</div>}<div className="account-grid"><section className="panel"><div className="panel-heading"><h2>Thông tin cá nhân</h2><UserRound size={20}/></div><form className="account-form" onSubmit={profile}><label className="form-field"><span>Tên đăng nhập</span><input value={user.username} disabled/><small>Tên đăng nhập là định danh cố định của tài khoản.</small></label><label className="form-field"><span>Họ và tên</span><input required maxLength={200} value={fullName} onChange={e=>setFullName(e.target.value)} autoComplete="name"/></label><div className="account-roles"><span>Vai trò được cấp</span><div>{user.roles.map(role=><span className="badge good" key={role}>{({ADMIN:'Quản trị viên',CLERK:'Nhân viên kho',APPROVER:'Người phê duyệt',VIEWER:'Người xem'})[role]||role}</span>)}</div></div><button className="primary" disabled={!!busy}>{busy==='profile'?<RotateCw size={16} className="spin"/>:<Check size={16}/>}Lưu thông tin</button></form></section><section className="panel"><div className="panel-heading"><h2>Đổi mật khẩu</h2><LockKeyhole size={20}/></div><form className="account-form" onSubmit={password}>{[{key:'currentPassword',label:'Mật khẩu hiện tại'},{key:'newPassword',label:'Mật khẩu mới'},{key:'confirm',label:'Xác nhận mật khẩu mới'}].map(f=><label className="form-field" key={f.key}><span>{f.label}</span><input required type="password" autoComplete={f.key==='currentPassword'?'current-password':'new-password'} minLength={f.key==='currentPassword'?1:12} maxLength={72} value={passwords[f.key]} onChange={e=>setPasswords(v=>({...v,[f.key]:e.target.value}))}/></label>)}<p className="muted">Dùng ít nhất 12 ký tự, tối đa 72 byte UTF-8. Sau khi đổi, bạn cần đăng nhập lại; các phiên cũ sẽ hết hiệu lực khi gửi yêu cầu tiếp theo.</p><button className="primary" disabled={!!busy}>{busy==='password'?<RotateCw size={16} className="spin"/>:<ShieldCheck size={16}/>}Đổi mật khẩu</button></form></section></div></>;
}
