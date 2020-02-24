package pl.ss.currency.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.ss.currency.domain.CurrencyRate;

@Repository
public interface CurrencyRateRepository extends CrudRepository<CurrencyRate, Long> {
	
	@Query(value = "Select c.currency.currencyCode as currencyCode, (max(c.rateValue)-min(c.rateValue)) as queryValue From CurrencyRate c WHERE c.rateDate between :dateFrom AND :dateTo group by c.currency.currencyCode order by queryValue DESC")
	List<CurrencyDifferenceValue> getDifference(@Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);
	
	@Query(value = "Select c From CurrencyRate c Where c.rateDate = :date and c.currency.currencyCode = :currencyCode")
	Optional<CurrencyRate> getRateByCurrencyCodeAndDate(@Param("date") LocalDate date, @Param("currencyCode") String currencyCode);
	
	@Query(value = "Select c.rateDate as rateDate, c.rateValue as rateValue from CurrencyRate c" + 
			" where c.currency.currencyCode = :currencyCode" + 
			" and c.rateDate between :dateFrom and :dateTo" + 
			" and c.rateValue = ("+ 
			" select min(r.rateValue) from CurrencyRate r " + 
			" where r.currency.currencyCode = :currencyCode" + 
			" AND r.rateDate between :dateFrom and :dateTo)")
	List<RateValueDto> getMinValueByCodeAndDateRange(@Param("currencyCode") String currencyCode, @Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);
	
	@Query(value = "Select c.rateDate as rateDate, c.rateValue as rateValue from CurrencyRate c" + 
			" where c.currency.currencyCode = :currencyCode" + 
			" and c.rateDate between :dateFrom and :dateTo" + 
			" and c.rateValue = ("+ 
			" select max(r.rateValue) from CurrencyRate r " + 
			" where r.currency.currencyCode = :currencyCode" + 
			" AND r.rateDate between :dateFrom and :dateTo)")
	List<RateValueDto> getMaxValueByCodeAndDateRange(@Param("currencyCode") String currencyCode, @Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);
	
	@Query(value = "SELECT c FROM CurrencyRate c WHERE c.currency.currencyCode = :currencyCode ORDER by c.rateValue DESC")
	List<CurrencyRate> get5BestRatesByCode(@Param("currencyCode") String currencyCode);	
	
	@Query(value = "SELECT c FROM CurrencyRate c WHERE c.currency.currencyCode = :currencyCode ORDER by c.rateValue ASC")
	List<CurrencyRate> get5LowestRatesByCode(@Param("currencyCode") String currencyCode);	
	
}
