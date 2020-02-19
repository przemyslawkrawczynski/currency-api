package pl.ss.currency.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import pl.ss.currency.dtos.response.raport.CurrencyRateValueDto;

@Service
public class CurrencyDtoMapper {
	
	public CurrencyRateValueDto mapToCurrencyRateValue(Object[] object) {		
		return new CurrencyRateValueDto((String) object[1], new BigDecimal((String) object[2]));
	}
	
	public List<CurrencyRateValueDto> mapToCurrencyRateDtoList(List<Object[]> list) {
		return list.stream()
				.map(o -> mapToCurrencyRateValue(o))
				.collect(Collectors.toList());
	}
}
