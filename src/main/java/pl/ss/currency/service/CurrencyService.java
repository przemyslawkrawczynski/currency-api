package pl.ss.currency.service;

import org.springframework.stereotype.Service;
import pl.ss.currency.dataprovider.DataProvider;
import pl.ss.currency.domain.Currency;
import pl.ss.currency.domain.CurrencyInfo;
import pl.ss.currency.dtos.request.CurrencyRequest;
import pl.ss.currency.mapper.CurrencyMapperProvider;
import pl.ss.currency.repository.CurrencyRepository;
import pl.ss.currency.validator.RequestValidator;

import java.time.LocalDate;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final DataProvider dataProvider;
    private final CurrencyMapperProvider currencyMapperProvider;
    private final RequestValidator requestValidator;

    public CurrencyService(CurrencyRepository currencyRepository, DataProvider dataProvider, CurrencyMapperProvider currencyMapperProvider, RequestValidator requestValidator) {
        this.currencyRepository = currencyRepository;
        this.dataProvider = dataProvider;
        this.currencyMapperProvider = currencyMapperProvider;
        this.requestValidator = requestValidator;
    }

    public CurrencyInfo getCurrencyByRequest(CurrencyRequest currencyRequest) {
        requestValidator.validate(currencyRequest);
        return currencyMapperProvider.mapToCurrencyDto(getCurrencyActualData(currencyRequest));
    }
    
    public Currency getCurrencyByLocalDateAndCode(LocalDate date, String currencyCode) {
        return currencyRepository.getByCodeAndDate(date, currencyCode);
    }

    public boolean isExistByRequest(LocalDate date, String currencyCode) {
        return currencyRepository.isExistByDateAndCurrencyCode(date, currencyCode) > 0;
    }

    public void updateCurrencyByDate(Currency currency) {
        currencyRepository.save(currency);
    }

    public Currency getCurrencyActualData(CurrencyRequest request) {

        Currency currency = null;
        boolean foundResponse = false;
        String currencyCode = request.getCurrencyCode();
        LocalDate checkingDate = request.getOnDate();


        while (!foundResponse) {
            if (isExistByRequest(request.getOnDate(), request.getCurrencyCode())) {
                currency = getCurrencyByLocalDateAndCode(checkingDate, currencyCode);
                foundResponse = true;
            } else {
                if (update(request)) {
                    currency = getCurrencyByLocalDateAndCode(checkingDate, currencyCode);
                    foundResponse = true;
                } else {
                    checkingDate = checkingDate.minusDays(1);
                    System.out.println("Zmiana daty na: " + checkingDate);
                }
            }
        }

        return currency;
    }

    private boolean update(CurrencyRequest request) {

        String providerResponse = dataProvider.getCurrencyDataByRequest(request);

        if (dataProvider.getCurrencyDataByRequest(request) != null) {
            updateCurrencyByDate(currencyMapperProvider.mapToCurrency(providerResponse));
            return true;
        }
        return false;
    }

}
