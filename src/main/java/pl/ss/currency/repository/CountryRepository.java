package pl.ss.currency.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.ss.currency.domain.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {
	
	
	@Query(value = "Select c.countryName as countryName, count(0) as currencyNumber from Country c join c.currencySet s group by countryName having count(0) > 1")
	List<CountryWithMoreThanOneCurrency> getCountryWhereCurrencysMoreThanOne();
	
	List<Country> findAll();
	

}
