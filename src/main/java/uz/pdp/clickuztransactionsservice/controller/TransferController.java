package uz.pdp.clickuztransactionsservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.clickuztransactionsservice.dto.TransferDto;
import uz.pdp.clickuztransactionsservice.entity.enums.Status;
import uz.pdp.clickuztransactionsservice.service.TransferService;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RequestMapping("/transfer")
public class TransferController {
    private final TransferService transferService;

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(transferService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(transferService.getAll());
    }

    @GetMapping("/cardID/{id}")
    public ResponseEntity<?> getByCardId(@PathVariable Long id){
        return ResponseEntity.ok(transferService.getByReceiverCardId(id));
    }
    @GetMapping("/cardNumber/{number}")
    public ResponseEntity<?> getByReceiverCardNumber(@PathVariable String number){
        return ResponseEntity.ok(transferService.getByReceiverCardNumber(number));
    }
    @GetMapping("/cardNumber/{number}")
    public ResponseEntity<?> getBySenderCardNumber(@PathVariable String number){
        return ResponseEntity.ok(transferService.getBySenderCardNumber(number));
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<?> getByStatus(@PathVariable Status status){
        return ResponseEntity.ok(transferService.getAllByStatus(status));
    }
    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferDto transferDto){
        return ResponseEntity.ok(transferService.transfer(transferDto));
    }
    @GetMapping("/amount/{amount}")
    public ResponseEntity<?> getByAmount(@PathVariable BigDecimal amount){
        return ResponseEntity.ok(transferService.getAllByAmount(amount));
    }
    @GetMapping("/senderCardId/{id}")
    public ResponseEntity<?> getBySenderCardId(@PathVariable Long id){
        return ResponseEntity.ok(transferService.getBySenderCardId(id));
    }
    @GetMapping("/receiverCardId/{id}")
    public ResponseEntity<?> getByReceiverCardId(@PathVariable Long id){
        return ResponseEntity.ok(transferService.getByReceiverCardId(id));
    }


}
