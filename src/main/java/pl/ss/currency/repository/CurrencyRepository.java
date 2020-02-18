package pl.ss.currency.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pl.ss.currency.domain.Currency;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {

    @Query
    long isExistByDateAndCurrencyCode(@Param("date")LocalDate date, @Param("code") String code);

    @Query
    List<Object[]> getRateByCodeAndDate(@Param("date")LocalDate date, @Param("code") String code);
    
   
    
}
