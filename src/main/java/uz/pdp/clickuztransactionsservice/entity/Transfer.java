package uz.pdp.clickuztransactionsservice.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.clickuztransactionsservice.entity.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private Card senderCard;
    @Transient
    private Card receiverCard;
    private BigDecimal amount;
    private Status status;
    private LocalDateTime transferDate;
}
