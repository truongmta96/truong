package vn.truong.warehouse.auth;
import java.io.Serializable;
import java.security.Principal;
public record AuthPrincipal(Long id, String username, String fingerprint) implements Serializable, Principal {
    @Override public String getName(){return username;}
    @Override public String toString(){return username;}
}
