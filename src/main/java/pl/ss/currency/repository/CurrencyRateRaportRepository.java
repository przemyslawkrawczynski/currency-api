package pl.ss.currency.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import pl.ss.currency.domain.CurrencyRate;



@Component
public class CurrencyRateRaportRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<CurrencyRate> get5maxResults(String currencyCode) {
		
		TypedQuery<CurrencyRate> query = entityManager.createQuery("SELECT c FROM CurrencyRate c WHERE c.currency.currencyCode = :currencyCode ORDER by c.rateValue DESC", CurrencyRate.class);
		query.setParameter("currencyCode", currencyCode);
		query.setMaxResults(5);
		List<CurrencyRate> resultList = query.getResultList();
		
		return resultList;
		
	}
	
	public List<CurrencyRate> get5minResults(String currencyCode) {
		
		TypedQuery<CurrencyRate> query = entityManager.createQuery("SELECT c FROM CurrencyRate c WHERE c.currency.currencyCode = :currencyCode ORDER by c.rateValue ASC", CurrencyRate.class);
		query.setParameter("currencyCode", currencyCode);
		query.setMaxResults(5);
		List<CurrencyRate> resultList = query.getResultList();
		
		return resultList;
		
	}
	

}
