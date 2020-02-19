package pl.ss.currency.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import pl.ss.currency.dataprovider.DataProvider;
import pl.ss.currency.domain.Currency;
import pl.ss.currency.domain.CurrencyRate;
import pl.ss.currency.exception.NbpApiConnectionException;
import pl.ss.currency.mapper.CurrencyMapperProvider;
import pl.ss.currency.repository.CurrencyRateRepository;
import pl.ss.currency.repository.CurrencyRepository;

@Service
public class CurrencyUpdateInformationService {
	
	private final CurrencyRepository currencyRepository;
	private final CurrencyRateRepository currencyRateRepository;
	private final DataProvider dataProvider;
	private final CurrencyMapperProvider currencyMapperProvider;
	
	public CurrencyUpdateInformationService(CurrencyRepository currencyRepository,
			CurrencyRateRepository currencyRateRepository, DataProvider dataProvider,
			CurrencyMapperProvider currencyMapperProvider) {
		super();
		this.currencyRepository = currencyRepository;
		this.currencyRateRepository = currencyRateRepository;
		this.dataProvider = dataProvider;
		this.currencyMapperProvider = currencyMapperProvider;
	}

	public Currency getInfoFromNbp(String currencyCode, LocalDate dateFrom, LocalDate dateTo) {
		
		String responseFromDataProvider = dataProvider.getCurrencyDataByRangeDateAndCurrencyCode(currencyCode, dateFrom, dateTo);		
		Currency dataFromNbp = currencyMapperProvider.mapToCurrencyWithRateList(responseFromDataProvider);
		
		if (dataFromNbp == null) throw new NbpApiConnectionException("Can`t find data from given data: " + currencyCode + " " + dateFrom.toString() + " " + dateTo.toString());
		return dataFromNbp;
	}
	
	public Currency updateDataInDatabase(String currencyCode, LocalDate dateFrom, LocalDate dateTo) {
		Long currencyId = currencyRepository.getIdByCodeName(currencyCode);
		Currency currencyFromDataProvider = getInfoFromNbp(currencyCode, dateFrom, dateTo);
		if (currencyId == null) {
			currencyRepository.save(currencyFromDataProvider);
			return currencyFromDataProvider;
		} else {
			Currency currencyToUpdate = currencyRepository.findById(currencyId).get();
			HashMap<LocalDate, CurrencyRate> currencyRatesFromDatabase = getHashMapFromCurrency(currencyToUpdate.getRates());
			
			
			for (CurrencyRate rate: currencyFromDataProvider.getRates()) {
				
				LocalDate updatedDate = rate.getRateDate();
				BigDecimal updatedValue = rate.getRateValue();
				
				if (currencyRatesFromDatabase.containsKey(updatedDate)) {
					currencyRatesFromDatabase.get(updatedDate).setRateValue(updatedValue);
				} else {
					currencyRatesFromDatabase.put(updatedDate, rate);
				}
			}
			
			Set<CurrencyRate> updatedList = currencyRatesFromDatabase.entrySet().stream()
					.map(rate -> rate.getValue())
					.collect(Collectors.toSet());
			
			updatedList.forEach(rate -> rate.setCurrency(currencyToUpdate));	
			currencyToUpdate.setRates(updatedList);
			currencyRepository.save(currencyToUpdate);
			return currencyToUpdate;			
		}
	}
	
	private HashMap<LocalDate, CurrencyRate> getHashMapFromCurrency(Set<CurrencyRate> currencyRateSet) {
		
		HashMap<LocalDate, CurrencyRate> currencyRateMap = new HashMap<>();
		
		currencyRateSet.stream().forEach(rate -> currencyRateMap.put(rate.getRateDate(), rate));
		return currencyRateMap;
		
	}
	
}
