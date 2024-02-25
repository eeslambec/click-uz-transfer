package uz.pdp.clickuztransactionsservice.service;

import org.springframework.stereotype.Service;
import uz.pdp.clickuztransactionsservice.dto.TransferDto;
import uz.pdp.clickuztransactionsservice.entity.History;

@Service
public interface TransferService {
    History transfer(TransferDto transferDto);
}
