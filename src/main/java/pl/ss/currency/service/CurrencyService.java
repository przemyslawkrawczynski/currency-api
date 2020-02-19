package pl.ss.currency.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import pl.ss.currency.dataprovider.DataProvider;
import pl.ss.currency.domain.Currency;
import pl.ss.currency.domain.CurrencyRate;
import pl.ss.currency.dtos.request.CurrencyRequest;
import pl.ss.currency.dtos.response.CurrencyInfo;
import pl.ss.currency.dtos.response.CurrencyRateDto;
import pl.ss.currency.exception.DataValueException;
import pl.ss.currency.mapper.CurrencyMapperProvider;
import pl.ss.currency.repository.CurrencyRateRepository;
import pl.ss.currency.repository.CurrencyRepository;
import pl.ss.currency.validator.RequestValidator;

@Service
public class CurrencyService {

	private final CurrencyRepository currencyRepository;
	private final CurrencyRateRepository currencRateRepository;
	private final DataProvider dataProvider;
	private final CurrencyMapperProvider currencyMapperProvider;
	private final RequestValidator requestValidator;

	public CurrencyService(CurrencyRepository currencyRepository, CurrencyRateRepository currencRateRepository,
			DataProvider dataProvider, CurrencyMapperProvider currencyMapperProvider,
			RequestValidator requestValidator) {
		this.currencyRepository = currencyRepository;
		this.currencRateRepository = currencRateRepository;
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
		BigDecimal currencyRate = new BigDecimal((String) response[3]);

		return new CurrencyRateDto(currencyId, code, currencyDate, currencyRate);

	}

	public boolean isExistByRequest(LocalDate date, String currencyCode) {
		return currencyRepository.isExistByDateAndCurrencyCode(date, currencyCode) > 0;
	}

	public Long ifExistGetCurrencyId(String currencyCode) {
		Long idCurrencyCode = currencyRepository.getIdByCodeName(currencyCode);
		return idCurrencyCode;
		
	}

	public void saveNew(Currency currency) {
		currencyRepository.save(currency);
	}

	public void updateRateByDateAndCode(LocalDate date, String currencyCode, BigDecimal newValue) throws DataValueException {

		Optional<CurrencyRate> opt = currencRateRepository.getRateByCurrencyCodeAndDate(date, currencyCode);
		if (opt.isPresent()) {
			CurrencyRate updatedRate = opt.get();
			updatedRate.setRateValue(newValue);
			currencRateRepository.save(updatedRate);
		} else {
			throw new DataValueException("Couldn`t find Rate for: " + currencyCode + " and " + date);
		}
	}
	
	public void deleteCurrencyRateByCodeAndDate(LocalDate date, String currencyCode) {
		Optional<CurrencyRate> opt = currencRateRepository.getRateByCurrencyCodeAndDate(date, currencyCode);
		if (opt.isPresent()) {
			CurrencyRate updatedRate = opt.get();
			currencRateRepository.delete(updatedRate);
		} else {
			throw new DataValueException("Couldn`t find Rate for: " + currencyCode + " and " + date);
		}
	}

	public CurrencyRateDto getCurrencyActualData(CurrencyRequest request) {

        CurrencyRateDto currency = null;
        boolean foundResponse = false;
        String currencyCode = request.getCurrencyCode();
        LocalDate checkingDate = request.getOnDate();
                
        while (!foundResponse) {
            if (isExistByRequest(request.getOnDate(), request.getCurrencyCode())) {
                currency = getCurrencyByLocalDateAndCode(checkingDate, currencyCode);
                foundResponse = true;
            } else {
                if (updateMissingCurrencyInfoInDatabase(request)) {
                    currency = getCurrencyByLocalDateAndCode(checkingDate, currencyCode);
                    foundResponse = true;
                } else {
                    checkingDate = checkingDate.minusDays(1);
                    request.setOnDate(checkingDate);
                    System.out.println("Zmiana daty na: " + checkingDate);
                }
            }
        }

        return currency;
    }

	private boolean updateMissingCurrencyInfoInDatabase(CurrencyRequest request) {

		String providerResponse = dataProvider.getCurrencyDataByRequest(request);		
		if (providerResponse == null) return false;
		
		Currency responseFromProvider = currencyMapperProvider.mapToCurrencyWithSingleDateRate(providerResponse);
		Long currencyId = ifExistGetCurrencyId(request.getCurrencyCode());

		if (currencyId != null) {
			Currency currency = currencyRepository.findById(currencyId).get();
			BigDecimal rateValue = responseFromProvider.getRateByDate(request.getOnDate()).get();
			currency.addNewRateByDate(new CurrencyRate(currency, request.getOnDate(), rateValue));
			currencyRepository.save(currency);
			return true;
		} else {
			saveNew(currencyMapperProvider.mapToCurrencyWithSingleDateRate(providerResponse));
			return true;
		}
	}

}
