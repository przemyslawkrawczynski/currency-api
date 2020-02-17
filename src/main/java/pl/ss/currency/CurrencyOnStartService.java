package pl.ss.currency;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.ss.currency.domain.Currency;
import pl.ss.currency.domain.CurrencyInfo;
import pl.ss.currency.dtos.request.CurrencyRequest;
import pl.ss.currency.repository.CurrencyRepository;
import pl.ss.currency.service.CurrencyService;

import java.math.BigDecimal;
import java.time.LocalDate;

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

        currencyRepository.save(new Currency("USD", "2020-02-16", "A", BigDecimal.TEN, "test desc"));


        CurrencyRequest currencyRequest = new CurrencyRequest.CurrencyRequestBuilder()
                .currencyCode("USD")
                .setDate(LocalDate.parse("2020-02-14"))
                .setTable("A")
                .build();

        CurrencyInfo currencyInfo = currencyService.getCurrencyByRequest(currencyRequest);
        System.out.println("Wynik sprawdzenia: " + currencyInfo.toString());
    }
}
