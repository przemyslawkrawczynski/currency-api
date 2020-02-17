package pl.ss.currency.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.ss.currency.domain.Currency;

import java.time.LocalDate;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {

    @Query
    long isExistByDateAndCurrencyCode(@Param("date")LocalDate date, @Param("code") String code);

    @Query
    Currency getByCodeAndDate(@Param("date")LocalDate date, @Param("code") String code);
}
