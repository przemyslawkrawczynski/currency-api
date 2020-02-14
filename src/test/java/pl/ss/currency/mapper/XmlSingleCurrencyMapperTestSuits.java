package pl.ss.currency.mapper;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import pl.ss.currency.domain.CurrencyInfo;
import pl.ss.currency.dtos.response.ExchangeRatesSeries;
import pl.ss.currency.dtos.response.RateDto;
import pl.ss.currency.dtos.response.Rates;
import pl.ss.currency.exception.NbpApiConnectionException;


//public class XmlSingleCurrencyMapperTestSuits {
//	
//	private MapperProvider<ExchangeRatesSeries> mapper = new XmlSingleCurrencyMapper();
//	
//	@Test
//	public void testMapToCurrencyInfo() {
//		ExchangeRatesSeries exchangeRatesSeries = generateEchExchangeRatesSeries();
//		CurrencyInfo result = mapper.mapToCurrencyInfo(exchangeRatesSeries);
//		
//		Assert.assertEquals("CHF", result.getCurrencyCode());
//		Assert.assertTrue(new BigDecimal(4.0020).equals(result.getCurrencyRate()));
//		Assert.assertEquals(LocalDate.now(), result.getCheckingDate());
//		
//		
//	}
//	
//	@Test
//	public void testMapFromInputStreamToExchangeRateSeries() throws FileNotFoundException {
//		
//		File xmlFile = new File("src/test/resources/TestedInputXML.xml");
//		InputStream responseStream = new FileInputStream(xmlFile);		
//		ExchangeRatesSeries exResult = mapper.mapFromInputStream(responseStream);
//		
//		Assert.assertEquals(exResult.getCode(), "CHF");		
//		
//		
//	}
//	
//	@Test
//	public void testThrowNbpApiExceptionWhenWrongInputStream() {
//		String fakeData = "FakeData";
//		InputStream fakeInputStream = new ByteArrayInputStream(fakeData.getBytes());
//		
//		NbpApiConnectionException ex = assertThrows(NbpApiConnectionException.class,
//				() -> mapper.mapFromInputStream(fakeInputStream));
//		
//		Assert.assertEquals("Mapper problem - Unmarshaller - can't parse response to ExchangeRateSeries.class", ex.getMessage());
//	}
//	
//	private ExchangeRatesSeries generateEchExchangeRatesSeries() {
//		
//		ExchangeRatesSeries exchangeRatesSeries = new ExchangeRatesSeries();
//		Rates rates = new Rates();
//		exchangeRatesSeries.setRates(rates);
//		RateDto rateDto = new RateDto();
//		rateDto.setEffectiveDate(LocalDate.now().toString());
//		System.out.println(rateDto.getEffectiveDate());
//		rateDto.setMid(new BigDecimal(4.0020));
//		rates.setRate(new ArrayList<RateDto>());
//		rates.getRate().add(rateDto);
//		exchangeRatesSeries.setCode("CHF");
//		exchangeRatesSeries.setTable("A");
//		
//		return exchangeRatesSeries;
//	}
	
//}
