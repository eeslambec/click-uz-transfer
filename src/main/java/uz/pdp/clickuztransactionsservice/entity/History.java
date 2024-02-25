package uz.pdp.clickuztransactionsservice.entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import uz.pdp.clickuztransactionsservice.entity.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class History {
    private Long id;
    private String receiverCardNumber;
    private String senderCardNumber;
    private BigDecimal amount;
    private Long serviceId;
    private LocalDateTime transactionDateTime;
    private Status status;
}
