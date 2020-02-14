package pl.ss.currency.validator;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import pl.ss.currency.dataprovider.DataProvider;
import pl.ss.currency.dataprovider.DatabaseDataProvider;
import pl.ss.currency.dtos.request.CurrencyRequest;

public class DataProviderResponseValidatorTestSuits {
	
	private DataProviderResponseResolver responseResolver = new DataProviderResponseValidator();
	
	@Test
	public void shouldCheckThatAlgoritmGivsCorrectResponse() {
		
		//Given - Request
		String currencyShortCode = "USD";
		String currencyTableRequest = "A";
		LocalDate dateWhichIsDefineInDatabaseProvider = LocalDate.of(2020,02,10);
		
		CurrencyRequest request= new CurrencyRequest.CurrencyRequestBuilder()
				.currencyCode(currencyShortCode)
				.setTable(currencyTableRequest)
				.setDate(dateWhichIsDefineInDatabaseProvider)
				.build();
		
	
		//Given - Data Provider
		DataProvider dataProvider = new DatabaseDataProvider();
		String expectedResponse = "USD;A;2020-02-10;3.9815";
		
		//When
		String response = responseResolver.getResponse(request, dataProvider);
		
		//Then
		Assert.assertEquals(expectedResponse, response);
	}
	
	@Test
	public void shouldCheckThatAlgoritmGivsCorrectResponseWhenMustPrepareAnotherDayToRequest() {
		
		//Given - Request
		String currencyShortCode = "USD";
		String currencyTableRequest = "A";
		LocalDate dateWhichIsntDefineInDatabaseProvider = LocalDate.of(2020,02,9);
		
		CurrencyRequest request= new CurrencyRequest.CurrencyRequestBuilder()
				.currencyCode(currencyShortCode)
				.setTable(currencyTableRequest)
				.setDate(dateWhichIsntDefineInDatabaseProvider)
				.build();
		
	
		//Given - Data Provider
		DataProvider dataProvider = new DatabaseDataProvider();
		String expectedResponse = "USD;A;2020-02-07;3.9815";
		
		//When
		String response = responseResolver.getResponse(request, dataProvider);
		
		//Then
		Assert.assertEquals(expectedResponse, response);
	}

}
