package pl.ss.currency.api;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;

import pl.ss.currency.dataprovider.DataProvider;
import pl.ss.currency.dtos.request.CurrencyRequest;
import pl.ss.currency.dtos.response.CurrencyInfo;
import pl.ss.currency.mapper.CurrencyMapperProvider;
import pl.ss.currency.validator.RequestValidator;

public class CurrencyFromNbpXMLProviderTestSuits {
	
	private CurrencyFromNbpXMLProvider currencyProvider;

	@Test
	public void shouldVerifyHowMuchTimesProvidersMethodAreUsed() {

		// Given
		CurrencyRequest testedCurrencyRequest = null;
		String testDataResponse = "XML";
		String prepareCurrencyCodeToResponse = "USD";
		String prepareCurrencyDateToResponse = "2020-02-10";
		BigDecimal preparedRateToResponse = BigDecimal.ZERO;
		
		CurrencyInfo preparedResponse = new CurrencyInfo.CurrencyInfoBuilder()
				.setCurrencyCode(prepareCurrencyCodeToResponse)
				.setCurrencyRate(preparedRateToResponse)
				.setCheckingDate(prepareCurrencyDateToResponse)
				.build();			
				
		
		// Given - Mock
		DataProvider dataProvider = mock(DataProvider.class);
		CurrencyMapperProvider currencyMapperProvider = mock(CurrencyMapperProvider.class);
		RequestValidator requestValidator = mock(RequestValidator.class); 

		currencyProvider = new CurrencyFromNbpXMLProvider(dataProvider, currencyMapperProvider, requestValidator);

		// Mock Learning
		doNothing().when(requestValidator).validate(testedCurrencyRequest);
		when(dataProvider.getCurrencyDataByRequest(testedCurrencyRequest)).thenReturn(testDataResponse);
		when(currencyMapperProvider.mapToCurrencyInfo(testDataResponse)).thenReturn(preparedResponse);


		//When
		
		CurrencyInfo response = currencyProvider.getCurrencyInfo(testedCurrencyRequest);
		
		//Then
		verify(requestValidator, times(1)).validate(testedCurrencyRequest);
		verify(currencyMapperProvider, times(1)).mapToCurrencyInfo(testDataResponse);

	}

}
