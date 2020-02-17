package pl.ss.currency.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.ss.currency.domain.Currency;
import pl.ss.currency.repository.CurrencyRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class DatabaseService {

    private final CurrencyRepository currencyRepository;

    public DatabaseService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public Currency getCurrencyByLocalDateAndCode(LocalDate date, String currencyCode) {
        return currencyRepository.getByCodeAndDate(date, currencyCode);
    }

    public boolean isExistByRequest(LocalDate date, String currencyCode) {
        System.out.println("Wyszukuje: " + date + " / " + currencyCode);
        return currencyRepository.isExistByDateAndCurrencyCode(date, currencyCode) > 0;
    }

    public void updateCurrencyByDate(Currency currency) {
        currencyRepository.save(currency);
    }

}
