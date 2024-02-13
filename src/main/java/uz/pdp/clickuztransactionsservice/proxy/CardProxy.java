package uz.pdp.clickuztransactionsservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.clickuztransactionsservice.entity.Card;

import java.math.BigDecimal;

@FeignClient( value = "click-uz-cards")
public interface CardProxy {
    @GetMapping("")
    ResponseEntity<Card> getById(Long id);

    @GetMapping("")
    void setBalance(BigDecimal balance);

    @GetMapping("")
    ResponseEntity<Card> getByNumber(String number);
}