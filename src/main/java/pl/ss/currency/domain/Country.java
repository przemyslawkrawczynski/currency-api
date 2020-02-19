package pl.ss.currency.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String countryCode;
    private String countryName;
    
    @ManyToMany
    private Set<Currency> currency;
	public Country(Long id, String countryCode, String countryName, Set<Currency> currency) {
		super();
		this.id = id;
		this.countryCode = countryCode;
		this.countryName = countryName;
		this.currency = currency;
	}
	
    
}
