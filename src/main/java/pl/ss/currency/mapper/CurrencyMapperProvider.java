package pl.ss.currency.mapper;


import java.util.List;

import pl.ss.currency.domain.Currency;
import pl.ss.currency.dtos.response.CurrencyInfo;
import pl.ss.currency.dtos.response.CurrencyRateDto;


public interface CurrencyMapperProvider {
	
	CurrencyInfo mapToCurrencyInfo(String currencyDataFromProvider);
	CurrencyInfo mapToCurrencyInfo(CurrencyRateDto currencyDataFromDatabase);
	Currency mapToCurrencyWithRateList(String dataProviderResponse);
	Currency mapToCurrencyWithSingleDateRate(String dataProviderResponse);
}
