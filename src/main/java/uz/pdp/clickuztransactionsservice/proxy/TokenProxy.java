package uz.pdp.clickuztransactionsservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.clickuztransactionsservice.dto.CustomResponseEntity;
import uz.pdp.clickuztransactionsservice.security.ClickUzAuthentication;

@FeignClient("click-uz-settings")
public interface TokenProxy {
    @GetMapping("/verify")
    CustomResponseEntity<ClickUzAuthentication> verify(@RequestParam String token);
}
