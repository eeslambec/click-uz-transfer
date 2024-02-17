package uz.pdp.clickuztransactionsservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.clickuztransactionsservice.dto.SetBalanceDto;
import uz.pdp.clickuztransactionsservice.entity.Card;
import uz.pdp.clickuztransactionsservice.entity.Transfer;
import uz.pdp.clickuztransactionsservice.entity.enums.Status;
import uz.pdp.clickuztransactionsservice.exception.InvalidArgumentException;
import uz.pdp.clickuztransactionsservice.exception.NotFoundException;
import uz.pdp.clickuztransactionsservice.exception.NullOrEmptyException;
import uz.pdp.clickuztransactionsservice.proxy.CardProxy;
import uz.pdp.clickuztransactionsservice.repository.TransferRepository;
import uz.pdp.clickuztransactionsservice.service.TransferService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {
    @Value("${click.uz.const.min}")
    private BigDecimal min;
    @Value("${click.uz.const.max}")
    private BigDecimal max;
    @Value("${click.uz.const.commission}")
    private Integer commission;
    @Value("${click.uz.const.cashback}")
    private Integer cashback;
    private final TransferRepository transferRepository;
    private final CardProxy cardProxy;

    @Override
    public Transfer transfer(Transfer transfer) {
        if (transfer.getSenderCardId() == null)
            throw new NullOrEmptyException("Sender ID");
        if (transfer.getReceiverCardId() == null)
            throw new NullOrEmptyException("Receiver ID");
        if (transfer.getAmount() == null)
            throw new NullOrEmptyException("Amount");
        if (transfer.getSenderCardId().equals(transfer.getReceiverCardId()))
            throw new InvalidArgumentException("Card Id");
        ResponseEntity<Card> senderProxyCardById = cardProxy.getById(transfer.getSenderCardId());
        ResponseEntity<Card> receiverProxyCardById = cardProxy.getById(transfer.getReceiverCardId());
        if (senderProxyCardById.getBody() == null)
            throw new NotFoundException("Sender card");
        if (receiverProxyCardById.getBody() == null)
            throw new NotFoundException("Receiver card");
        Card senderCard = senderProxyCardById.getBody();
        Card receiverCard = receiverProxyCardById.getBody();
        BigDecimal commission = transfer.getAmount().divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(this.commission));
        BigDecimal cashback = transfer.getAmount().divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(this.cashback));
        Status status = Status.FAILED;
        if ((transfer.getAmount().compareTo(min) < 0
                && transfer.getAmount().compareTo(max) > 0 &&
                transfer.getAmount().add(commission).compareTo(senderCard.getBalance()) > 0)
        ){
            status = Status.SUCCESS;
            BigDecimal newBalance = senderCard.getBalance().subtract(transfer.getAmount().add(commission));
            newBalance = newBalance.add(cashback);
            cardProxy.setBalance(new SetBalanceDto(senderCard.getId(),newBalance));
            cardProxy.setBalance(new SetBalanceDto(receiverCard.getId(),receiverCard.getBalance().add(transfer.getAmount())));
        }
        return transferRepository.save(
                Transfer.builder()
                        .transferDate(transfer.getTransferDate())
                        .amount(transfer.getAmount())
                        .senderCardId(senderCard.getId())
                        .receiverCardId(receiverCard.getId())
                        .transferDate(LocalDateTime.now())
                        .status(status)
                        .build()
        );
    }

    @Override
    public Transfer getById(Long id) {
        if (id == null)
            throw new NullOrEmptyException("ID");
        return transferRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Transfer")
        );
    }

    @Override
    public List<Transfer> getByReceiverCardId(Long id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        if (cardProxy.getById(id).getBody() == null)
            throw new NotFoundException("Card");
        return transferRepository.findAllByReceiverCardId(id);
    }

    @Override
    public List<Transfer> getBySenderCardId(Long id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        if (cardProxy.getById(id).getBody() == null)
            throw new NotFoundException("Card");
        return transferRepository.findAllBySenderCardId(id);
    }

    @Override
    public List<Transfer> getAll() {
        return transferRepository.findAll();
    }

    @Override
    public List<Transfer> getAllByStatus(Status status) {
        if (status == null)
            throw new NullOrEmptyException("Status");
        return transferRepository.findAllByStatus(status);
    }

    @Override
    public List<Transfer> getAllByAmount(BigDecimal amount) {
        if (amount == null)
            throw new NullOrEmptyException("Amount");
        return transferRepository.findAllByAmount(amount);
    }

    @Override
    public List<Transfer> getByReceiverCardNumber(String cardNumber) {
        if (isNullOrEmpty(cardNumber))
            throw new NullOrEmptyException("Card number");
        ResponseEntity<Card> byNumber = cardProxy.getByNumber(cardNumber);
        if (byNumber.getBody() == null)
            throw new NullOrEmptyException("Card");
        Card card = byNumber.getBody();
        return transferRepository.findAllByReceiverCardId(card.getId());
    }
    @Override
    public List<Transfer> getBySenderCardNumber(String cardNumber) {
        if (isNullOrEmpty(cardNumber))
            throw new NullOrEmptyException("Card number");
        ResponseEntity<Card> byNumber = cardProxy.getByNumber(cardNumber);
        if (byNumber.getBody() == null)
            throw new NullOrEmptyException("Card");
        Card card = byNumber.getBody();
        return transferRepository.findAllBySenderCardId(card.getId());
    }
    public boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }
}

