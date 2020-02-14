package pl.ss.currency.validator;

import pl.ss.currency.dataprovider.DataProvider;
import pl.ss.currency.dtos.request.CurrencyRequest;

public interface DataProviderResponseResolver {

    String getResponse(CurrencyRequest currencyRequest, DataProvider dataProvider);
}
