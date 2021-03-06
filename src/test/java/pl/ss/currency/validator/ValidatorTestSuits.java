package pl.ss.currency.validator;

import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.ss.currency.dtos.request.CurrencyRequest;
import pl.ss.currency.exception.ValidationException;
import pl.ss.currency.validator.CurrencyRequestValidator;
import pl.ss.currency.validator.RequestValidator;



import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidatorTestSuits {
	
	private final RequestValidator validator = new CurrencyRequestValidator();
	
	
	@Test
	public void shouldThrowsValidationExceptionBecouseLocaldateIsNull() {
		
		//Given
		String currencyShortCode = "USD";
		LocalDate givingLocalDate = null;
		
		CurrencyRequest request= new CurrencyRequest.CurrencyRequestBuilder()
				.currencyCode(currencyShortCode)
				.setDate(givingLocalDate)
				.build();
		//When
		
		ValidationException ex = assertThrows(ValidationException.class,
				() -> validator.validate(request));
		
		//Then
		Assert.assertEquals("Wrong argument date on request: " + givingLocalDate, ex.getMessage());
		
	}
	
	@Test
	public void shouldThrowsValidationExceptionBecouseLocaldateIsInTheFuture() {
		
		//Given
		String currencyShortCode = "USD";
		String currencyTableRequest = "A";
		LocalDate givingLocalDate = LocalDate.now().plusDays(1);
		
		CurrencyRequest request= new CurrencyRequest.CurrencyRequestBuilder()
				.currencyCode(currencyShortCode)
				.setDate(givingLocalDate)
				.build();
		
		//When
		ValidationException ex = assertThrows(ValidationException.class,
				() -> validator.validate(request));
		
		//Then
		Assert.assertEquals("Wrong argument date: " + givingLocalDate + ". Date can't be after " + LocalDate.now(), ex.getMessage());
		
	}
	
	@Test
	public void shouldValidateCorrectNotThrowingAnyExceptions() {
		//Given
		String currencyShortCode = "USD";
		LocalDate givenDate = LocalDate.now();
		
		CurrencyRequest request= new CurrencyRequest.CurrencyRequestBuilder()
				.currencyCode(currencyShortCode)
				.setDate(givenDate)
				.build();
		
		//When
		int countThrows = 0;
		
		try {
			validator.validate(request);
		} catch (ValidationException ex) {
			countThrows++;
		}
		
		//Then
		int expectedWhenNotThrown = 0;
		Assert.assertEquals(expectedWhenNotThrown, countThrows);
	}
	
}
