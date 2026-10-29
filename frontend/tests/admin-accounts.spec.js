import {test,expect} from '@playwright/test';

/**
 * Kiểm tra form bằng API giả lập, không tạo/cấp ADMIN trong database.
 * Quyền thật và bảo vệ tài khoản ADMIN được kiểm tra bằng AdminAccountTest trên MySQL.
 */
test('admin edits ordinary account name and status without account creation or privilege fields',async({page})=>{
 let account={id:42,username:'staff42',fullName:'Nhân viên kho',isActive:true,createdAt:'2026-10-29T00:00:00'};
 let sent;
 await page.route('**/api/**',async route=>{
  const req=route.request(),path=new URL(req.url()).pathname;
  const json=body=>route.fulfill({status:200,contentType:'application/json',body:JSON.stringify(body)});
  if(path==='/api/auth/me')return json({id:1,username:'admin-test',fullName:'Quản trị giả lập',roles:['ADMIN']});
  if(path==='/api/auth/csrf')return json({token:'test-csrf',headerName:'X-CSRF-TOKEN'});
  if(path==='/api/users/42'&&req.method()==='PUT'){
   expect(req.headers()['x-csrf-token']).toBe('test-csrf');sent=req.postDataJSON();account={...account,...sent};return json(account);
  }
  if(path==='/api/users')return json({content:[account],number:0,totalPages:1,totalElements:1});
  return json({content:[],number:0,totalPages:0,totalElements:0});
 });
 await page.goto('/#users');
 await expect(page.getByRole('heading',{name:'Người dùng.',exact:true})).toBeVisible();
 await expect(page.getByRole('button',{name:'Thêm người dùng',exact:true})).toHaveCount(0);
 await expect(page.getByText('Tài khoản có quyền ADMIN không xuất hiện',{exact:false})).toBeVisible();
 await page.getByRole('button',{name:'Sửa staff42',exact:true}).click();
 const dialog=page.getByRole('dialog');
 await dialog.getByLabel('Họ và tên',{exact:false}).fill('Họ tên đã sửa');
 await dialog.getByRole('checkbox',{name:'Đang sử dụng'}).uncheck();
 await expect(dialog.getByLabel('Tên đăng nhập',{exact:false})).toHaveCount(0);
 await expect(dialog.getByLabel('Mật khẩu',{exact:false})).toHaveCount(0);
 await dialog.getByRole('button',{name:'Lưu thay đổi'}).click();
 await expect(dialog).toHaveCount(0);
 await expect(page.getByRole('row').filter({hasText:'staff42'})).toContainText('Họ tên đã sửa');
 await expect(page.getByRole('row').filter({hasText:'staff42'})).toContainText('Ngừng dùng');
 expect(sent).toEqual({fullName:'Họ tên đã sửa',isActive:false});
});
