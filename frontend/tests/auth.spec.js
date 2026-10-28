/** Kiểm thử tài khoản bằng trình duyệt thật. Dữ liệu e2e-auth-* chỉ dành cho database kiểm thử. */
import {test,expect} from '@playwright/test';
const password='Browser-test-password-123!';
/** Tạo tài khoản VIEWER qua giao diện và chờ thông báo đăng ký thành công. */
async function register(page){
 const username='e2e-auth-'+Date.now()+'-'+Math.random().toString(36).slice(2,6);
 await page.goto('/');await page.getByRole('button',{name:'Đăng ký',exact:true}).click();
 await page.getByLabel('Họ và tên',{exact:true}).fill('Người dùng kiểm thử');
 await page.getByLabel('Tên đăng nhập',{exact:true}).fill(username);
 await page.getByLabel('Mật khẩu',{exact:true}).fill(password);
 await page.getByLabel('Xác nhận mật khẩu',{exact:true}).fill(password);
 await page.getByRole('button',{name:'Tạo tài khoản',exact:true}).click();
 await expect(page.getByRole('status')).toContainText('Đăng ký thành công');
 return username;
}
/** Nhập thông tin và gửi form đăng nhập; ca kiểm thử gọi hàm sẽ xác nhận kết quả. */
async function login(page,username,pwd=password){
 await page.getByLabel('Tên đăng nhập',{exact:true}).fill(username);
 await page.getByLabel('Mật khẩu',{exact:true}).fill(pwd);
 await page.getByRole('button',{name:'Đăng nhập',exact:true}).last().click();
}

/** Kiểm tra cả vòng đời tài khoản: tạo mới, sai mật khẩu, giữ phiên khi reload, đổi họ tên, đổi mật khẩu và đăng xuất. */
test('register, login, persistent session, edit own profile, change password and logout',async({page})=>{
 const username=await register(page);
 await login(page,username,'wrong-password');await expect(page.getByRole('alert')).toContainText('không đúng');
 await login(page,username);await expect(page.getByRole('heading',{name:'Mọi thứ trong tầm kiểm soát.'})).toBeVisible();
 await page.getByRole('button',{name:'Tài khoản cá nhân',exact:true}).click();
 await page.getByLabel('Họ và tên',{exact:true}).fill('Tên mới của tôi');
 await page.getByRole('button',{name:'Lưu thông tin',exact:true}).click();
 await expect(page.getByRole('status')).toContainText('Đã cập nhật');
 await page.reload();await expect(page.getByLabel('Họ và tên',{exact:true})).toHaveValue('Tên mới của tôi');
 await page.getByLabel('Mật khẩu hiện tại',{exact:true}).fill(password);
 await page.getByLabel('Mật khẩu mới',{exact:true}).fill('Changed-browser-password-123!');
 await page.getByLabel('Xác nhận mật khẩu mới',{exact:true}).fill('Changed-browser-password-123!');
 await page.getByRole('button',{name:'Đổi mật khẩu',exact:true}).click();
 await expect(page.getByRole('heading',{name:'Chào mừng trở lại.'})).toBeVisible();
 await login(page,username,password);await expect(page.getByRole('alert')).toContainText('không đúng');
 await login(page,username,'Changed-browser-password-123!');
 await page.getByRole('button',{name:'Tài khoản cá nhân',exact:true}).click();
 await page.getByRole('button',{name:'Đăng xuất',exact:true}).click();
 await expect(page.getByRole('heading',{name:'Chào mừng trở lại.'})).toBeVisible();
 await page.reload();await expect(page.getByRole('heading',{name:'Chào mừng trở lại.'})).toBeVisible();
});

/** Kiểm tra VIEWER không thấy nút sửa hoặc quản trị; truy cập trực tiếp API quản trị cũng bị chặn. */
test('viewer cannot edit catalog or visit administration',async({page})=>{
 const username=await register(page);await login(page,username);
 await expect(page.getByRole('heading',{name:'Mọi thứ trong tầm kiểm soát.'})).toBeVisible();
 await expect(page.getByRole('button',{name:'Quản trị',exact:true})).toHaveCount(0);
 await page.getByRole('button',{name:'Danh mục vật tư',exact:true}).click();
 await expect(page.getByRole('button',{name:'Thêm vật tư',exact:true})).toHaveCount(0);
 await page.goto('/#users');await expect(page.getByRole('heading',{name:'Bạn không có quyền truy cập trang này.'})).toBeVisible();
 expect((await page.request.get('/api/users')).status()).toBe(403);
});

/** Kiểm tra form điện thoại báo mật khẩu xác nhận sai và không làm trang tràn ngang. */
test('mobile registration checks password confirmation and has no page overflow',async({page})=>{
 await page.setViewportSize({width:390,height:844});await page.goto('/');
 await page.getByRole('button',{name:'Đăng ký',exact:true}).click();
 await page.getByLabel('Họ và tên',{exact:true}).fill('Mobile Test');
 await page.getByLabel('Tên đăng nhập',{exact:true}).fill('e2e-mobile-'+Date.now());
 await page.getByLabel('Mật khẩu',{exact:true}).fill(password);
 await page.getByLabel('Xác nhận mật khẩu',{exact:true}).fill('different-password-123!');
 await page.getByRole('button',{name:'Tạo tài khoản',exact:true}).click();
 await expect(page.getByRole('alert')).toContainText('chưa khớp');
 expect(await page.evaluate(()=>document.documentElement.scrollWidth<=innerWidth)).toBe(true);
});
