package uz.pdp.clickuztransactionsservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uz.pdp.clickuztransactionsservice.dto.CustomResponseEntity;
import uz.pdp.clickuztransactionsservice.dto.SetBalanceDto;
import uz.pdp.clickuztransactionsservice.dto.TransferDto;
import uz.pdp.clickuztransactionsservice.entity.Card;
import uz.pdp.clickuztransactionsservice.entity.History;
import uz.pdp.clickuztransactionsservice.entity.enums.Status;
import uz.pdp.clickuztransactionsservice.exception.BadRequestException;
import uz.pdp.clickuztransactionsservice.exception.InvalidArgumentException;
import uz.pdp.clickuztransactionsservice.exception.NullOrEmptyException;
import uz.pdp.clickuztransactionsservice.proxy.CardProxy;
import uz.pdp.clickuztransactionsservice.proxy.HistoryProxy;
import uz.pdp.clickuztransactionsservice.security.filter.JwtTokenFilter;
import uz.pdp.clickuztransactionsservice.service.TransferService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

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
    private final CardProxy cardProxy;
    private final HistoryProxy historyProxy;

    @Override
    public History transfer(TransferDto transferDto) {
        if (transferDto.getSenderCardId() == null)
            throw new NullOrEmptyException("Sender ID");
        if (transferDto.getReceiverCardId() == null)
            throw new NullOrEmptyException("Receiver ID");
        if (transferDto.getAmount() == null)
            throw new NullOrEmptyException("Amount");
        if (transferDto.getSenderCardId().equals(transferDto.getReceiverCardId()))
            throw new InvalidArgumentException("Card Id");
        CustomResponseEntity<Card> senderProxyCardById = cardProxy.getById(JwtTokenFilter.TOKEN,transferDto.getSenderCardId());
        CustomResponseEntity<Card> receiverProxyCardById = cardProxy.getById(JwtTokenFilter.TOKEN,transferDto.getReceiverCardId());
        if (senderProxyCardById.getBody() == null)
            throw new BadRequestException(senderProxyCardById.getErrorMessage());
        if (receiverProxyCardById.getBody() == null)
            throw new BadRequestException(receiverProxyCardById.getErrorMessage());
        Card senderCard = senderProxyCardById.getBody();
        Card receiverCard = receiverProxyCardById.getBody();
        BigDecimal commission = transferDto.getAmount().divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(this.commission));
        BigDecimal cashback = transferDto.getAmount().divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(this.cashback));
        Status status = Status.FAILED;
        if ((transferDto.getAmount().compareTo(min) < 0
                && transferDto.getAmount().compareTo(max) > 0 &&
                transferDto.getAmount().add(commission).compareTo(senderCard.getBalance()) >= 0)
        ) {
            status = Status.SUCCESS;
            BigDecimal newBalance = senderCard.getBalance().subtract(transferDto.getAmount().add(commission));
            newBalance = newBalance.add(cashback);
            cardProxy.setBalance(JwtTokenFilter.TOKEN,new SetBalanceDto(senderCard.getId(), newBalance));
            cardProxy.setBalance(JwtTokenFilter.TOKEN,new SetBalanceDto(receiverCard.getId(), receiverCard.getBalance().add(transferDto.getAmount())));
        }
        CustomResponseEntity<History> responseEntity = historyProxy.create(JwtTokenFilter.TOKEN,new History(
                null,
                receiverCard.getCardNumber(),
                senderCard.getCardNumber(),
                transferDto.getAmount(),
                1L,
                LocalDate.now().atTime(LocalTime.now().getHour(), LocalTime.now().getMinute(), 0, 0),
                status
        ));
        if (responseEntity.getBody() == null)
            throw new BadRequestException(responseEntity.getErrorMessage());
        return responseEntity.getBody();
    }
}

