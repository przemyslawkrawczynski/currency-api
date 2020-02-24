package pl.ss.currency.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import pl.ss.currency.domain.Country;
import pl.ss.currency.repository.CountryRepository;
import pl.ss.currency.repository.CountryWithMoreThanOneCurrency;

@Service
public class CountryService {

	private final CountryRepository countryRepository;
	
	public CountryService(CountryRepository countryRepository) {
		super();
		this.countryRepository = countryRepository;
	}

	public List<CountryWithMoreThanOneCurrency> getMoreThanOneCurrencyCountry() {
		return countryRepository.getCountryWhereCurrencysMoreThanOne();
		
	}

	public Map<String, Integer> getCountryWhereMoreThanOneCurrency() {
		
		List<Country> countries = countryRepository.findAll();
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		
		countries.stream()
			.filter(c -> c.getCurrencySet().size() > 1)
			.forEach(c -> resultMap.put(c.getCountryName(), c.getCurrencySet().size()));
		
		return resultMap;	
		
	}

}
