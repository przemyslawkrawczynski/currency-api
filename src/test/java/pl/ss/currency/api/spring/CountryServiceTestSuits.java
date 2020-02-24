package pl.ss.currency.api.spring;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import pl.ss.currency.domain.Country;
import pl.ss.currency.domain.Currency;
import pl.ss.currency.repository.CountryRepository;
import pl.ss.currency.repository.CurrencyRepository;
import pl.ss.currency.service.CountryService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("TEST")
public class CountryServiceTestSuits {
	
	@Autowired
	private CurrencyRepository currencyRepository;
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private CountryService countryService;
	
	
	@Test
	public void should_get_Germany_as_Country_Where_Multiple_Currency() {
		
		//Given
		Country germany = new Country("Germany");
		Country poland = new Country("Poland");
		
		countryRepository.save(germany);
		countryRepository.save(poland);
		
		Currency currencyEuro = new Currency("EUR", "A","Euro");
		Currency currencyUSD = new Currency("USD", "A","Euro");
		
		currencyEuro.addCountry(germany);
		currencyUSD.addCountry(germany);
		currencyEuro.addCountry(poland);
		
		currencyRepository.saveAll(Stream.of(currencyEuro, currencyUSD).collect(Collectors.toList()));
		
		//When
		
		Map<String, Integer> countryResultName = countryService.getCountryWhereMoreThanOneCurrency();
		String expectedResult = germany.getCountryName();
			
		//Then
		
		Assert.assertTrue(countryResultName.containsKey(expectedResult));
		
	}

}
