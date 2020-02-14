package pl.ss.currency.mapper;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;

import pl.ss.currency.domain.CurrencyInfo;
import pl.ss.currency.dtos.response.ExchangeRatesSeries;
import pl.ss.currency.exception.NbpApiConnectionException;

public class XmlResponseFromNbpMapper implements MapperProvider {

	public CurrencyInfo mapToCurrencyInfo(String XMLStringResponse) {

		ExchangeRatesSeries exchangeRatesSeries = mapFromXMLString(XMLStringResponse);

		return new CurrencyInfo.CurrencyInfoBuilder()
						.setCurrencyName(exchangeRatesSeries.getCurrency())
						.setCheckingDate(exchangeRatesSeries.getRates().getRate().get(0).getEffectiveDate())
						.setCurrencyCode(exchangeRatesSeries.getCode())
						.setCurrencyRate(exchangeRatesSeries.getRates().getRate().get(0).getMid())
						.build();
	}

	public ExchangeRatesSeries mapFromXMLString(String XMLStringResponse) {
		
		ExchangeRatesSeries exchangeRatesSeries;
		
		try {
		
		JAXBContext jc = JAXBContext.newInstance(ExchangeRatesSeries.class);
		Unmarshaller jaxUnmarshaller = jc.createUnmarshaller();
		exchangeRatesSeries = (ExchangeRatesSeries) jaxUnmarshaller.unmarshal(new StringReader(XMLStringResponse));
		
		} catch (UnmarshalException ex) {
			throw new NbpApiConnectionException("Unmarshaller - can't parse XML response from Nbp API");
		} catch (JAXBException ex) {
			throw new NbpApiConnectionException("Unexpected errors occur while unmarshalling");
		}
		
		return exchangeRatesSeries;
		
	}
	

}
