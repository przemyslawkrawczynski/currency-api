package pl.ss.currency.mapper;

import pl.ss.currency.domain.Currency;
import pl.ss.currency.domain.CurrencyInfo;

import java.math.BigDecimal;


public class DatabaseResponseCurrencyMapper implements CurrencyMapperProvider {

    @Override
    public CurrencyInfo mapToCurrencyInfo(String currencyData) {
        String[] response = getCurrencyInfoFromResponse(currencyData);

        return new CurrencyInfo.CurrencyInfoBuilder()
                .setCurrencyCode(response[1])
                .setCurrencyName("")
                .setCheckingDate(response[2])
                .setCurrencyRate(BigDecimal.valueOf(Double.parseDouble(response[3])))
                .build();
    }

    public DatabaseResponseCurrencyMapper() {
        super();
    }

    @Override
    public Currency mapToCurrency(String dataProviderResponse) {
        return null;
    }

    @Override
    public CurrencyInfo mapToCurrencyDto(Currency currencyDataFromDatabase) {
        return null;
    }

    public String[] getCurrencyInfoFromResponse(String currencyData) {
        return currencyData.split(";");
    }
}
