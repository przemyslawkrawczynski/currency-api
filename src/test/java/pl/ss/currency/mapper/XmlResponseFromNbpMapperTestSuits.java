package pl.ss.currency.mapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import pl.ss.currency.domain.CurrencyInfo;
import pl.ss.currency.dtos.response.ExchangeRatesSeries;

public class XmlResponseFromNbpMapperTestSuits {
	
	private XmlResponseFromNbpMapper mapper = new XmlResponseFromNbpMapper();
	
	@Test
	public void shouldMapToExchangeRatesSeriesFromXMLStringResponse() throws IOException {
		
		//Given
		File xmlTestedFile = new File("src/test/resources/XMLStringFile.xml");
		InputStream responseStream = new FileInputStream(xmlTestedFile);
		String xmlStringResponse = IOUtils.toString(responseStream, StandardCharsets.UTF_8);
		
		String currencyCodeFromXmlFile = "USD";
		BigDecimal midPriceFromXmlFile = BigDecimal.valueOf(3.9104);
		String dateFromXmlFile = "2020-02-13";
		
		//When
		ExchangeRatesSeries resultOfMapping = mapper.mapFromXMLString(xmlStringResponse);
		String currencyCodeFromResult = resultOfMapping.getCode();
		String dateFromResult = resultOfMapping.getRates().getRate().get(0).getEffectiveDate();
		BigDecimal midPriceFromResult = resultOfMapping.getRates().getRate().get(0).getMid();
		
		//Then
		Assert.assertEquals(currencyCodeFromXmlFile, currencyCodeFromResult);
		Assert.assertEquals(midPriceFromXmlFile, midPriceFromResult);
		Assert.assertEquals(dateFromXmlFile, dateFromResult);		
 
	}
	
	@Test
	public void shouldMapToCurrencyInfoFromXMLNbpDataProviderResponse() throws IOException {
		
		//Given
		File xmlTestedFile = new File("src/test/resources/XMLStringFile.xml");
		InputStream responseStream = new FileInputStream(xmlTestedFile);
		String xmlStringResponse = IOUtils.toString(responseStream, StandardCharsets.UTF_8);
		
		String currencyCodeFromXmlFile = "USD";
		BigDecimal midPriceFromXmlFile = BigDecimal.valueOf(3.9104);
		LocalDate dateFromXmlFile = LocalDate.of(2020,02,13);
		
		//When
		CurrencyInfo mappedCurrencyInfo = mapper.mapToCurrencyInfo(xmlStringResponse);
		String currencyCodeFromMappedCurrencyInfo = mappedCurrencyInfo.getCurrencyCode();
		LocalDate dateFromMappedCurrencyInfo = mappedCurrencyInfo.getCheckingDate();
		BigDecimal rateFromMappedCurrencyInfo = mappedCurrencyInfo.getCurrencyRate();
		
		//Then
		Assert.assertEquals(currencyCodeFromXmlFile, currencyCodeFromMappedCurrencyInfo);
		Assert.assertEquals(dateFromXmlFile, dateFromMappedCurrencyInfo);		
		Assert.assertEquals(midPriceFromXmlFile, rateFromMappedCurrencyInfo);
	}
	

}

