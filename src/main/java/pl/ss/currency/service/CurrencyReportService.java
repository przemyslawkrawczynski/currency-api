package pl.ss.currency.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.ss.currency.domain.CurrencyRate;
import pl.ss.currency.dtos.response.raport.CurrencyRateValueDto;
import pl.ss.currency.exception.DataValueException;
import pl.ss.currency.mapper.CurrencyDtoMapper;
import pl.ss.currency.repository.CurrencyRateRepository;

@Service
public class CurrencyReportService {

	private final CurrencyRateRepository currencyRateRepository;
	private final CurrencyDtoMapper currencyDtoMapper;

	public CurrencyReportService(CurrencyRateRepository currencyRateRepository, CurrencyDtoMapper currencyDtoMapper) {
		super();
		this.currencyRateRepository = currencyRateRepository;
		this.currencyDtoMapper = currencyDtoMapper;
	}

	// 1.
	public CurrencyRateValueDto getCurrencyWhereMaxDiffrenceRateOfTime(LocalDate dateFrom, LocalDate dateTo) {
		List<Object[]> maxDifferenceList = currencyRateRepository.getDifferences(dateFrom, dateTo);
		if (maxDifferenceList.size() != 0) {
			return currencyDtoMapper.mapToCurrencyRateDtoList(maxDifferenceList).get(0);
		} else {
			throw new DataValueException("Don't have data on given dates");
		}

	}

	// 2.

	public CurrencyRateValueDto getMinValueByCodeAndRange(String currencyCode, LocalDate dateFrom, LocalDate dateTo) {

		List<Object[]> resultlist = currencyRateRepository.getMinValueByCodeAndDateRange(currencyCode, dateFrom, dateTo);

		if (resultlist.size() > 0) {
			Object[] minValueObjects = resultlist.get(0);
			return currencyDtoMapper.mapFromMinOrMaxValueResult(minValueObjects, currencyCode);
		} else {
			throw new DataValueException(
					"Couldn`t find data for currency: " + currencyCode + " and range " + dateFrom + " " + dateTo);
		}
	}
	
	//3
	public CurrencyRateValueDto getMaxValueByCodeAndRange(String currencyCode, LocalDate dateFrom, LocalDate dateTo) {

		List<Object[]> resultlist = currencyRateRepository.getMaxValueByCodeAndDateRange(currencyCode, dateFrom, dateTo);

		if (resultlist.size() > 0) {
			Object[] minValueObjects = resultlist.get(0);
			return currencyDtoMapper.mapFromMinOrMaxValueResult(minValueObjects, currencyCode);
		} else {
			throw new DataValueException(
					"Couldn`t find data for currency: " + currencyCode + " and range " + dateFrom + " " + dateTo);
		}
	}

	//4
	public List<CurrencyRateValueDto> get5BestRates(String currencyCode) {
		List<CurrencyRate> results = currencyRateRepository.get5BestRatesByCode(currencyCode);
		if (results.size() > 0) {
			return currencyDtoMapper.mapToCurrencyRateValueDtosFromCurrancyRates(results);
		} else {
			throw new DataValueException(
					"Couldn`t find data for currency: " + currencyCode);
		}
	}


	//5
	public List<CurrencyRateValueDto> get5LowestRates(String currencyCode) {
		List<CurrencyRate> results = currencyRateRepository.get5LowestRatesByCode(currencyCode);
		if (results.size() > 0) {
			return currencyDtoMapper.mapToCurrencyRateValueDtosFromCurrancyRates(results);
		} else {
			throw new DataValueException(
					"Couldn`t find data for currency: " + currencyCode);
		}
	}

}
