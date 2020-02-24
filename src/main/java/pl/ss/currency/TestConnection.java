package pl.ss.currency;

import java.time.LocalDate;

import pl.ss.currency.api.CurrencyFromNbpXMLProvider;
import pl.ss.currency.api.CurrencyProvider;
import pl.ss.currency.dataprovider.DataProvider;
import pl.ss.currency.dataprovider.XmlFromNbpApiDataProvider;
import pl.ss.currency.dtos.request.CurrencyRequest;
import pl.ss.currency.dtos.response.CurrencyInfo;
import pl.ss.currency.mapper.CurrencyMapperProvider;
import pl.ss.currency.mapper.XmlResponseFromNbpCurrencyMapper;
import pl.ss.currency.validator.CurrencyRequestValidator;
import pl.ss.currency.validator.RequestValidator;

public class TestConnection {

    public static void main(String[] args) {

        //Nbp - XML    	
        CurrencyMapperProvider xmlCurrencyMapperProvider = new XmlResponseFromNbpCurrencyMapper();
        DataProvider dataProvider = new XmlFromNbpApiDataProvider();
        RequestValidator requestValidator = new CurrencyRequestValidator();

        CurrencyProvider currencyApi = new CurrencyFromNbpXMLProvider(dataProvider, xmlCurrencyMapperProvider, requestValidator);

        //Single currency
        CurrencyRequest currencyRequest = new CurrencyRequest.CurrencyRequestBuilder()
                .currencyCode("USD")
                .setDate(LocalDate.of(2020, 02, 9))
                .build();


        CurrencyInfo currencyInfoFromNbp = currencyApi.getCurrencyInfo(currencyRequest);

        System.out.println("[XMLDataProvider]" + currencyInfoFromNbp.toString());

    }

}
