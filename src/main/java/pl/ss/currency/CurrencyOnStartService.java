package pl.ss.currency;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pl.ss.currency.domain.Currency;
import pl.ss.currency.domain.CurrencyRate;
import pl.ss.currency.repository.CurrencyRepository;
import pl.ss.currency.service.CurrencyService;

@Component
public class CurrencyOnStartService implements CommandLineRunner {

    private final CurrencyService currencyService;
    private final CurrencyRepository currencyRepository;

    public CurrencyOnStartService(CurrencyService currencyService, CurrencyRepository currencyRepository) {
        this.currencyService = currencyService;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Currency currency = new Currency("USD", "A","test desc");
        
        
        
        currency.addNewRateByDate(new CurrencyRate(currency, LocalDate.parse("2020-02-18"), BigDecimal.valueOf(3.1265)));
        currency.addNewRateByDate(new CurrencyRate(currency, LocalDate.parse("2020-02-17"), BigDecimal.valueOf(3.4565)));
        currency.addNewRateByDate(new CurrencyRate(currency, LocalDate.parse("2020-02-14"), BigDecimal.valueOf(3.6565)));       
        
		currencyRepository.save(currency);


    }
}
