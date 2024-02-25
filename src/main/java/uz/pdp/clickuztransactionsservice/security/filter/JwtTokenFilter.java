package uz.pdp.clickuztransactionsservice.security.filter;

import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.clickuztransactionsservice.dto.CustomResponseEntity;
import uz.pdp.clickuztransactionsservice.proxy.TokenProxy;
import uz.pdp.clickuztransactionsservice.security.ClickUzAuthentication;

import java.io.IOException;

@Component
@NonNullApi
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final TokenProxy tokenProxy;
    public static String TOKEN = "token";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token.startsWith("Bearer ")){
            TOKEN = token;
            CustomResponseEntity<ClickUzAuthentication> verify = tokenProxy.verify(token.split(" ")[1]);
            if (verify.getBody() != null){
                SecurityContextHolder.getContext().setAuthentication(
                        ClickUzAuthentication.cast(verify.getBody())
                );
            }
        }
        filterChain.doFilter(request,response);
    }
}
