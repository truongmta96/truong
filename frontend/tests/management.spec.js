import {test,expect} from '@playwright/test';

test('overview and all management pages render with real API data',async({page})=>{
 const errors=[];page.on('pageerror',e=>errors.push(e.message));
 await page.goto('/');
 await expect(page.getByRole('heading',{name:'Mọi thứ trong tầm kiểm soát.'})).toBeVisible();
 await expect(page.getByRole('button',{name:/Vật tư trong danh mục/})).not.toContainText('—');
 const {resources}=await import('../src/resources.js');
 for(const r of resources){
  await page.goto('/#'+r.key);
  await expect(page.getByRole('heading',{name:r.title+'.',exact:true})).toBeVisible();
  await expect(page.getByText('Đang tải dữ liệu…',{exact:true})).toHaveCount(0);
  await expect(page.getByRole('alert')).toHaveCount(0);
  await expect(page.getByText(/Tổng \d+ bản ghi/)).toBeVisible();
 }
 expect(errors).toEqual([]);
});

test('create, edit, detail and filter a category through real backend',async({page})=>{
 const code='UI-E2E-'+Date.now();
 await page.goto('/#categories');
 await page.getByRole('button',{name:'Thêm nhóm vật tư',exact:true}).click();
 const dialog=page.getByRole('dialog');
 await dialog.getByLabel('Mã',{exact:false}).fill(code);
 await dialog.getByLabel('Tên',{exact:false}).fill('Nhóm kiểm thử giao diện');
 await dialog.getByRole('button',{name:'Tạo mới',exact:true}).click();
 await expect(dialog).toHaveCount(0);
 await expect(page.getByRole('status').filter({hasText:'Đã lưu'})).toBeVisible();
 const row=page.getByRole('row').filter({hasText:code});
 await expect(row).toContainText('Nhóm kiểm thử giao diện');
 await row.getByRole('button',{name:'Sửa '+code,exact:true}).click();
 await dialog.getByLabel('Tên',{exact:false}).fill('Nhóm đã cập nhật');
 await dialog.getByRole('button',{name:'Lưu thay đổi'}).click();
 await expect(dialog).toHaveCount(0);
 await expect(row).toContainText('Nhóm đã cập nhật');
 await row.getByRole('button',{name:'Xem chi tiết '+code,exact:true}).click();
 await expect(dialog).toContainText('Nhóm đã cập nhật');
 await dialog.getByRole('button',{name:'Đóng',exact:true}).click();
 await page.getByRole('textbox',{name:'Tìm trong trang hiện tại'}).fill('this-does-not-exist');
 await expect(page.getByRole('heading',{name:'Không có kết quả phù hợp'})).toBeVisible();
});

test('failure is visible and can be retried without fake data',async({page})=>{
 await page.route('**/api/suppliers?*',route=>route.fulfill({status:503,contentType:'application/json',body:JSON.stringify({message:'Máy chủ tạm thời không sẵn sàng.'})}));
 await page.goto('/#suppliers');
 await expect(page.getByRole('alert')).toContainText('Máy chủ tạm thời không sẵn sàng.');
 await page.unroute('**/api/suppliers?*');
 await page.getByRole('button',{name:'Thử lại'}).click();
 await expect(page.getByRole('alert')).toHaveCount(0);
});

test('mobile menu and accessible modal work without horizontal page overflow',async({page})=>{
 await page.setViewportSize({width:390,height:844});
 await page.goto('/');
 await page.getByRole('button',{name:'Mở menu'}).click();
 await page.getByRole('button',{name:'Danh mục vật tư',exact:true}).click();
 await expect(page.getByRole('heading',{name:'Vật tư.',exact:true})).toBeVisible();
 await page.getByRole('button',{name:'Thêm vật tư',exact:true}).click();
 await expect(page.getByRole('dialog')).toBeVisible();
 await page.keyboard.press('Escape');
 await expect(page.getByRole('dialog')).toHaveCount(0);
 expect(await page.evaluate(()=>document.documentElement.scrollWidth<=innerWidth)).toBe(true);
});
