//package pl.ss.nbp.service;
//
//import org.junit.Test;
//import pl.ss.nbp.dtos.response.ExchangeRatesSeries;
//import pl.ss.nbp.dtos.response.RateDto;
//import pl.ss.nbp.dtos.response.Rates;
//import pl.ss.nbp.service.dataprovider.CurrencyDataProvider;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.ArrayList;
//
//import static org.mockito.Mockito.mock;
//
//public class CurrencyFromApiProviderTestSuits {
//
//	private CurrencyDataProvider currencyDataProvider;
//
//	@Test
//	public void testGetCurrencyInfo() {
//		
////		XmlResponseProvider nbpConnectionServiceMock = mock(XmlResponseProvider.class);
////		RequestToCurrencyApiDto requestMock = mock(RequestToCurrencyApiDto.class);
////		currencyDataProvider.(nbpConnectionServiceMock);
////		ExchangeRatesSeries exchangeRatesSeries = generateEchExchangeRatesSeries();
////		when(nbpConnectionServiceMock.getCurrencyInformation(requestMock)).thenReturn(exchangeRatesSeries);
////
////		CurrencyInfo result = service.getCurrencyInfo(requestMock);
////
////		Assert.assertEquals("USD", result.getCurrencyCode());
////		Assert.assertEquals(LocalDate.now(), result.getCheckingDate());
////		Assert.assertEquals(result.getCurrencyCode(), "USD");
////		Assert.assertEquals(new BigDecimal(3.7895), result.getCurrencyRate());
//
//	}
//	
//	private ExchangeRatesSeries generateEchExchangeRatesSeries() {
//		ExchangeRatesSeries exchangeRatesSeries = new ExchangeRatesSeries();
//		Rates rates = new Rates();
//		exchangeRatesSeries.setRates(rates);
//		RateDto rateDto = new RateDto();
//		rateDto.setEffectiveDate(LocalDate.now().toString());
//		rateDto.setMid(new BigDecimal(3.7895));
//		rates.setRate(new ArrayList<RateDto>());
//		rates.getRate().add(rateDto);
//		exchangeRatesSeries.setCode("USD");
//		return exchangeRatesSeries;
//	}
//
//}
