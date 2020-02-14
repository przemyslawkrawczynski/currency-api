package pl.ss.currency.mapper;

import pl.ss.currency.domain.CurrencyInfo;

import java.math.BigDecimal;

public class DatabaseResponseMapper implements MapperProvider {

    @Override
    public CurrencyInfo mapToCurrencyInfo(String currencyData) {
        String[] response = getCurrencyInfoFromResponse(currencyData);

        return new CurrencyInfo.CurrencyInfoBuilder()
                .setCurrencyCode(response[1])
                .setCurrencyName("")
                .setCheckingDate(response[0])
                .setCurrencyRate(BigDecimal.valueOf(Double.parseDouble(response[3])))
                .build();
    }

    public String[] getCurrencyInfoFromResponse(String currencyData) {
        return currencyData.split(";");
    }
}
