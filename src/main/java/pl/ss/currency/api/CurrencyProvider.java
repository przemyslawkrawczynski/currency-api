package pl.ss.currency.api;

import pl.ss.currency.dtos.request.CurrencyRequest;
import pl.ss.currency.dtos.response.CurrencyInfo;

public interface CurrencyProvider {

    CurrencyInfo getCurrencyInfo(CurrencyRequest currencyRequest);

}
