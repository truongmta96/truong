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

@Configuration
public class SecurityConfig {
    @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder(12);}
    @Bean SecurityContextRepository contexts(){return new HttpSessionSecurityContextRepository();}
    @Bean UserDetailsService userDetails(AppUserRepository users,AuthService accounts){return username->{var u=users.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Invalid credentials"));return User.withUsername(u.getUsername()).password(u.getPasswordHash()).disabled(!Boolean.TRUE.equals(u.getIsActive())).roles(accounts.roles(u.getId()).toArray(String[]::new)).build();};}
    @Bean AuthenticationManager authenticationManager(UserDetailsService service,PasswordEncoder encoder){var provider=new DaoAuthenticationProvider(service);provider.setPasswordEncoder(encoder);return new ProviderManager(provider);}
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
    private static void json(jakarta.servlet.http.HttpServletResponse res,int status,String message)throws java.io.IOException{res.setStatus(status);res.setContentType("application/json;charset=UTF-8");new com.fasterxml.jackson.databind.ObjectMapper().writeValue(res.getWriter(),java.util.Map.of("message",message));}
}
