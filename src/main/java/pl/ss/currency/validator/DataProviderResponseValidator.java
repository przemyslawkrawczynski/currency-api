package pl.ss.currency.validator;

import pl.ss.currency.dataprovider.DataProvider;
import pl.ss.currency.dtos.request.CurrencyRequest;

public class DataProviderResponseValidator implements DataProviderResponseResolver {

    @Override
    public String getResponse(CurrencyRequest currencyRequest, DataProvider dataProvider) {

        String correctResponse;
        boolean foundCorrectDataFromRequest;

        do {
            correctResponse = dataProvider.getCurrencyDataByRequest(currencyRequest);
            foundCorrectDataFromRequest = validateResponse(correctResponse);

            if (foundCorrectDataFromRequest == false) {
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
                .setTable(currencyRequest.getTableName())
                .setDate(currencyRequest.getOnDate().minusDays(1))
                .build();
    }


}
