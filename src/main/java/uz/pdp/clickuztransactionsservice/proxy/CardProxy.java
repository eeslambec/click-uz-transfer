package uz.pdp.clickuztransactionsservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.clickuztransactionsservice.dto.CustomResponseEntity;
import uz.pdp.clickuztransactionsservice.dto.SetBalanceDto;
import uz.pdp.clickuztransactionsservice.entity.Card;

@FeignClient(value = "click-uz-cards")
public interface CardProxy {
    @GetMapping("/card/{id}")
    CustomResponseEntity<Card> getById(@PathVariable Long id);
    @PostMapping("/card/balance")
    void setBalance(@RequestBody SetBalanceDto setBalanceDto);
    @GetMapping("/card/cardNumber/{cardNumber}")
    CustomResponseEntity<Card> getByNumber(@PathVariable String cardNumber);
}