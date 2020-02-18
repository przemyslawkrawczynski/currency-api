package pl.ss.currency.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

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
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<CurrencyRate> rates;

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
    
}
