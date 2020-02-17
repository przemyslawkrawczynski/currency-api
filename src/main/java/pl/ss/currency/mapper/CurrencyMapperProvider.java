package pl.ss.currency.mapper;

import pl.ss.currency.domain.Currency;
import pl.ss.currency.domain.CurrencyInfo;


public interface CurrencyMapperProvider {
	
	CurrencyInfo mapToCurrencyInfo(String currencyDataFromProvider);
	Currency mapToCurrency(String dataProviderResponse);
	CurrencyInfo mapToCurrencyDto(Currency currencyDataFromDatabase);
}
