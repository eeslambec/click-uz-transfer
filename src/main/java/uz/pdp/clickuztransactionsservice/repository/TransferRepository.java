package uz.pdp.clickuztransactionsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.clickuztransactionsservice.entity.Card;
import uz.pdp.clickuztransactionsservice.entity.Transfer;
import uz.pdp.clickuztransactionsservice.entity.enums.Status;

import java.math.BigDecimal;
import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    List<Transfer> findAllByAmount(BigDecimal amount);
    List<Transfer> findAllByStatus(Status status);
    List<Transfer> findAllByReceiverCard(Card card);
    List<Transfer> findAllBySenderCard(Card card);
}