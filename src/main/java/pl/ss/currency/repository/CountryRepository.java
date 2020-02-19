package pl.ss.currency.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.ss.currency.domain.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {
}
