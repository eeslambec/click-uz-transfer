package uz.pdp.clickuztransactionsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.clickuztransactionsservice.entity.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Stack;

@Getter
@AllArgsConstructor
public class TransferDto {
    private Long senderCardId;
    private Long receiverCardId;
    private BigDecimal amount;
    private LocalDateTime transferDate = LocalDateTime.now();
    private Status status = Status.FAILED;
}
