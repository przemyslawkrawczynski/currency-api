package pl.ss.currency.api;

import pl.ss.currency.dataprovider.DataProvider;
import pl.ss.currency.domain.CurrencyInfo;
import pl.ss.currency.dtos.request.CurrencyRequest;
import pl.ss.currency.mapper.MapperProvider;
import pl.ss.currency.validator.DataProviderResponseResolver;
import pl.ss.currency.validator.RequestValidator;

public class CurrencyFromDatabaseProvider implements CurrencyProvider {


    private DataProvider dataProvider;
    private MapperProvider mapperProvider;
    private RequestValidator requestValidator;
    private DataProviderResponseResolver responseResolver;

    public CurrencyFromDatabaseProvider(DataProvider dataProvider, MapperProvider mapperProvider, RequestValidator requestValidator, DataProviderResponseResolver responseResolver) {
        this.dataProvider = dataProvider;
        this.mapperProvider = mapperProvider;
        this.requestValidator = requestValidator;
        this.responseResolver = responseResolver;
    }


    @Override
    public CurrencyInfo getCurrencyInfo(CurrencyRequest currencyRequest) {
        requestValidator.validate(currencyRequest);
        return mapperProvider.mapToCurrencyInfo(getCorrectCurrencyData(currencyRequest));
    }

    private String getCorrectCurrencyData(CurrencyRequest currencyRequest) {
        return responseResolver.getResponse(currencyRequest, dataProvider);
    }



}