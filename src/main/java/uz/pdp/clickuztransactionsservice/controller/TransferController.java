package uz.pdp.clickuztransactionsservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.clickuztransactionsservice.entity.Transfer;
import uz.pdp.clickuztransactionsservice.entity.enums.Status;
import uz.pdp.clickuztransactionsservice.service.TransferService;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RequestMapping("/transfer")
public class TransferController {
    private final TransferService transferService;
    @PostMapping("/")
    public ResponseEntity<?> transfer(@RequestBody Transfer transfer){
        return ResponseEntity.ok(transferService.transfer(transfer));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(transferService.getById(id));
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(transferService.getAll());
    }
    @GetMapping("/cardId/receiver/{id}")
    public ResponseEntity<?> getByReceiverCardId(@PathVariable Long id){
        return ResponseEntity.ok(transferService.getByReceiverCardId(id));
    }
    @GetMapping("/cardNumber/receiver/{number}")
    public ResponseEntity<?> getByReceiverCardNumber(@PathVariable String number){
        return ResponseEntity.ok(transferService.getByReceiverCardNumber(number));
    }
    @GetMapping("/cardId/sender/{id}")
    public ResponseEntity<?> getBySenderCardId(@PathVariable Long id){
        return ResponseEntity.ok(transferService.getBySenderCardId(id));
    }
    @GetMapping("/cardNumber/sender/{number}")
    public ResponseEntity<?> getBySenderCardNumber(@PathVariable String number){
        return ResponseEntity.ok(transferService.getBySenderCardNumber(number));
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<?> getByStatus(@PathVariable Status status){
        return ResponseEntity.ok(transferService.getAllByStatus(status));
    }
    @GetMapping("/amount/{amount}")
    public ResponseEntity<?> getByAmount(@PathVariable BigDecimal amount){
        return ResponseEntity.ok(transferService.getAllByAmount(amount));
    }
}
