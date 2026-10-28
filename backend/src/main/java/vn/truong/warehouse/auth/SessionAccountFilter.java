package vn.truong.warehouse.auth;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import vn.truong.warehouse.repository.AppUserRepository;

public class SessionAccountFilter extends OncePerRequestFilter {
    private final AppUserRepository users; private final AuthService accounts;
    public SessionAccountFilter(AppUserRepository users,AuthService accounts){this.users=users;this.accounts=accounts;}
    @Override protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain)throws ServletException,IOException{
        var auth=SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null && auth.getPrincipal() instanceof AuthPrincipal principal){
            var user=users.findById(principal.id()).orElse(null);
            if(user==null||!Boolean.TRUE.equals(user.getIsActive())||!principal.fingerprint().equals(AuthService.fingerprint(user.getPasswordHash()))){
                SecurityContextHolder.clearContext();var session=request.getSession(false);if(session!=null)session.invalidate();
                response.setStatus(401);response.setContentType("application/json;charset=UTF-8");response.getWriter().write("{\"message\":\"Phiên đăng nhập đã hết hiệu lực. Vui lòng đăng nhập lại.\"}");return;
            }
            var fresh=UsernamePasswordAuthenticationToken.authenticated(principal,null,accounts.roles(principal.id()).stream().map(r->new SimpleGrantedAuthority("ROLE_"+r)).toList());
            SecurityContextHolder.getContext().setAuthentication(fresh);
        }
        chain.doFilter(request,response);
    }
}
