package pl.ss.currency.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.ss.currency.domain.CurrencyRate;
import pl.ss.currency.dtos.response.raport.CurrencyRateValueDto;
import pl.ss.currency.exception.DataValueException;
import pl.ss.currency.mapper.CurrencyDtoMapper;
import pl.ss.currency.repository.CurrencyDifferenceValue;
import pl.ss.currency.repository.CurrencyRateRaportRepository;
import pl.ss.currency.repository.CurrencyRateRepository;
import pl.ss.currency.repository.RateValueDto;

@Service
public class CurrencyReportService {

	private final CurrencyRateRepository currencyRateRepository;
	private final CurrencyDtoMapper currencyDtoMapper;
	private final CurrencyRateRaportRepository currencyRaportRepository;

	public CurrencyReportService(CurrencyRateRepository currencyRateRepository, CurrencyDtoMapper currencyDtoMapper,
			CurrencyRateRaportRepository currencyRaportRepository) {
		super();
		this.currencyRateRepository = currencyRateRepository;
		this.currencyDtoMapper = currencyDtoMapper;
		this.currencyRaportRepository = currencyRaportRepository;
	}

	// 1.
	public CurrencyDifferenceValue getCurrencyWhereMaxDiffrenceRateOfTime(LocalDate dateFrom, LocalDate dateTo) {
		
		List<CurrencyDifferenceValue> maxDifferenceList = currencyRateRepository.getDifference(dateFrom, dateTo);
		
		if (maxDifferenceList.size() != 0 ) {
			return maxDifferenceList.get(0);
		} else {
			throw new DataValueException("Don't have data on given dates");
		}

		
		
//		List<Object[]> maxDifferenceList = currencyRateRepository.getDifferences(dateFrom, dateTo);
//		if (maxDifferenceList.size() != 0) {
//			return currencyDtoMapper.mapToCurrencyRateDtoList(maxDifferenceList).get(0);
//		} else {
//			throw new DataValueException("Don't have data on given dates");
//		}

	}

	// 2.
	public CurrencyRateValueDto getMinValueByCodeAndRange(String currencyCode, LocalDate dateFrom, LocalDate dateTo) {

		List<RateValueDto> resultlist = currencyRateRepository.getMinValueByCodeAndDateRange(currencyCode, dateFrom, dateTo);

		if (resultlist.size() > 0) {
			return currencyDtoMapper.mapFromMinOrMaxValueResult(resultlist.get(0), currencyCode);
		} else {
			throw new DataValueException(
					"Couldn`t find data for currency: " + currencyCode + " and range " + dateFrom + " " + dateTo);
		}
	}
	
	//3
	public CurrencyRateValueDto getMaxValueByCodeAndRange(String currencyCode, LocalDate dateFrom, LocalDate dateTo) {

		List<RateValueDto> resultlist = currencyRateRepository.getMaxValueByCodeAndDateRange(currencyCode, dateFrom, dateTo);

		if (resultlist.size() > 0) {
			RateValueDto minValueObjects = resultlist.get(0);
			return currencyDtoMapper.mapFromMinOrMaxValueResult(minValueObjects, currencyCode);
		} else {
			throw new DataValueException(
					"Couldn`t find data for currency: " + currencyCode + " and range " + dateFrom + " " + dateTo);
		}
	}

	//4
	public List<CurrencyRateValueDto> get5BestRates(String currencyCode) {
		List<CurrencyRate> results = currencyRaportRepository.get5maxResults(currencyCode);
		if (results.size() > 0) {
			return currencyDtoMapper.mapToCurrencyRateValueDtosFromCurrancyRates(results);
		} else {
			throw new DataValueException(
					"Couldn`t find data for currency: " + currencyCode);
		}
	}


	//5
	public List<CurrencyRateValueDto> get5LowestRates(String currencyCode) {
		List<CurrencyRate> results = currencyRaportRepository.get5minResults(currencyCode);
		if (results.size() > 0) {
			return currencyDtoMapper.mapToCurrencyRateValueDtosFromCurrancyRates(results);
		} else {
			throw new DataValueException(
					"Couldn`t find data for currency: " + currencyCode);
		}
	}

}
