package pl.ss.currency;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import pl.ss.currency.domain.Country;
import pl.ss.currency.domain.Currency;
import pl.ss.currency.domain.CurrencyRate;
import pl.ss.currency.repository.CountryRepository;
import pl.ss.currency.repository.CurrencyRateRepository;
import pl.ss.currency.repository.CurrencyRepository;
import pl.ss.currency.service.CurrencyService;

@Component
@Profile("!TEST")
public class CurrencyOnStartService implements CommandLineRunner {

    private final CurrencyService currencyService;
    private final CurrencyRepository currencyRepository;
    private final CurrencyRateRepository currencyRateRepository;
    private final CountryRepository countryRepository;

    public CurrencyOnStartService(CurrencyService currencyService, CurrencyRepository currencyRepository,
			CurrencyRateRepository currencyRateRepository, CountryRepository countryRepository) {
		super();
		this.currencyService = currencyService;
		this.currencyRepository = currencyRepository;
		this.currencyRateRepository = currencyRateRepository;
		this.countryRepository = countryRepository;
	}


	@Override
    public void run(String... args) throws Exception {
		
		Random random  = new Random();

        long start = System.currentTimeMillis();
        
        //Country
        List<Country> countryList = new ArrayList<Country>();
        
        Country country1 = new Country("Niemcy");
        Country country2 = new Country("Francja");
        Country country3 = new Country("Stany Zjednoczone");
        Country country4 = new Country("Chiny");
        Country country5 = new Country("Szwecja");
        Country country6 = new Country("Anglia");
        Country country7 = new Country("Rosja");
        
        

        countryRepository.saveAll(Stream.of(country1, country2, country3, country4, country5, country6, country7).collect(Collectors.toList()));
        //Currency
        Currency currencyUSD = new Currency("USD", "A","Dolar amerykański");



        List<CurrencyRate> currencyList = new ArrayList<>();

        LocalDate date = LocalDate.now();
            for (int i=0; i <100; i++) {
                double randomValue = 3.125 + (random.nextDouble()) - 0.018;

                currencyList.add(new CurrencyRate(currencyUSD, date, new BigDecimal(randomValue).setScale(4, BigDecimal.ROUND_HALF_EVEN)));
                date = date.minusDays(1);
        }

        currencyUSD.addCountry(country1);
        currencyUSD.addCountry(country3);
        currencyUSD.addCountry(country4);
        
		currencyRepository.save(currencyUSD);
        currencyRateRepository.saveAll(currencyList);

        Currency currency2 = new Currency("SEK", "A","Korona szwedzka");

        List<CurrencyRate> currencyList2 = new ArrayList<>();
        date = LocalDate.now();
        for (int i=0; i <100; i++) {
            double randomValue =  (random.nextDouble());
            currencyList2.add(new CurrencyRate(currency2, date, new BigDecimal(randomValue).setScale(4, BigDecimal.ROUND_HALF_EVEN)));
            date = date.minusDays(1);
        }

        currency2.addCountry(country5);
        currencyRepository.save(currency2);
        currencyRateRepository.saveAll(currencyList2);

        Currency currency3 = new Currency("EURO", "A","Euro");

        List<CurrencyRate> currencyList3 = new ArrayList<>();
        date = LocalDate.now();
        for (int i=0; i <100; i++) {
            double randomValue = 4 + (random.nextDouble());
            currencyList3.add(new CurrencyRate(currency3, date, new BigDecimal(randomValue).setScale(4, BigDecimal.ROUND_HALF_EVEN)));
            date = date.minusDays(1);
        }

        currency3.addCountry(country1);
        currency3.addCountry(country2);
        currency3.addCountry(country5);
        currency3.addCountry(country6);
        currencyRepository.save(currency3);
        currencyRateRepository.saveAll(currencyList3);
        
        Currency currency4 = new Currency("GBP", "A","Funt brytyjski");

        List<CurrencyRate> currencyList4 = new ArrayList<>();
        date = LocalDate.now();
        for (int i=0; i <100; i++) {
            double randomValue = 5 + (random.nextDouble());
            currencyList4.add(new CurrencyRate(currency4, date, new BigDecimal(randomValue).setScale(4, BigDecimal.ROUND_HALF_EVEN)));
            date = date.minusDays(1);
        }

        currency4.addCountry(country6);
        currencyRepository.save(currency4);
        currencyRateRepository.saveAll(currencyList4);
        
        Currency currency5 = new Currency("RUB", "A","Euro");

        List<CurrencyRate> currencyList5 = new ArrayList<>();
        date = LocalDate.now();
        for (int i=0; i <100; i++) {
            double randomValue = (random.nextDouble()) / 10;
            currencyList5.add(new CurrencyRate(currency5, date, new BigDecimal(randomValue).setScale(4, BigDecimal.ROUND_HALF_EVEN)));
            date = date.minusDays(1);
        }

        currency5.addCountry(country7);
        currencyRepository.save(currency5);
        currencyRateRepository.saveAll(currencyList5);

        System.out.println("Import trwał: " + (System.currentTimeMillis() - start)/1000);
    }
}
