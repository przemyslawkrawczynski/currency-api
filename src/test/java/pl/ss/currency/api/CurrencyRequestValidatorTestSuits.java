package pl.ss.currency.api;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import pl.ss.currency.dtos.request.CurrencyRequest;
import pl.ss.currency.exception.ValidationException;

public class CurrencyRequestValidatorTestSuits {
	
//	private final RequestValidator requestValidator = new CurrencyRequestValidator();
//	
//	@Test
//	public void validate() {
//		
//		CurrencyRequest request = new CurrencyRequest.CurrencyRequestBuilder()
//				.currencyCode("USD")
//				.setDate(LocalDate.now()) 
//				.setTable("A")
//				.build();
//		
//		requestValidator.validate(request);	
//		
//	}
//		
//	@Test
//	public void validateCode() {
//		
//		CurrencyRequest incorrectCurrencyNumberRequest = new CurrencyRequest.CurrencyRequestBuilder()
//				.currencyCode("PLN")
//				.setDate(LocalDate.now()) 
//				.setTable("A")
//				.build();
//		
//		ValidationException ex = assertThrows(ValidationException.class,
//				() -> requestValidator.validate(incorrectCurrencyNumberRequest));
//		
//		Assert.assertEquals("Wrong argument CurrencyCode: " + incorrectCurrencyNumberRequest.getCurrencyCode(), ex.getMessage());
//	}
//	
//	@Test
//	public void validateTable() {
//		
//		CurrencyRequest incorectTableRequest = new CurrencyRequest.CurrencyRequestBuilder()
//				.currencyCode("USD")
//				.setDate(LocalDate.now()) 
//				.setTable("G")
//				.build();
//		
//		ValidationException ex = assertThrows(ValidationException.class,
//				() -> requestValidator.validate(incorectTableRequest));
//		
//		Assert.assertEquals("Wrong argument tableName: " + incorectTableRequest.getTableName(), ex.getMessage());
//	}
//	
//	@Test
//	public void validateDate() {
//		
//		CurrencyRequest incorectDateRequest = new CurrencyRequest.CurrencyRequestBuilder()
//				.currencyCode("USD")
//				.setDate(LocalDate.now().plusDays(2L))
//				.setTable("A")
//				.build();
//		
//		ValidationException ex = assertThrows(ValidationException.class,
//				() -> requestValidator.validate(incorectDateRequest));
//		
//		Assert.assertEquals("Wrong argument date: " + incorectDateRequest.getOnDate() + ". Date can't be after " + LocalDate.now(), ex.getMessage());
//	}
		
}


