package vn.truong.warehouse.auth;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.jdbc.core.JdbcTemplate;
import vn.truong.warehouse.entity.AppUser;
import vn.truong.warehouse.repository.AppUserRepository;

@Service
public class AuthService {
    private final AppUserRepository users;
    private final PasswordEncoder encoder;
    private final JdbcTemplate jdbc;
    public AuthService(AppUserRepository users, PasswordEncoder encoder, JdbcTemplate jdbc) { this.users=users; this.encoder=encoder; this.jdbc=jdbc; }
    public record Account(Long id, String username, String fullName, List<String> roles) {}
    public List<String> roles(Long id) { return jdbc.queryForList("SELECT r.code FROM app_role r JOIN user_role ur ON ur.role_id=r.id WHERE ur.user_id=?", String.class, id); }
    public Account account(AppUser user) { return new Account(user.getId(),user.getUsername(),user.getFullName(),roles(user.getId())); }
    public AppUser require(Long id) { return users.findById(id).orElseThrow(()->new BadCredentialsException("Phiên đăng nhập không hợp lệ.")); }
    public static String fingerprint(String hash) {
        try { return HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(hash.getBytes(StandardCharsets.UTF_8))); }
        catch (java.security.NoSuchAlgorithmException e) { throw new IllegalStateException(e); }
    }
    private void password(String value) { if (value.getBytes(StandardCharsets.UTF_8).length>72) throw new IllegalArgumentException("Mật khẩu không được vượt 72 byte UTF-8."); }
    @Transactional
    public Account register(String username,String fullName,String password) {
        password(password);
        if(users.findByUsername(username).isPresent()) throw new org.springframework.dao.DuplicateKeyException("Username exists");
        var role = jdbc.queryForList("SELECT id FROM app_role WHERE code='VIEWER'",Long.class);
        if(role.isEmpty()) throw new IllegalStateException("Missing VIEWER role; initialize schema seeds first");
        var user=new AppUser();user.setUsername(username);user.setFullName(fullName.strip());user.setPasswordHash(encoder.encode(password));user.setIsActive(true);
        users.saveAndFlush(user);
        jdbc.update("INSERT INTO user_role(user_id,role_id) VALUES (?,?)",user.getId(),role.getFirst());
        return account(user);
    }
    @Transactional
    public Account update(Long id,String fullName){var user=require(id);user.setFullName(fullName.strip());users.saveAndFlush(user);return account(user);}
    @Transactional
    public void changePassword(Long id,String current,String next){password(next);var user=require(id);if(!encoder.matches(current,user.getPasswordHash()))throw new BadCredentialsException("Mật khẩu hiện tại không đúng.");if(encoder.matches(next,user.getPasswordHash()))throw new IllegalArgumentException("Mật khẩu mới phải khác mật khẩu hiện tại.");user.setPasswordHash(encoder.encode(next));users.saveAndFlush(user);}
}
