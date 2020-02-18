package pl.ss.currency.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import pl.ss.currency.dataprovider.DataProvider;
import pl.ss.currency.domain.Currency;
import pl.ss.currency.domain.CurrencyInfo;
import pl.ss.currency.dtos.request.CurrencyRequest;
import pl.ss.currency.dtos.response.CurrencyRateDto;
import pl.ss.currency.mapper.CurrencyMapperProvider;
import pl.ss.currency.repository.CurrencyRepository;
import pl.ss.currency.validator.RequestValidator;

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
        return currencyMapperProvider.mapToCurrencyInfo(getCurrencyActualData(currencyRequest));
    }
    
    public CurrencyRateDto getCurrencyByLocalDateAndCode(LocalDate date, String currencyCode) {
    	
    	Object[] response = currencyRepository.getRateByCodeAndDate(date, currencyCode).get(0);
    	
    	Long currencyId = (Long) response[0];
    	String code = (String) response[1];
    	LocalDate currencyDate = (LocalDate) response[2];
    	BigDecimal currencyRate = (BigDecimal) response[3];
    	
    	
    	return new CurrencyRateDto(currencyId, code, currencyDate, currencyRate);
    	
    }

    public boolean isExistByRequest(LocalDate date, String currencyCode) {
        return currencyRepository.isExistByDateAndCurrencyCode(date, currencyCode) > 0;
    }

    public void saveNew(Currency currency) {
        currencyRepository.save(currency);
    }
    
    public void updateByDateAndCode(LocalDate date, String code) {
    	   	
    	if (isExistByRequest(date, code)) {
    		//Currency updatedCurrency = currencyRepository.getRateByCodeAndDate(date, code);
    	}
    }

    public CurrencyRateDto getCurrencyActualData(CurrencyRequest request) {

        CurrencyRateDto currency = null;
        boolean foundResponse = false;
        String currencyCode = request.getCurrencyCode();
        LocalDate checkingDate = request.getOnDate();
        
        System.out.println(isExistByRequest(checkingDate, currencyCode));
        System.out.println(getCurrencyActualData(request));

//        while (!foundResponse) {
//            if (isExistByRequest(request.getOnDate(), request.getCurrencyCode())) {
//                currency = getCurrencyByLocalDateAndCode(checkingDate, currencyCode);
//                foundResponse = true;
//            } else {
//                if (updateMissingCurrencyInfoInDatabase(request)) {
//                    currency = getCurrencyByLocalDateAndCode(checkingDate, currencyCode);
//                    foundResponse = true;
//                } else {
//                    checkingDate = checkingDate.minusDays(1);
//                    System.out.println("Zmiana daty na: " + checkingDate);
//                }
//            }
//        }

        return currency;
    }

    private boolean updateMissingCurrencyInfoInDatabase(CurrencyRequest request) {

        String providerResponse = dataProvider.getCurrencyDataByRequest(request);

        if (dataProvider.getCurrencyDataByRequest(request) != null) {
            saveNew(currencyMapperProvider.mapToCurrency(providerResponse));
            return true;
        }
        return false;
    }

}
