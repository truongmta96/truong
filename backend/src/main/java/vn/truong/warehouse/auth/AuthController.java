package vn.truong.warehouse.auth;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import jakarta.servlet.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import vn.truong.warehouse.repository.AppUserRepository;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService accounts;private final AuthenticationManager manager;private final SecurityContextRepository contexts;private final AppUserRepository users;
    public AuthController(AuthService accounts,AuthenticationManager manager,SecurityContextRepository contexts,AppUserRepository users){this.accounts=accounts;this.manager=manager;this.contexts=contexts;this.users=users;}
    public record Register(@NotBlank @Pattern(regexp="[A-Za-z0-9._-]{3,150}") String username,@NotBlank @Size(max=200) String fullName,@NotBlank @Size(min=12,max=72) String password){}
    public record Login(@NotBlank @Size(max=150) String username,@NotBlank @Size(max=72) String password){}
    public record Profile(@NotBlank @Size(max=200) String fullName){}
    public record Password(@NotBlank @Size(max=72) String currentPassword,@NotBlank @Size(min=12,max=72) String newPassword){}
    @GetMapping("/csrf") public Map<String,String> csrf(CsrfToken token){return Map.of("token",token.getToken(),"headerName",token.getHeaderName());}
    @PostMapping("/register") @ResponseStatus(HttpStatus.CREATED)
    public AuthService.Account register(@Valid @RequestBody Register dto){return accounts.register(dto.username(),dto.fullName(),dto.password());}
    @PostMapping("/login") public AuthService.Account login(@Valid @RequestBody Login dto,HttpServletRequest req,HttpServletResponse res){
        manager.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(dto.username(),dto.password()));
        var user=users.findByUsername(dto.username()).orElseThrow();
        if(req.getSession(false)!=null)req.changeSessionId();else req.getSession(true);
        new org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository().saveToken(null,req,res);
        var principal=new AuthPrincipal(user.getId(),user.getUsername(),AuthService.fingerprint(user.getPasswordHash()));
        var auth=UsernamePasswordAuthenticationToken.authenticated(principal,null,accounts.roles(user.getId()).stream().map(r->new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_"+r)).toList());
        var context=SecurityContextHolder.createEmptyContext();context.setAuthentication(auth);SecurityContextHolder.setContext(context);contexts.saveContext(context,req,res);
        return accounts.account(user);
    }
    @GetMapping("/me") public AuthService.Account me(@AuthenticationPrincipal AuthPrincipal user){return accounts.account(accounts.require(user.id()));}
    @PutMapping("/me") public AuthService.Account update(@AuthenticationPrincipal AuthPrincipal user,@Valid @RequestBody Profile dto){return accounts.update(user.id(),dto.fullName());}
    @PutMapping("/password") public Map<String,String> password(@AuthenticationPrincipal AuthPrincipal user,@Valid @RequestBody Password dto,HttpServletRequest req){accounts.changePassword(user.id(),dto.currentPassword(),dto.newPassword());SecurityContextHolder.clearContext();if(req.getSession(false)!=null)req.getSession(false).invalidate();return Map.of("message","Đã đổi mật khẩu. Vui lòng đăng nhập lại.");}
}
