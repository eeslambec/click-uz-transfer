package uz.pdp.clickuztransactionsservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.clickuztransactionsservice.dto.SetBalanceDto;
import uz.pdp.clickuztransactionsservice.entity.Card;

@FeignClient(value = "click-uz-cards",url = "http://localhost:6060")
public interface CardProxy {
    @GetMapping("/{id}")
    ResponseEntity<Card> getById(@PathVariable Long id);
    @PostMapping("/b")
    void setBalance(@RequestBody SetBalanceDto setBalanceDto);

    @GetMapping("/cardNumber")
    ResponseEntity<Card> getByNumber(String number);
}