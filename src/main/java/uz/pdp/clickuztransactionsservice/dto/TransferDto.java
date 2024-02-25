package uz.pdp.clickuztransactionsservice.dto;

import lombok.*;
import uz.pdp.clickuztransactionsservice.entity.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TransferDto {
    private Long id;
    private Long senderCardId;
    private Long receiverCardId;
    private BigDecimal amount;
    private Status status;
    private LocalDateTime transferDate;
}
