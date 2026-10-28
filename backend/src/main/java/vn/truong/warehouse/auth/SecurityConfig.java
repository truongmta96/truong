package vn.truong.warehouse.auth;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.*;
import org.springframework.security.web.csrf.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.http.HttpMethod;
import vn.truong.warehouse.repository.AppUserRepository;

/**
 * Thiết lập xác thực bằng phiên và kiểm tra quyền ở backend. VIEWER/APPROVER được đọc dữ liệu kho; CLERK/ADMIN được ghi danh mục; nhóm API quản trị chỉ dành cho ADMIN.
 */
@Configuration
public class SecurityConfig {
    /**
     * Tạo bộ băm/đối chiếu mật khẩu BCrypt với hệ số xử lý 12. Không mã hóa theo cách có thể giải ngược ra mật khẩu thô.
     */
    @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder(12);}
    /**
     * Lưu và khôi phục thông tin đăng nhập trong phiên HTTP phía backend; trình duyệt chỉ giữ cookie định danh phiên.
     */
    @Bean SecurityContextRepository contexts(){return new HttpSessionSecurityContextRepository();}
    /**
     * Cung cấp tên đăng nhập, chuỗi băm, trạng thái khóa và vai trò cho Spring Security khi xác minh đăng nhập.
     */
    @Bean UserDetailsService userDetails(AppUserRepository users,AuthService accounts){return username->{var u=users.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Invalid credentials"));return User.withUsername(u.getUsername()).password(u.getPasswordHash()).disabled(!Boolean.TRUE.equals(u.getIsActive())).roles(accounts.roles(u.getId()).toArray(String[]::new)).build();};}
    /**
     * Kết hợp nguồn tài khoản và bộ kiểm tra BCrypt để xác minh thông tin đăng nhập.
     */
    @Bean AuthenticationManager authenticationManager(UserDetailsService service,PasswordEncoder encoder){var provider=new DaoAuthenticationProvider(service);provider.setPasswordEncoder(encoder);return new ProviderManager(provider);}
    /**
     * Khai báo thứ tự kiểm tra quyền API, bật CSRF, xử lý lỗi 401/403 và đăng xuất. Quyền thực sự được kiểm tra ở backend, không chỉ bằng việc ẩn nút trên giao diện.
     */
    @Bean SecurityFilterChain security(HttpSecurity http,SecurityContextRepository contexts,AppUserRepository users,AuthService accounts)throws Exception{
        http.securityContext(c->c.securityContextRepository(contexts))
            .csrf(c->c.csrfTokenRepository(new HttpSessionCsrfTokenRepository()))
            .authorizeHttpRequests(a->a
                .requestMatchers("/api/auth/csrf","/api/auth/login","/api/auth/register","/error").permitAll()
                .requestMatchers("/api/auth/**").authenticated()
                .requestMatchers("/api/users/**","/api/roles/**","/api/user-roles/**","/api/audit-logs/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/api/**").hasAnyRole("ADMIN","CLERK","APPROVER","VIEWER")
                .requestMatchers("/api/**").hasAnyRole("ADMIN","CLERK")
                .anyRequest().denyAll())
            .exceptionHandling(e->e.authenticationEntryPoint((req,res,ex)->json(res,401,"Vui lòng đăng nhập để tiếp tục."))
                .accessDeniedHandler((req,res,ex)->json(res,403,"Bạn không có quyền thực hiện hoặc phiên xác thực đã thay đổi. Vui lòng tải lại trang.")))
            .logout(l->l.logoutUrl("/api/auth/logout").invalidateHttpSession(true).clearAuthentication(true).deleteCookies("JSESSIONID").logoutSuccessHandler((req,res,auth)->json(res,200,"Đã đăng xuất.")))
            .addFilterBefore(new SessionAccountFilter(users,accounts),AnonymousAuthenticationFilter.class);
        return http.build();
    }
    /**
     * Ghi mã HTTP và thông báo JSON tiếng Việt cho yêu cầu không được xác thực, bị từ chối hoặc vừa đăng xuất.
     * @param res Phản hồi HTTP gửi về trình duyệt.
     */
    private static void json(jakarta.servlet.http.HttpServletResponse res,int status,String message)throws java.io.IOException{res.setStatus(status);res.setContentType("application/json;charset=UTF-8");new com.fasterxml.jackson.databind.ObjectMapper().writeValue(res.getWriter(),java.util.Map.of("message",message));}
}
