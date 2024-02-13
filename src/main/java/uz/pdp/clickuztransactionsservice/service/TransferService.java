package uz.pdp.clickuztransactionsservice.service;

import org.springframework.stereotype.Service;
import uz.pdp.clickuztransactionsservice.entity.Transfer;
import uz.pdp.clickuztransactionsservice.dto.TransferDto;
import uz.pdp.clickuztransactionsservice.entity.enums.Status;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface TransferService {
    Transfer transfer(TransferDto transferDto);
    Transfer getById(Long id);
    List<Transfer> getByReceiverCardId(Long id);
    List<Transfer> getBySenderCardId(Long id);
    List<Transfer> getAll();
    List<Transfer> getAllByStatus(Status status);
    List<Transfer> getAllByAmount(BigDecimal amount);
    List<Transfer> getByReceiverCardNumber(String cardNumber);
    List<Transfer> getBySenderCardNumber(String cardNumber);
}
