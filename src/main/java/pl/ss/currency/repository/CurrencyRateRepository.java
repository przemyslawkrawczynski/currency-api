package pl.ss.currency.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.ss.currency.domain.CurrencyRate;

@Repository
public interface CurrencyRateRepository extends CrudRepository<CurrencyRate, Long> {

}
