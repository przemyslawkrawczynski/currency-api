package pl.ss.currency.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

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



	public CurrencyRateValueDto getCurrencyWhereMaxDiffrenceRateOfTime(LocalDate dateFrom, LocalDate dateTo) {
		System.out.println(dateFrom + "  " + dateTo);
		List<Object[]> maxDifferenceList = currencyRateRepository.getDifferences(dateFrom, dateTo);
		if (maxDifferenceList.size() != 0) {
			return currencyDtoMapper.mapToCurrencyRateDtoList(maxDifferenceList).get(0);
		} else {
			throw new DataValueException("Don't have data on given dates");
		}
		
	}
	

}
