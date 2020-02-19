package pl.ss.currency.mapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import pl.ss.currency.domain.CurrencyRate;
import pl.ss.currency.dtos.response.raport.CurrencyRateValueDto;
import pl.ss.currency.repository.CurrencyRateRepository;

@Service
public class CurrencyDtoMapper {
	
	public CurrencyRateValueDto mapToCurrencyRateValue(Object[] resposne) {		
		
		BigDecimal value = (BigDecimal) resposne[1];
		
		return new CurrencyRateValueDto((String) resposne[0], value);
	}
	
	public List<CurrencyRateValueDto> mapToCurrencyRateDtoList(List<Object[]> list) {
		return list.stream()
				.map(o -> mapToCurrencyRateValue(o))
				.collect(Collectors.toList());
	}
	
	public CurrencyRateValueDto mapFromMinOrMaxValueResult(Object[] resposne, String currencyCode) {
		
		LocalDate date = (LocalDate) resposne[0];
		BigDecimal value = (BigDecimal) resposne[1];
		
		return new CurrencyRateValueDto(currencyCode, date, value);
		
	}
	public List<CurrencyRateValueDto> mapToCurrencyRateValueDtosFromCurrancyRates(List<CurrencyRate> list) {
		
		return list.stream()
				.map(r -> new CurrencyRateValueDto(r.getCurrency().getCurrencyCode(), r.getRateDate(), r.getRateValue()))
				.collect(Collectors.toList());
						
	}
}
