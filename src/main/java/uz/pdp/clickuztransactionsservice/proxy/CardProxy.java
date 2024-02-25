package uz.pdp.clickuztransactionsservice.proxy;

import feign.HeaderMap;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import uz.pdp.clickuztransactionsservice.dto.CustomResponseEntity;
import uz.pdp.clickuztransactionsservice.dto.SetBalanceDto;
import uz.pdp.clickuztransactionsservice.entity.Card;

import java.util.Map;

@FeignClient(value = "click-uz-cards")
public interface CardProxy {
    @GetMapping("/card/{id}")
    CustomResponseEntity<Card> getById(@RequestHeader("Authorization") String token, @PathVariable Long id);
    @PostMapping("/card/balance")
    void setBalance(@RequestHeader("Authorization") String token,@RequestBody SetBalanceDto setBalanceDto);
    @GetMapping("/card/cardNumber/{cardNumber}")
    CustomResponseEntity<Card> getByNumber(@RequestHeader("Authorization") String token,@PathVariable String cardNumber);
}