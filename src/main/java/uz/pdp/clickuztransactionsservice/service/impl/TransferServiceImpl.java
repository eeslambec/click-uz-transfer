package uz.pdp.clickuztransactionsservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import uz.pdp.clickuztransactionsservice.entity.Card;
import uz.pdp.clickuztransactionsservice.entity.Transfer;
import uz.pdp.clickuztransactionsservice.dto.TransferDto;
import uz.pdp.clickuztransactionsservice.entity.enums.Status;
import uz.pdp.clickuztransactionsservice.exception.MoneyMoreThanMaximumException;
import uz.pdp.clickuztransactionsservice.exception.NotEnoughMoneyException;
import uz.pdp.clickuztransactionsservice.exception.NotFoundException;
import uz.pdp.clickuztransactionsservice.exception.NullOrEmptyException;
import uz.pdp.clickuztransactionsservice.proxy.CardProxy;
import uz.pdp.clickuztransactionsservice.repository.TransferRepository;
import uz.pdp.clickuztransactionsservice.service.TransferService;

import java.math.BigDecimal;
import java.util.List;

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
    public Transfer transfer(TransferDto transferDto) {
        if (transferDto == null)
            throw new NullOrEmptyException("Transfer DTO");
        if (transferDto.getSenderCardId() == null)
            throw new NullOrEmptyException("Sender ID");
        if (transferDto.getReceiverCardId() == null)
            throw new NullOrEmptyException("Receiver ID");
        if (transferDto.getAmount() == null)
            throw new NullOrEmptyException("Amount");
        if (transferDto.getAmount().compareTo(min) < 0)
            throw new MoneyMoreThanMaximumException();
        if (transferDto.getAmount().compareTo(max) > 0)
            throw new MoneyMoreThanMaximumException();
        ResponseEntity<Card> senderProxyCardById = cardProxy.getById(transferDto.getSenderCardId());
        ResponseEntity<Card> receiverProxyCardById = cardProxy.getById(transferDto.getReceiverCardId());
        if (senderProxyCardById.getBody() == null)
            throw new NotFoundException("Sender card");
        if (receiverProxyCardById.getBody() == null)
            throw new NotFoundException("Receiver card");
        Card senderCard = senderProxyCardById.getBody();
        Card receiverCard = senderProxyCardById.getBody();
        if (transferDto.getAmount().compareTo(senderCard.getBalance()) > 0)
            throw new NotEnoughMoneyException();
          //This code minuses amount of money then adds cashback.
        cardProxy.setBalance(senderCard.getBalance().subtract(transferDto.getAmount()
                        .add(transferDto.getAmount().divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(commission))))
                        .add(transferDto.getAmount().divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(cashback))));
        return transferRepository.save(
                Transfer.builder()
                        .transferDate(transferDto.getTransferDate())
                        .amount(transferDto.getAmount())
                        .senderCard(senderCard)
                        .receiverCard(receiverCard)
                        .status(Status.SUCCESS)
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
            throw new NullOrEmptyException("ID");
        ResponseEntity<Card> byId = cardProxy.getById(id);
        if (byId.getBody() == null)
            throw new NotFoundException("Card");
        Card card = byId.getBody();
        return transferRepository.findAllByReceiverCard(card);
    }

    @Override
    public List<Transfer> getBySenderCardId(Long id) {
        if (id == null)
            throw new NullOrEmptyException("ID");
        ResponseEntity<Card> byId = cardProxy.getById(id);
        if (byId.getBody() == null)
            throw new NotFoundException("Card");
        Card card = byId.getBody();
        return transferRepository.findAllBySenderCard(card);
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
        if (cardNumber == null)
            throw new NullOrEmptyException("Card number");
        ResponseEntity<Card> byNumber = cardProxy.getByNumber(cardNumber);
        if (byNumber == null)
            throw new NullOrEmptyException("Card");
        Card card = byNumber.getBody();
        return transferRepository.findAllByReceiverCard(card);
    }

    @Override
    public List<Transfer> getBySenderCardNumber(String cardNumber) {
        if (cardNumber == null)
            throw new NullOrEmptyException("Card number");
        ResponseEntity<Card> byNumber = cardProxy.getByNumber(cardNumber);
        if (byNumber == null)
            throw new NullOrEmptyException("Card");
        Card card = byNumber.getBody();
        return transferRepository.findAllBySenderCard(card);
    }

}

