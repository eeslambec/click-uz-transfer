package uz.pdp.clickuztransactionsservice.proxy;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import uz.pdp.clickuztransactionsservice.dto.CustomResponseEntity;
import uz.pdp.clickuztransactionsservice.entity.History;
@FeignClient("click-uz-reports")
public interface HistoryProxy {
    @PostMapping("/history/create")
    CustomResponseEntity<History> create(@RequestHeader("Authorization") String token, @RequestBody History history);
}
