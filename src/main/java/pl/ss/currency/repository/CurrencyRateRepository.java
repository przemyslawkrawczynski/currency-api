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
	
	@Query(value = "SELECT c.currency_code, (max(RATE_VALUE)-min(RATE_VALUE)) as difference " +
			"FROM CURRENCY_RATE r " +
			"INNER JOIN CURRENCY c " +
			"ON c.id = currency_id " +
			"where r.RATE_DATE between :dateFrom AND :dateTo " +
			"group by currency_id " +
			"order by difference DESC;", nativeQuery = true)
	List<Object[]> getDifferences(@Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);
	
	@Query(value = "select * from CURRENCY_RATE \r\n" + 
			"where rate_date like :date\r\n" + 
			"AND currency_id = (select c.id FROM CURRENCY c WHERE CURRENCY_CODE like :currencyCode)", nativeQuery = true)
	Optional<CurrencyRate> getRateByCurrencyCodeAndDate(@Param("date") LocalDate date, @Param("currencyCode") String currencyCode);

}
