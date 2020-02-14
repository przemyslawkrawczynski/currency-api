package pl.ss.currency.mapper;

import pl.ss.currency.domain.CurrencyInfo;


public interface MapperProvider {
	
	CurrencyInfo mapToCurrencyInfo(String currencyData);
}
