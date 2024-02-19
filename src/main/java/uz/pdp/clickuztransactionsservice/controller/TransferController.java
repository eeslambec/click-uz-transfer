package uz.pdp.clickuztransactionsservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.clickuztransactionsservice.dto.CustomResponseEntity;
import uz.pdp.clickuztransactionsservice.entity.Transfer;
import uz.pdp.clickuztransactionsservice.entity.enums.Status;
import uz.pdp.clickuztransactionsservice.service.TransferService;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transfer")
public class TransferController {
    private final TransferService transferService;
    @PostMapping("/")
    public CustomResponseEntity<?> transfer(@RequestBody Transfer transfer){
        return CustomResponseEntity.ok(transferService.transfer(transfer));
    }
    @GetMapping("/{id}")
    public CustomResponseEntity<?> getById(@PathVariable Long id){
        return CustomResponseEntity.ok(transferService.getById(id));
    }
    @GetMapping("/all")
    public CustomResponseEntity<?> getAll(){
        return CustomResponseEntity.ok(transferService.getAll());
    }
    @GetMapping("/cardId/receiver/{id}")
    public CustomResponseEntity<?> getByReceiverCardId(@PathVariable Long id){
        return CustomResponseEntity.ok(transferService.getByReceiverCardId(id));
    }
    @GetMapping("/cardNumber/receiver/{number}")
    public CustomResponseEntity<?> getByReceiverCardNumber(@PathVariable String number){
        return CustomResponseEntity.ok(transferService.getByReceiverCardNumber(number));
    }
    @GetMapping("/cardId/sender/{id}")
    public CustomResponseEntity<?> getBySenderCardId(@PathVariable Long id){
        return CustomResponseEntity.ok(transferService.getBySenderCardId(id));
    }
    @GetMapping("/cardNumber/sender/{number}")
    public CustomResponseEntity<?> getBySenderCardNumber(@PathVariable String number){
        return CustomResponseEntity.ok(transferService.getBySenderCardNumber(number));
    }
    @GetMapping("/status/{status}")
    public CustomResponseEntity<?> getByStatus(@PathVariable Status status){
        return CustomResponseEntity.ok(transferService.getAllByStatus(status));
    }
    @GetMapping("/amount/{amount}")
    public CustomResponseEntity<?> getByAmount(@PathVariable BigDecimal amount){
        return CustomResponseEntity.ok(transferService.getAllByAmount(amount));
    }
}
