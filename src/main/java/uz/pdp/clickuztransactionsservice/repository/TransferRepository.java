package uz.pdp.clickuztransactionsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.clickuztransactionsservice.entity.Card;
import uz.pdp.clickuztransactionsservice.entity.Transfer;
import uz.pdp.clickuztransactionsservice.entity.enums.Status;

import java.math.BigDecimal;
import java.util.List;
@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {
    List<Transfer> findAllByAmount(BigDecimal amount);
    List<Transfer> findAllByStatus(Status status);
    List<Transfer> findAllByReceiverCardId(Long cardId);
    List<Transfer> findAllBySenderCardId(Long cardId);
}