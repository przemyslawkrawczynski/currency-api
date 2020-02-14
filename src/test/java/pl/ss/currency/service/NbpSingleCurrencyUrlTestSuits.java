//package pl.ss.nbp.service;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.time.LocalDate;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import pl.ss.nbp.dtos.request.CurrencyRequest;
//
//public class NbpSingleCurrencyUrlTestSuits {
//	
//	@Test
//	public void testGetUrlFirstTime() throws MalformedURLException {
//		
//		CurrencyRequest request = requestGenerator();
//		
//		UrlProvider urlProvider = new NbpSingleCurrencyUrlIterateDown();
//		urlProvider.setRequest(request);
//		
//		URL expectedUrl = new URL("http://api.nbp.pl/api/exchangerates/rates/A/USD/" + LocalDate.now() + "?format=xml");
//		URL resultUrl = urlProvider.getUrl();
//		
//		Assert.assertTrue(expectedUrl.equals(resultUrl));
//		
//		
//	}
//	
//	@Test
//	public void testGetUrlAfterNextCall() throws MalformedURLException {
//		
//		CurrencyRequest request = requestGenerator();
//		
//		UrlProvider urlProvider = new NbpSingleCurrencyUrlIterateDown();
//		urlProvider.setRequest(request);
//		
//		URL expectedUrl = new URL("http://api.nbp.pl/api/exchangerates/rates/A/USD/" + LocalDate.now().minusDays(1) + "?format=xml");
//		URL resultUrl = urlProvider.getUrl();
//		resultUrl = urlProvider.getUrl();
//		
//		Assert.assertTrue(expectedUrl.equals(resultUrl));
//		
//		
//	}
//	
//	@Test
//	public void testSetRequest() {
//		
//		CurrencyRequest request = requestGenerator();		
//		NbpSingleCurrencyUrlIterateDown urlProvider = new NbpSingleCurrencyUrlIterateDown();
//		
//		CurrencyRequest beforeSet = urlProvider.getRequest();
//		urlProvider.setRequest(request);
//		
//		CurrencyRequest afterSet = urlProvider.getRequest();
//		
//		Assert.assertNull(beforeSet);
//		Assert.assertNotNull(afterSet);		
//	}
//	
//	private CurrencyRequest requestGenerator() {
//		return new CurrencyRequest.CurrencyRequestBuilder()
//				.currencyCode("USD")
//				.setTable("A")
//				.setDate(LocalDate.now())
//				.build();
//	}
//}
//
