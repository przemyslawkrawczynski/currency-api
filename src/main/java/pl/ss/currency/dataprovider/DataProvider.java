package pl.ss.currency.dataprovider;

import pl.ss.currency.dtos.request.CurrencyRequest;

public interface DataProvider {
    String getCurrencyDataByRequest(CurrencyRequest request);
}
