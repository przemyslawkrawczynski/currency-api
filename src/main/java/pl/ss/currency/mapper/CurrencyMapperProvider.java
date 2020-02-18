package pl.ss.currency.mapper;

import pl.ss.currency.domain.Currency;
import pl.ss.currency.domain.CurrencyInfo;
import pl.ss.currency.dtos.response.CurrencyRateDto;


public interface CurrencyMapperProvider {
	
	CurrencyInfo mapToCurrencyInfo(String currencyDataFromProvider);
	Currency mapToCurrency(String dataProviderResponse);
	CurrencyInfo mapToCurrencyInfo(CurrencyRateDto currencyDataFromDatabase);
}
