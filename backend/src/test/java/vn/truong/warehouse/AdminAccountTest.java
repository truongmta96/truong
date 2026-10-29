package vn.truong.warehouse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import vn.truong.warehouse.auth.*;
import vn.truong.warehouse.entity.AppUser;
import vn.truong.warehouse.repository.AppUserRepository;
import java.util.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;

/** Kiểm thử quyền quản trị tài khoản trong transaction rollback, không cấp ADMIN lâu dài cho database. */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@WithMockUser(roles="ADMIN")
class AdminAccountTest {
    @Autowired MockMvc mvc;
    @Autowired AppUserRepository users;
    @Autowired JdbcTemplate jdbc;
    @Autowired AuthService auth;

    /** Tạo tài khoản giả lập và các quyền chỉ trong transaction kiểm thử. */
    private AppUser fixture(String... roles) {
        var user=new AppUser();user.setUsername("admin-test-"+UUID.randomUUID());user.setFullName("Original");
        user.setPasswordHash("test-hash-must-not-change");user.setIsActive(true);users.saveAndFlush(user);
        for(String role:roles)jdbc.update("INSERT INTO user_role(user_id,role_id) SELECT ?,id FROM app_role WHERE code=?",user.getId(),role);
        return user;
    }

    /** ADMIN đọc/sửa tài khoản thường, không thể chèn thay đổi username, mật khẩu hoặc role vào JSON. */
    @Test void adminCanReadAndEditOnlyAllowedFields() throws Exception {
        var target=fixture("VIEWER");String originalName=target.getUsername();
        mvc.perform(get("/api/users/"+target.getId())).andExpect(status().isOk()).andExpect(jsonPath("$.username").value(originalName));
        mvc.perform(put("/api/users/"+target.getId()).with(csrf()).contentType("application/json")
            .content("{\"fullName\":\"Tên đã sửa\",\"isActive\":false,\"username\":\"injected\",\"passwordHash\":\"injected\",\"roles\":[\"ADMIN\"]}"))
            .andExpect(status().isOk()).andExpect(jsonPath("$.fullName").value("Tên đã sửa")).andExpect(jsonPath("$.isActive").value(false));
        assertThat(target.getUsername()).isEqualTo(originalName);
        assertThat(target.getPasswordHash()).isEqualTo("test-hash-must-not-change");
        assertThat(auth.roles(target.getId())).containsExactly("VIEWER");
        mvc.perform(put("/api/users/"+target.getId()).with(csrf()).contentType("application/json").content("{\"fullName\":\"Reactivated\",\"isActive\":true}"))
            .andExpect(status().isOk()).andExpect(jsonPath("$.isActive").value(true));
    }

    /** Tài khoản có ADMIN cùng nhiều vai trò vẫn bị ẩn; nhập trực tiếp ID để xem/sửa cũng bị chặn. */
    @Test void adminTargetsAreHiddenAndCannotBeReadOrChanged() throws Exception {
        var target=fixture("ADMIN","VIEWER");target.setIsActive(false);users.saveAndFlush(target);
        var normal=fixture("CLERK");
        mvc.perform(get("/api/users?size=200"))
            .andExpect(status().isOk()).andExpect(jsonPath("$.content[*].id",not(hasItem(target.getId().intValue()))))
            .andExpect(jsonPath("$.content[*].id",hasItem(normal.getId().intValue())));
        mvc.perform(get("/api/users/"+target.getId())).andExpect(status().isForbidden());
        mvc.perform(put("/api/users/"+target.getId()).with(csrf()).contentType("application/json").content("{\"fullName\":\"Hacked\",\"isActive\":true}"))
            .andExpect(status().isForbidden());
        assertThat(target.getFullName()).isEqualTo("Original");assertThat(target.getIsActive()).isFalse();
    }

    /** VIEWER không được mở API quản trị kể cả khi tự gửi PUT hợp lệ và CSRF. */
    @Test @WithMockUser(roles="VIEWER") void nonAdminCannotManageAccounts() throws Exception {
        var target=fixture("VIEWER");
        mvc.perform(get("/api/users")).andExpect(status().isForbidden());
        mvc.perform(get("/api/users/"+target.getId())).andExpect(status().isForbidden());
        mvc.perform(put("/api/users/"+target.getId()).with(csrf()).contentType("application/json").content("{\"fullName\":\"Denied\",\"isActive\":false}"))
            .andExpect(status().isForbidden());
        assertThat(target.getFullName()).isEqualTo("Original");
    }

    /** Kiểm tra lỗi trường trống, thiếu CSRF và tài khoản không tồn tại. */
    @Test void validationCsrfAndMissingIdAreHandled() throws Exception {
        var target=fixture("VIEWER");
        mvc.perform(put("/api/users/"+target.getId()).contentType("application/json").content("{\"fullName\":\"Changed\",\"isActive\":true}"))
            .andExpect(status().isForbidden());
        mvc.perform(put("/api/users/"+target.getId()).with(csrf()).contentType("application/json").content("{\"fullName\":\" \",\"isActive\":true}"))
            .andExpect(status().isBadRequest());
        mvc.perform(put("/api/users/9223372036854775807").with(csrf()).contentType("application/json").content("{\"fullName\":\"Changed\",\"isActive\":true}"))
            .andExpect(status().isNotFound());
    }

    /** Khóa bằng API quản trị làm yêu cầu tiếp theo của phiên tài khoản đích bị từ chối. */
    @Test void disablingAccountRejectsItsAuthenticatedRequests() throws Exception {
        var target=fixture("VIEWER");
        var principal=new AuthPrincipal(target.getId(),target.getUsername(),AuthService.fingerprint(target.getPasswordHash()));
        var token=UsernamePasswordAuthenticationToken.authenticated(principal,null,List.of(new SimpleGrantedAuthority("ROLE_VIEWER")));
        mvc.perform(put("/api/users/"+target.getId()).with(csrf()).contentType("application/json").content("{\"fullName\":\"Disabled\",\"isActive\":false}"))
            .andExpect(status().isOk());
        mvc.perform(get("/api/auth/me").with(authentication(token))).andExpect(status().isUnauthorized());
    }
}
