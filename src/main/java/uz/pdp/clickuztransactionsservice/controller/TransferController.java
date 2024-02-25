package uz.pdp.clickuztransactionsservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.clickuztransactionsservice.dto.CustomResponseEntity;
import uz.pdp.clickuztransactionsservice.dto.TransferDto;
import uz.pdp.clickuztransactionsservice.service.TransferService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transfer")
public class TransferController {
    private final TransferService transferService;
    @PostMapping("/")
    public CustomResponseEntity<?> transfer(@RequestBody TransferDto transferDto){
        return CustomResponseEntity.ok(transferService.transfer(transferDto));
    }
}
