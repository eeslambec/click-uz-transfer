package uz.pdp.clickuztransactionsservice.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.clickuztransactionsservice.entity.enums.CardType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Card {
    private Long id;
    private String name;
    private String cardNumber;
    private String expiryDate;
    @Enumerated(EnumType.STRING)
    private CardType type;
    private boolean isMain;
    private Byte cvv;
    private BigDecimal balance;
}


