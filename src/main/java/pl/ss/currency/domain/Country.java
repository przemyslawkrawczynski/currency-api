package pl.ss.currency.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String countryName;
    
//    @ManyToMany(mappedBy = "countrys")
//    private Set<Currency> currencySet;

    public Country() {}

    public Country(Long id, String countryName, Set<Currency> currencySet) {
		this.id = id;
		this.countryName = countryName;
	//	this.currencySet = currencySet;
	}

    public Country(String countryName) {
        this.countryName = countryName;
  //      this.currencySet = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

//    public Set<Currency> getCurrencySet() {
//        return currencySet;
//    }
//
//    public void addCurrency(Currency currency) {
//        this.currencySet.add(currency);
//    }
//
//    public void setCurrencySet(Set<Currency> currencySet) {
//        this.currencySet = currencySet;
//    }
}
