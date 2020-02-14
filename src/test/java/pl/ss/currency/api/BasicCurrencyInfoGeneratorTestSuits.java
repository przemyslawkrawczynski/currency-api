package pl.ss.currency.api;

import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import pl.ss.currency.domain.CurrencyInfo;
import pl.ss.currency.dtos.request.CurrencyRequest;


public class BasicCurrencyInfoGeneratorTestSuits {


//	@Test
//	public void testGetCurrencyInfo() {
//		
//		CurrencyDataProvider currencyDataProvider = mock(CurrencyDataProvider.class);
//		CurrencyRequestValidator currencyRequestValidator = mock(CurrencyRequestValidator.class);
//		CurrencyInfoProvider currencyInfoGenerator = new BasicCurrencyInfoGenerator(currencyDataProvider, currencyRequestValidator);
//		
//		CurrencyRequest request = new CurrencyRequest.CurrencyRequestBuilder()
//				.currencyCode("USD")
//				.setTable("A")
//				.build();
//		
//		CurrencyInfo currencyInfo = new CurrencyInfo.CurrencyInfoBuilder()
//				.setCheckingDate("2020-01-20")
//				.setCurrencyCode("USD")
//				.setCurrencyName("Dolar ameryka�ski")
//				.setCurrencyRate(BigDecimal.valueOf(3.5621))
//				.build();
//		
//		when(currencyDataProvider.getCurrencyInfo(request)).thenReturn(currencyInfo);
//		doNothing().when(currencyRequestValidator).validate(request);;
//		
//			
//		//when
//		CurrencyInfo result = currencyInfoGenerator.getCurrencyInfo(request);
//		
//		Assert.assertEquals(currencyInfo.getCurrencyCode(), result.getCurrencyCode());
//		Assert.assertEquals(currencyInfo.getCurrencyName(), result.getCurrencyName());
//		Assert.assertEquals(currencyInfo.getCheckingDate(), result.getCheckingDate());
//		Assert.assertEquals(currencyInfo.getCurrencyRate(), result.getCurrencyRate());				
//	}
	
}
