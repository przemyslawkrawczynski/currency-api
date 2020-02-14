package pl.ss.currency;

import java.time.LocalDate;

import pl.ss.currency.api.CurrencyFromDatabaseProvider;
import pl.ss.currency.api.CurrencyFromNbpXMLProvider;
import pl.ss.currency.api.CurrencyProvider;
import pl.ss.currency.dataprovider.DataProvider;
import pl.ss.currency.dataprovider.DatabaseDataProvider;
import pl.ss.currency.dataprovider.XmlFromNbpApiDataProvider;
import pl.ss.currency.domain.CurrencyInfo;
import pl.ss.currency.dtos.request.CurrencyRequest;
import pl.ss.currency.mapper.DatabaseResponseMapper;
import pl.ss.currency.mapper.MapperProvider;
import pl.ss.currency.mapper.XmlResponseFromNbpMapper;
import pl.ss.currency.validator.CurrencyRequestValidator;
import pl.ss.currency.validator.DataProviderResponseResolver;
import pl.ss.currency.validator.DataProviderResponseValidator;
import pl.ss.currency.validator.RequestValidator;

public class TestConnection {

    public static void main(String[] args) {

        //XML
    	
        MapperProvider xmlMapperProvider = new XmlResponseFromNbpMapper();
        DataProvider dataProvider = new XmlFromNbpApiDataProvider();
        RequestValidator requestValidator = new CurrencyRequestValidator();
        DataProviderResponseResolver dataResponseResolver = new DataProviderResponseValidator();

        CurrencyProvider currencyApi = new CurrencyFromNbpXMLProvider(dataProvider, xmlMapperProvider, requestValidator, dataResponseResolver);

        //Database
        MapperProvider databaseMapper = new DatabaseResponseMapper();
        DataProvider dataBaseProvider = new DatabaseDataProvider();

        CurrencyProvider currencyDatabase = new CurrencyFromDatabaseProvider(dataBaseProvider, databaseMapper, requestValidator, dataResponseResolver);

        //Single currency
        CurrencyRequest currencyRequest = new CurrencyRequest.CurrencyRequestBuilder()
                .setTable("A")
                .currencyCode("USD")
                .setDate(LocalDate.of(2020, 02, 9))
                .build();


        CurrencyInfo currencyInfoFromNbp = currencyApi.getCurrencyInfo(currencyRequest);
        CurrencyInfo currencyInfoFromDatabase = currencyDatabase.getCurrencyInfo(currencyRequest);

        System.out.println("[DatabaseDataProvider]" + currencyInfoFromDatabase.toString());
        System.out.println("[XMLDataProvider]" + currencyInfoFromNbp.toString());

    }

}
