package pl.ss.currency;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pl.ss.currency.domain.Country;
import pl.ss.currency.domain.Currency;
import pl.ss.currency.domain.CurrencyRate;
import pl.ss.currency.repository.CountryRepository;
import pl.ss.currency.repository.CurrencyRateRepository;
import pl.ss.currency.repository.CurrencyRepository;
import pl.ss.currency.service.CurrencyService;

@Component
public class CurrencyOnStartService implements CommandLineRunner {

    private final CurrencyService currencyService;
    private final CurrencyRepository currencyRepository;
    private final CurrencyRateRepository currencyRateRepository;
    private final CountryRepository countryRepository;

    public CurrencyOnStartService(CurrencyService currencyService, CurrencyRepository currencyRepository, CurrencyRateRepository currencyRateRepository, CountryRepository countryRepository) {
        this.currencyService = currencyService;
        this.currencyRepository = currencyRepository;
        this.currencyRateRepository = currencyRateRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        long start = System.currentTimeMillis();

        Country country = new Country("Niemcy");
        Country country1 = new Country("Francja");


        countryRepository.save(country);
        countryRepository.save(country1);


        Currency currency = new Currency("USD", "A","Dolar amerykański");
        Random random  = new Random();




        List<CurrencyRate> currencyList = new ArrayList<>();

        LocalDate date = LocalDate.now();
            for (int i=0; i <300000; i++) {
                double randomValue = 3.125 + (random.nextDouble()) - 0.018;

                currencyList.add(new CurrencyRate(currency, date, new BigDecimal(randomValue).setScale(4, BigDecimal.ROUND_HALF_EVEN)));
                date = date.minusDays(1);
        }

        currency.addCountry(country);
        currency.addCountry(country1);
		currencyRepository.save(currency);
        currencyRateRepository.saveAll(currencyList);

        Currency currency2 = new Currency("SEK", "A","Korona szwedzka");

        List<CurrencyRate> currencyList2 = new ArrayList<>();
        date = LocalDate.now();
        for (int i=0; i <300000; i++) {
            double randomValue =  (random.nextDouble());
            currencyList2.add(new CurrencyRate(currency2, date, new BigDecimal(randomValue).setScale(4, BigDecimal.ROUND_HALF_EVEN)));
            date = date.minusDays(1);
        }

        currency2.addCountry(country);
        currency2.addCountry(country1);
        currencyRepository.save(currency2);
        currencyRateRepository.saveAll(currencyList2);

        Currency currency3 = new Currency("EURO", "A","Euro");

        List<CurrencyRate> currencyList3 = new ArrayList<>();
        date = LocalDate.now();
        for (int i=0; i <400000; i++) {
            double randomValue = 4 + (random.nextDouble());
            currencyList3.add(new CurrencyRate(currency3, date, new BigDecimal(randomValue).setScale(4, BigDecimal.ROUND_HALF_EVEN)));
            date = date.minusDays(1);
        }

        currency3.addCountry(country1);
        currencyRepository.save(currency3);
        currencyRateRepository.saveAll(currencyList3);

        System.out.println("Import trwał: " + (System.currentTimeMillis() - start)/1000);
    }
}
