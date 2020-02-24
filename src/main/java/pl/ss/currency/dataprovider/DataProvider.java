package pl.ss.currency.dataprovider;

import java.time.LocalDate;

import pl.ss.currency.dtos.request.CurrencyRequest;

public interface DataProvider {
    String getCurrencyDataByRequest(CurrencyRequest request);
    String getCurrencyDataByRangeDateAndCurrencyCode(String currencyCode, LocalDate dateFrom, LocalDate dateTo);

}
