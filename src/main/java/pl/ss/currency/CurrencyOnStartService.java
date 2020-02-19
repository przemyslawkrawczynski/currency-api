package pl.ss.currency;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pl.ss.currency.domain.Currency;
import pl.ss.currency.domain.CurrencyRate;
import pl.ss.currency.repository.CurrencyRateRepository;
import pl.ss.currency.repository.CurrencyRepository;
import pl.ss.currency.service.CurrencyService;

@Component
public class CurrencyOnStartService implements CommandLineRunner {

    private final CurrencyService currencyService;
    private final CurrencyRepository currencyRepository;
    private final CurrencyRateRepository currencyRateRepository;

    public CurrencyOnStartService(CurrencyService currencyService, CurrencyRepository currencyRepository, CurrencyRateRepository currencyRateRepository) {
        this.currencyService = currencyService;
        this.currencyRepository = currencyRepository;
        this.currencyRateRepository = currencyRateRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Currency currency = new Currency("USD", "A","Dolar amerykański");
        Random random  = new Random();




        List<CurrencyRate> currencyList = new ArrayList<>();

        LocalDate date = LocalDate.now();
            for (int i=0; i <10000; i++) {
                double randomValue = 3.125 + (random.nextDouble()) - 0.018;

                currencyList.add(new CurrencyRate(currency, date, new BigDecimal(randomValue).setScale(4, BigDecimal.ROUND_HALF_EVEN)));
                date = date.minusDays(1);
        }

		currencyRepository.save(currency);
        currencyRateRepository.saveAll(currencyList);

        Currency currency2 = new Currency("SEK", "A","Korona szwedzka");

        List<CurrencyRate> currencyList2 = new ArrayList<>();
        date = LocalDate.now();
        for (int i=0; i <10000; i++) {
            double randomValue =  (random.nextDouble());
            currencyList2.add(new CurrencyRate(currency2, date, new BigDecimal(randomValue).setScale(4, BigDecimal.ROUND_HALF_EVEN)));
            date = date.minusDays(1);
        }

        currencyRepository.save(currency2);
        currencyRateRepository.saveAll(currencyList2);

        Currency currency3 = new Currency("EURO", "A","Euro");

        List<CurrencyRate> currencyList3 = new ArrayList<>();
        date = LocalDate.now();
        for (int i=0; i <10000; i++) {
            double randomValue = 4 + (random.nextDouble());
            currencyList3.add(new CurrencyRate(currency3, date, new BigDecimal(randomValue).setScale(4, BigDecimal.ROUND_HALF_EVEN)));
            date = date.minusDays(1);
        }

        currencyRepository.save(currency3);
        currencyRateRepository.saveAll(currencyList3);

    }
}
