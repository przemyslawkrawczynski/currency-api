package pl.ss.currency.api;

import pl.ss.currency.domain.CurrencyInfo;
import pl.ss.currency.dtos.request.CurrencyRequest;

public interface CurrencyProvider {

    CurrencyInfo getCurrencyInfo(CurrencyRequest currencyRequest);

}
