package pl.ss.currency.api.spring;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import pl.ss.currency.domain.Currency;
import pl.ss.currency.domain.CurrencyRate;
import pl.ss.currency.dtos.request.CurrencyRequest;
import pl.ss.currency.dtos.response.CurrencyInfo;
import pl.ss.currency.dtos.response.CurrencyRateDto;
import pl.ss.currency.repository.CurrencyRateRepository;
import pl.ss.currency.repository.CurrencyRepository;
import pl.ss.currency.service.CurrencyService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("TEST")
@Transactional
public class CurrencyServiceTestSuits {

	@Autowired
	private CurrencyRepository currencyRepository;
	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private CurrencyRateRepository currencyRateRepository;
	@PersistenceContext
	EntityManager entityManager;

	@Test
	public void should_get_Rate_Value_From_Database() {

		// Given
		Currency currencyEUR = new Currency("EUR", "A", "Euro");
		currencyRepository.save(currencyEUR);

		LocalDate rateDate = LocalDate.now();
		BigDecimal rateValue = BigDecimal.valueOf(3.1234);
		CurrencyRate rate = new CurrencyRate(currencyEUR, rateDate, rateValue);
		currencyRateRepository.save(rate);

		CurrencyRequest currencyRequest = new CurrencyRequest.CurrencyRequestBuilder().currencyCode("EUR")
				.setDate(rateDate).build();

		// When
		CurrencyInfo resultCurrencyInfo = currencyService.getCurrencyByRequest(currencyRequest);
		BigDecimal resultValue = resultCurrencyInfo.getCurrencyRate();

		// Then
		Assert.assertEquals(rateValue, resultValue);

	}

	@Test
	public void should_get_value_from_DataProvider_And_Update_Database() {

		// Given
		String currencyCode = "EUR";
		LocalDate serchingDate = LocalDate.parse("2020-02-20");

		CurrencyRequest currencyRequest = new CurrencyRequest.CurrencyRequestBuilder().currencyCode(currencyCode)
				.setDate(serchingDate).build();

		boolean isExistInDatabaseBeforeRequest = currencyRepository
				.isExistByDateAndCurrencyCode(serchingDate, currencyCode) > 0;

		// When
		CurrencyInfo resultCurrencyInfo = currencyService.getCurrencyByRequest(currencyRequest);
		LocalDate resultDate = resultCurrencyInfo.getCheckingDate();

		boolean isExistInDatabaseAfterRequest = currencyRepository
				.isExistByDateAndCurrencyCode(serchingDate, currencyCode) > 0;

		// Then
		Assert.assertTrue(isExistInDatabaseAfterRequest != isExistInDatabaseBeforeRequest);
		Assert.assertEquals(serchingDate, resultDate);

	}

	@Test
	public void should_delete_Currency_by_Id_Compare_Database_Size() {

		// Given

		String query = "SELECT count(0) FROM Currency";
		
		long numbersOfEntitysOnStart = (Long) entityManager.createQuery(query)
				.getSingleResult();

		Currency currencyEUR = new Currency("EUR", "A", "Euro");
		entityManager.persist(currencyEUR);
		long savedEntityId = currencyEUR.getId();

		long numbersOfEntitysBeforeDelete = (Long) entityManager.createQuery(query)
				.getSingleResult();

		// When
		currencyService.deleteCurrencyById(savedEntityId);
		long numbersOfEntitysAfter = (Long) entityManager.createQuery(query)
				.getSingleResult();

		// Then
		Assert.assertEquals(numbersOfEntitysOnStart, numbersOfEntitysAfter);
		Assert.assertEquals(numbersOfEntitysOnStart + 1, numbersOfEntitysBeforeDelete);

	}

	@Test
	public void should_update_firstSavedCurrency_for_new_Code_and_Description() {

		// When
		String currencyCode = "EUR";
		Currency currencyEUR = new Currency(currencyCode, "A", "Euro");
		entityManager.persist(currencyEUR);
		long savedEntityId = currencyEUR.getId();

		String newCurrencyCode = "USD";
		String newDescription = "Dolar amerykański";

		Currency updatedCurrency = new Currency(savedEntityId, newCurrencyCode, "A", newDescription);

		// When
		currencyService.updateCurrency(updatedCurrency);

		Currency currencyUpdated = entityManager.find(Currency.class, savedEntityId);
		String resultCurrencyCode = currencyUpdated.getCurrencyCode();
		String resultDesc = currencyUpdated.getCurrencyDescription();

		// Then
		Assert.assertEquals(newDescription, resultDesc);
		Assert.assertEquals(newCurrencyCode, resultCurrencyCode);

	}
	
	@Test
	public void should_get_rate_2_days_before_request_Date_from_database() {

		//Given
		
		LocalDate reqestDateShouldBySunday = LocalDate.parse("2020-02-16");
		
		String currencyCode = "EUR";
		Currency currencyEUR = new Currency(currencyCode, "A", "Euro");
		currencyRepository.save(currencyEUR);		
		
		BigDecimal rateValue = BigDecimal.valueOf(3.1234);
		CurrencyRate rate = new CurrencyRate(currencyEUR, reqestDateShouldBySunday.minusDays(2), rateValue);
		currencyRateRepository.save(rate);
		
		CurrencyRequest currencyRequest = new CurrencyRequest.CurrencyRequestBuilder().currencyCode(currencyCode)
				.setDate(reqestDateShouldBySunday).build();
		
		//When
		CurrencyRateDto resultRateDto = currencyService.getCurrencyActualData(currencyRequest);
		
		String resultCode = resultRateDto.getCurrencyCode();
		LocalDate dateOfRateFromResult = resultRateDto.getRateDate(); 
		
		//ExpectedValue
		LocalDate dateOfRateExpected = reqestDateShouldBySunday.minusDays(2);
		
		//Then
		Assert.assertEquals(currencyCode, resultCode);
		Assert.assertEquals(dateOfRateExpected, dateOfRateFromResult);
	}
	
	@Test
	public void should_get_rate_2_days_before_request_and_update_Database_for_new_Currency_And_currencyRate() {

		//Given
		
		String currencyCode = "EUR";
		LocalDate serchingDate = LocalDate.parse("2020-02-16");
		
		boolean isExistInDatabaseBeforeRequest = currencyRepository.isExistByDateAndCurrencyCode(serchingDate, currencyCode) > 0;				
						
		CurrencyRequest currencyRequest = new CurrencyRequest.CurrencyRequestBuilder().currencyCode(currencyCode)
				.setDate(serchingDate).build();
		
		//When
		CurrencyRateDto resultRateDto = currencyService.getCurrencyActualData(currencyRequest);
		
		//ResultValues
		boolean isExistInDatabaseAfterRequest = currencyRepository.isExistByDateAndCurrencyCode(serchingDate.minusDays(2), currencyCode) > 0;
		String resultCode = resultRateDto.getCurrencyCode();
		LocalDate dateOfRateFromResult = resultRateDto.getRateDate(); 
		
	
		//ExpectedValue
		LocalDate dateOfRateExpected = serchingDate.minusDays(2);
		
		//Then
		Assert.assertFalse(isExistInDatabaseBeforeRequest);
		Assert.assertTrue(isExistInDatabaseAfterRequest);
		Assert.assertEquals(currencyCode, resultCode);
		Assert.assertEquals(dateOfRateExpected, dateOfRateFromResult);
	}		

}
