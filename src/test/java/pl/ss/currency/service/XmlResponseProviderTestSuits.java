//package pl.ss.nbp.service;
//
//import java.net.MalformedURLException;
//import java.time.DayOfWeek;
//import java.time.LocalDate;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import pl.ss.nbp.domain.CurrencyInfo;
//import pl.ss.nbp.dtos.request.CurrencyRequest;
//import pl.ss.nbp.dtos.response.ExchangeRatesSeries;
//import pl.ss.nbp.mapper.MapperProvider;
//import pl.ss.nbp.mapper.XmlSingleCurrencyMapper;
//
//public class XmlResponseProviderTestSuits {
//
//	private final XmlResponseProvider xmlResponseProvider = new XmlResponseProvider();
//
//	@Test
//	public void testGetDataFromApiCallMapperNotWeekend() throws MalformedURLException {
//
//		CurrencyRequest request = requestGenerator(weekLocalDate());
//		MapperProvider<ExchangeRatesSeries> mapper = new XmlSingleCurrencyMapper();
//		UrlProvider urlProvider = new NbpSingleCurrencyUrlIterateDown();
//
//		CurrencyInfo currencyInfo = xmlResponseProvider.getDataFromApi(request, mapper, urlProvider);
//
//		Assert.assertEquals(weekLocalDate(), currencyInfo.getCheckingDate());
//		Assert.assertEquals("USD", currencyInfo.getCurrencyCode());
//
//	}
//
//	@Test
//	public void testGetDataFromApiCallMapperWeekend() throws MalformedURLException {
//		
//		LocalDate sunday = LocalDate.of(2020,02,9);
//		CurrencyRequest request = requestGenerator(sunday);
//		MapperProvider<ExchangeRatesSeries> mapper = new XmlSingleCurrencyMapper();
//		UrlProvider urlProvider = new NbpSingleCurrencyUrlIterateDown();
//
//		CurrencyInfo currencyInfo = xmlResponseProvider.getDataFromApi(request, mapper, urlProvider);
//
//		Assert.assertEquals(sunday.minusDays(2), currencyInfo.getCheckingDate());
//		Assert.assertEquals("USD", currencyInfo.getCurrencyCode());
//
//	}
//
//	private CurrencyRequest requestGenerator(LocalDate localDate) {
//		return new CurrencyRequest.CurrencyRequestBuilder().currencyCode("USD").setTable("A").setDate(localDate)
//				.build();
//	}
//
//	private LocalDate weekLocalDate() {
//		LocalDate date = LocalDate.now();
//		if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
//			date = date.minusDays(2);
//		}
//		return date;
//	}
//
//	private LocalDate getSundayDateInPast() {
//		return LocalDate.of(2020,02,9);
//	}
//}
