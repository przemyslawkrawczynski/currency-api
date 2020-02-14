//package pl.ss.nbp.service;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import pl.ss.nbp.domain.CurrencyInfo;
//import pl.ss.nbp.dtos.request.CurrencyRequest;
//import pl.ss.nbp.mapper.MapperProvider;
//
//public class CurrencyFromApiServiceTestSuits {
//	
//		
//
//	@Test
//	public void testGetCurrencyInfo() {	
//		
//		XmlResponseProvider nbpConnectionServiceMock = mock(XmlResponseProvider.class);		
//		CurrencyRequest requestMock = mock(CurrencyRequest.class);
//		MapperProvider mapperMock = mock(MapperProvider.class);
//		UrlProvider urlProvider = mock(UrlProvider.class);
//		CurrencyDataProvider apiProvider = new CurrencyFromApiService(nbpConnectionServiceMock, mapperMock, urlProvider);
//		
//		CurrencyInfo currencyInfo = new CurrencyInfo.CurrencyInfoBuilder()
//				.setCurrencyCode("USD")
//				.setCurrencyName("Dolar")
//				.setCurrencyRate(BigDecimal.valueOf(3.7895))
//				.setCheckingDate(LocalDate.now().toString())
//				.build();
//		
//		when(nbpConnectionServiceMock.getDataFromApi(requestMock, mapperMock, urlProvider)).thenReturn(currencyInfo);
//		
//		CurrencyInfo result = apiProvider.getCurrencyInfo(requestMock);
//		
//		Assert.assertEquals("USD", result.getCurrencyCode());
//		Assert.assertEquals(LocalDate.now(), result.getCheckingDate());
//		Assert.assertEquals(result.getCurrencyCode(), "USD");
//		Assert.assertEquals(BigDecimal.valueOf(3.7895), result.getCurrencyRate());
//	
//	}
//	
//
//}
