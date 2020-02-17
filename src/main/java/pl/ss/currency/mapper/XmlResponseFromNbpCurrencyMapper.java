package pl.ss.currency.mapper;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.ss.currency.domain.Currency;
import pl.ss.currency.domain.CurrencyInfo;
import pl.ss.currency.dtos.response.ExchangeRatesSeries;
import pl.ss.currency.dtos.response.RateDto;
import pl.ss.currency.exception.NbpApiConnectionException;

@Service
@Primary
public class XmlResponseFromNbpCurrencyMapper implements CurrencyMapperProvider {

	public CurrencyInfo mapToCurrencyInfo(String XMLStringResponse) {

		ExchangeRatesSeries exchangeRatesSeries = mapFromXMLString(XMLStringResponse);

		return new CurrencyInfo.CurrencyInfoBuilder()
						.setCurrencyName(exchangeRatesSeries.getCurrency())
						.setCheckingDate(exchangeRatesSeries.getRates().getRate().get(0).getEffectiveDate())
						.setCurrencyCode(exchangeRatesSeries.getCode())
						.setCurrencyRate(exchangeRatesSeries.getRates().getRate().get(0).getMid())
						.build();
	}

	public ExchangeRatesSeries mapFromXMLString(String XMLStringResponse) throws NbpApiConnectionException {
		
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

	@Override
	public Currency mapToCurrency(String XMLStringResponse) {
		ExchangeRatesSeries exchangeRatesSeries = mapFromXMLString(XMLStringResponse);
		RateDto rate = exchangeRatesSeries.getRates().getRate().get(0);

		return new Currency(exchangeRatesSeries.getCode(), rate.getEffectiveDate(), exchangeRatesSeries.getTable(), rate.getMid(), exchangeRatesSeries.getCurrency());
	}

	public CurrencyInfo mapToCurrencyDto(Currency currency) {
		return new CurrencyInfo.CurrencyInfoBuilder()
				.setCheckingDate(currency.getCurrencyRateDate().toString())
				.setCurrencyCode(currency.getCurrencyCode())
				.setCurrencyRate(currency.getCurrencyRate())
				.setCurrencyName(currency.getCurrencyDescription())
				.build();
	}

}
