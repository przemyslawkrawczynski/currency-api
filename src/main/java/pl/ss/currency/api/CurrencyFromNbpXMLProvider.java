package pl.ss.currency.api;

import org.springframework.stereotype.Service;
import pl.ss.currency.dataprovider.DataProvider;
import pl.ss.currency.dtos.request.CurrencyRequest;
import pl.ss.currency.dtos.response.CurrencyInfo;
import pl.ss.currency.validator.RequestValidator;
import pl.ss.currency.mapper.CurrencyMapperProvider;

@Service
public class CurrencyFromNbpXMLProvider implements CurrencyProvider {

    private DataProvider dataProvider;
    private CurrencyMapperProvider currencyMapperProvider;
    private RequestValidator requestValidator;

    public CurrencyFromNbpXMLProvider(DataProvider dataProvider, CurrencyMapperProvider currencyMapperProvider, RequestValidator requestValidator) {
        this.dataProvider = dataProvider;
        this.currencyMapperProvider = currencyMapperProvider;
        this.requestValidator = requestValidator;
    }


    @Override
    public CurrencyInfo getCurrencyInfo(CurrencyRequest currencyRequest) {
        requestValidator.validate(currencyRequest);
        return currencyMapperProvider.mapToCurrencyInfo(getResponse(currencyRequest));
    }



    public String getResponse(CurrencyRequest currencyRequest) {

        String correctResponse;
        boolean foundCorrectDataFromRequest;

        do {
            correctResponse = dataProvider.getCurrencyDataByRequest(currencyRequest);
            foundCorrectDataFromRequest = validateResponse(correctResponse);

            if (!foundCorrectDataFromRequest) {
                currencyRequest = prepareNewRequest(currencyRequest);
            }

        } while (!foundCorrectDataFromRequest);

        return correctResponse;
    }

    private boolean validateResponse(String response) {
        return response != null;
    }


    private CurrencyRequest prepareNewRequest(CurrencyRequest currencyRequest) {
        return new CurrencyRequest.CurrencyRequestBuilder()
                .currencyCode(currencyRequest.getCurrencyCode())
                .setDate(currencyRequest.getOnDate().minusDays(1))
                .build();
    }
}
