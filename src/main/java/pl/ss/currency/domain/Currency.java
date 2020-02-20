package pl.ss.currency.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@NamedQuery(name = "Currency.isExistByDateAndCurrencyCode",
            query = "SELECT count(0) from Currency c "
            		+ "INNER JOIN CurrencyRate r "
            		+ "ON r.currency.id = c.id "
            		+ "WHERE c.currencyCode like :code "
            		+ "AND r.rateDate like :date")

@NamedQuery(name = "Currency.getRateByCodeAndDate",
        query = "SELECT c.id, c.currencyCode, r.rateDate, r.rateValue from Currency c "
        		+ "INNER JOIN CurrencyRate r "
        		+ "ON r.currency.id = c.id "
        		+ "WHERE c.currencyCode like :code "
        		+ "AND r.rateDate like :date")

@NamedQuery(name = "Currency.getIdByCodeName",
query = "SELECT id from Currency c "
		+ "WHERE c.currencyCode like :code")

@Entity
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String currencyCode;
    private String currencyTableOnNbp;
    private String currencyDescription;
    
    @OneToMany(
            targetEntity = CurrencyRate.class,
            mappedBy = "currency",
            cascade = { CascadeType.MERGE, CascadeType.PERSIST },
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private Set<CurrencyRate> rates;
    
    @ManyToMany
    private Set<Country> countrySet;

    public Currency(Long id, String currencyCode, String currencyTableOnNbp,  String currencyDescription) {
        this.id = id;
        this.currencyCode = currencyCode;
        this.currencyTableOnNbp = currencyTableOnNbp;
        this.currencyDescription = currencyDescription;
        this.rates = new HashSet<>();
    }

    public Currency() {
    }

    public Currency(String currencyCode, String currencyTableOnNbp, String currencyDescription) {
        this.currencyCode = currencyCode;
        this.currencyTableOnNbp = currencyTableOnNbp;
        this.currencyDescription = currencyDescription;
        this.rates = new HashSet<>();
        this.countrySet = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }


    public String getCurrencyTableOnNbp() {
        return currencyTableOnNbp;
    }

    public void setCurrencyTableOnNbp(String currencyTableOnNbp) {
        this.currencyTableOnNbp = currencyTableOnNbp;
    }


    public String getCurrencyDescription() {
        return currencyDescription;
    }

    public void setCurrencyDescription(String currencyDescription) {
        this.currencyDescription = currencyDescription;
    }
    
    public void addNewRateByDate(CurrencyRate currencyRate) {
    	this.rates.add(currencyRate);
    }
    
    public Optional<BigDecimal> getRateByDate(LocalDate date) {
    	return rates.stream()
    			.filter(rate -> rate.getRateDate().equals(date))
    			.map(rate -> rate.getRateValue())
    			.findFirst();
    }

	public Set<CurrencyRate> getRates() {
		return rates;
	}

	public void setRates(Set<CurrencyRate> rates) {
		this.rates = rates;
	}
    
	public void addCountry(Country country) {
        this.countrySet.add(country);
    }
    
    
}
