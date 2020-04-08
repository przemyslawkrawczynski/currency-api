package pl.ss.currency.api.spring;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import pl.ss.currency.domain.Country;
import pl.ss.currency.domain.Currency;
import pl.ss.currency.domain.CurrencyRate;
import pl.ss.currency.dtos.response.raport.CurrencyRateValueDto;
import pl.ss.currency.repository.CountryRepository;
import pl.ss.currency.repository.CurrencyRateRepository;
import pl.ss.currency.repository.CurrencyRepository;
import pl.ss.currency.service.CurrencyReportService;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ActiveProfiles("TEST")
@Transactional
public class HibernateTasksTests {

	@Autowired
	CurrencyRepository currencyRepository;

	@Autowired
	CurrencyRateRepository currencyRateRepository;

	@Autowired
	CurrencyReportService currencyReportService;
	
	@Autowired 
	CountryRepository CountryRepository;
	

	@Test
	public void should_back_USD_currency_as_having_max_difference_between_rates_in_date_range() {
		
		should_assert_that_database_is_empty();
		
		// Given
		LocalDate dateFrom = LocalDate.now().minusDays(5);
		LocalDate dateTo = LocalDate.now();

		String currencyCodeUSD = "USD";
		String currencyCodeSEK = "SEK";

		generateDataToTest();

		BigDecimal maxRateUSD = currencyRateRepository
				.getMaxValueByCodeAndDateRange(currencyCodeUSD, dateFrom, dateTo).get(0).getRateValue();
		BigDecimal minRateUSD = currencyRateRepository
				.getMinValueByCodeAndDateRange(currencyCodeUSD, dateFrom, dateTo).get(0).getRateValue();
		BigDecimal differenceBetweenRatesInUSD = maxRateUSD.subtract(minRateUSD);

		BigDecimal maxRateSEK = currencyRateRepository
				.getMaxValueByCodeAndDateRange(currencyCodeSEK, dateFrom, dateTo).get(0).getRateValue();
		BigDecimal minRateSEK =  currencyRateRepository
				.getMinValueByCodeAndDateRange(currencyCodeSEK, dateFrom, dateTo).get(0).getRateValue();
		BigDecimal differenceBetweenRatesInSEK = maxRateSEK.subtract(minRateSEK);

		boolean hasUsaMoreDifferenceRatesInRange = (differenceBetweenRatesInUSD
				.compareTo(differenceBetweenRatesInSEK)) >= 0;

		String courencyCodeWhereMoreDifference = (hasUsaMoreDifferenceRatesInRange) ? "USD" : "SEK";

		// When
		String resultCurrencyCode = currencyReportService.getCurrencyWhereMaxDiffrenceRateOfTime(dateFrom, dateTo)
				.getCurrencyCode();

		// Then
		assertThat(resultCurrencyCode).isEqualTo(courencyCodeWhereMoreDifference);
		

	}

	@Test
	public void should_get_min_value_ByCode_And_Range() {
		
		should_assert_that_database_is_empty();
		
		// Given
		LocalDate dateFrom = LocalDate.now().minusDays(5);
		LocalDate dateTo = LocalDate.now();
		String currencyCode = "USD";

		Currency currencyUSD = new Currency(currencyCode, "A", "Dolar amerykański");
		int randomValueStart = 9;
		LocalDate rateDate = LocalDate.now();

		BigDecimal maxExpected = new BigDecimal(randomValueStart).setScale(4, BigDecimal.ROUND_HALF_EVEN);
		BigDecimal minExpected = null;

		List<CurrencyRate> currencyRates = new ArrayList<>();
		for (int i = 0; i < 5; i++) {

			BigDecimal startRate = new BigDecimal(randomValueStart).setScale(4, BigDecimal.ROUND_HALF_EVEN);
			currencyRates.add(new CurrencyRate(currencyUSD, rateDate, startRate));
			minExpected = startRate;
			startRate = startRate.subtract(BigDecimal.ONE);
			rateDate = rateDate.minusDays(1);
			randomValueStart--;
		}

		currencyRepository.save(currencyUSD);
		currencyRateRepository.saveAll(currencyRates);

		// When
		BigDecimal minValueResult = currencyReportService.getMinValueByCodeAndRange(currencyCode, dateFrom, dateTo)
				.getCurrencyRateValue();
		BigDecimal maxValueResult = currencyReportService.getMaxValueByCodeAndRange(currencyCode, dateFrom, dateTo)
				.getCurrencyRateValue();

		// Then
		assertThat(maxValueResult).isEqualTo(maxExpected);
		assertThat(minValueResult).isEqualTo(minExpected);

	}

	@Test
	public void should_get_5_lowest_And_5_Best_value_ByCode_and_Assert_Min_AND_Max_Values_From_Result_List() {
		
		should_assert_that_database_is_empty();

		// Given
		LocalDate dateFrom = LocalDate.now().minusDays(5);
		LocalDate dateTo = LocalDate.now();
		String currencyCode = "USD";

		Currency currencyUSD = new Currency(currencyCode, "A", "Dolar amerykański");
		int randomValueStart = 9;
		LocalDate rateDate = LocalDate.now();

		BigDecimal maxExpected = new BigDecimal(randomValueStart).setScale(4, BigDecimal.ROUND_HALF_EVEN);
		BigDecimal minExpected = null;

		List<CurrencyRate> currencyRates = new ArrayList<>();
		for (int i = 0; i < 8; i++) {

			BigDecimal startRate = new BigDecimal(randomValueStart).setScale(4, BigDecimal.ROUND_HALF_EVEN);
			currencyRates.add(new CurrencyRate(currencyUSD, rateDate, startRate));
			minExpected = startRate;
			startRate = startRate.subtract(BigDecimal.ONE);
			rateDate = rateDate.minusDays(1);
			randomValueStart--;
		}

		currencyRepository.save(currencyUSD);
		currencyRateRepository.saveAll(currencyRates);

		// When
		List<CurrencyRateValueDto> lowestRates = currencyReportService.get5LowestRates(currencyCode);
		List<CurrencyRateValueDto> bestRates = currencyReportService.get5BestRates(currencyCode);

		BigDecimal minValueFromlowestRates = lowestRates.get(0).getCurrencyRateValue();
		BigDecimal maxValuesFromHigherRates = bestRates.get(0).getCurrencyRateValue();

		// Then
		assertThat(minValueFromlowestRates).isEqualTo(minExpected);
		assertThat(maxValuesFromHigherRates).isEqualTo(maxExpected);
		assertThat(lowestRates.size()).isEqualTo(5);
		assertThat(bestRates.size()).isEqualTo(5);
		
	}

	private void generateDataToTest() {

		Random random = new Random();

		Currency currency1 = new Currency("SEK", "A", "Korona szwedzka");

		List<CurrencyRate> currencyList1 = new ArrayList<>();
		LocalDate date = LocalDate.now();
		for (int i = 0; i < 15; i++) {
			double randomValue = (random.nextDouble());
			currencyList1.add(new CurrencyRate(currency1, date,
					new BigDecimal(randomValue).setScale(4, BigDecimal.ROUND_HALF_EVEN)));
			date = date.minusDays(1);
		}

		currencyRepository.save(currency1);
		currencyRateRepository.saveAll(currencyList1);

		Currency currency2 = new Currency("USD", "A", "Dolar amerykański");
		date = LocalDate.now();

		List<CurrencyRate> currencyList2 = new ArrayList<>();
		for (int i = 0; i < 15; i++) {
			double randomValue = 3 + ((random.nextDouble()) * 2);
			currencyList2.add(new CurrencyRate(currency2, date,
					new BigDecimal(randomValue).setScale(4, BigDecimal.ROUND_HALF_EVEN)));
			date = date.minusDays(1);
		}

		currencyRepository.save(currency2);
		currencyRateRepository.saveAll(currencyList2);
	}
	
	
	@Test
	public void should_get_country_where_Currencys_Are_more_than_one() {
		
		//Given
		String countryNameWhereMoreThanOneString = "Niemcy";
		String countryNameWhereOnlyOneCurrency = "Francja";
		
        Country country1 = new Country(countryNameWhereMoreThanOneString);
        Country country2 = new Country(countryNameWhereOnlyOneCurrency);
        
        CountryRepository.saveAll(Stream.of(country1,country2).collect(Collectors.toList()));
		
		Currency currency1 = new Currency("SEK", "A", "Korona szwedzka");
		Currency currency2 = new Currency("USD", "A", "Dolar");
		Currency currency3 = new Currency("EUR", "A", "USD");
		
		currency1.addCountry(country1);
		currency2.addCountry(country1);
		currency3.addCountry(country2);
		
		currencyRepository.saveAll(Stream.of(currency1, currency2, currency3).collect(Collectors.toList()));
		
				
		//When
		String resultCountryName = CountryRepository.getCountryWhereCurrencysMoreThanOne().get(0).getCountryName();
		
		//Then
		assertThat(resultCountryName).isEqualTo(countryNameWhereMoreThanOneString);
		
	}
	
	private void should_assert_that_database_is_empty() {

		long currencyRepositorySize = currencyRepository.count();
		long currencyRateRepositorySize = currencyRateRepository.count();
		
		assertThat(currencyRateRepositorySize).isEqualTo(0);
		assertThat(currencyRepositorySize).isEqualTo(0);
	}

}
