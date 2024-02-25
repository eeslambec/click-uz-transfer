package uz.pdp.clickuztransactionsservice.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@AllArgsConstructor
@Getter
public class ClickUzAuthentication {
    private String principal;
    private String credential;
    private List<String> roles;
    public static UsernamePasswordAuthenticationToken cast(ClickUzAuthentication authentication){
        return new UsernamePasswordAuthenticationToken(
                authentication.getPrincipal(),
                authentication.getCredential(),
                authentication.getRoles().stream().map(SimpleGrantedAuthority::new).toList()
        );
    }
}
