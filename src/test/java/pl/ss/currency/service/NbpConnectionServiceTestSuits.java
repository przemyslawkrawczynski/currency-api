//package pl.ss.nbp.service;
//
//import org.junit.Assert;
//import org.junit.Test;
//import pl.ss.nbp.dtos.request.CurrencyRequest;
//import pl.ss.nbp.dtos.response.ExchangeRatesSeries;
//import pl.ss.nbp.exception.NbpApiConnectionException;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//public class NbpConnectionServiceTestSuits {
//
////	private final XmlResponseProvider service = new XmlResponseProvider();
////	@Test
////	public void testGetCurrencyInformation() {
////
////		RequestToCurrencyApiDto requestData = new CurrencyRequest.CurrencyRequestBuilder().currencyCode("USD")
////				.setTable("A").build();
////
////		ExchangeRatesSeries result = service.getCurrencyInformation(requestData);
////		String resultTableName = result.getTable();
////		String currencyCode = result.getCode();
////		String currencyName = result.getCurrency();
////
////		Assert.assertEquals("A", resultTableName);
////		Assert.assertEquals("USD", currencyCode);
////		Assert.assertEquals("dolar ameryka�ski", currencyName);
////
////	}
////
////	@Test
////	public void testCatchApiConnectionException() {
////		RequestToCurrencyApiDto requestData = new CurrencyRequest.CurrencyRequestBuilder().currencyCode("failData")
////				.setTable("failData").build();
////
////		NbpApiConnectionException ex = assertThrows(NbpApiConnectionException.class,
////				() -> service.getCurrencyInformation(requestData));
////
////		Assert.assertEquals("Error with connection to NBP api", ex.getMessage());
////	}
//
//}
