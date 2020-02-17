package pl.ss.currency.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@NamedQuery(name = "Currency.isExistByDateAndCurrencyCode",
            query = "SELECT count(0) FROM Currency WHERE currencyRateDate LIKE :date AND currencyCode LIKE :code")
@NamedQuery(name = "Currency.getByCodeAndDate",
        query = "FROM Currency WHERE currencyRateDate LIKE :date AND currencyCode LIKE :code")

@Entity
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String currencyCode;
    private LocalDate currencyRateDate;
    private String currencyTableOnNbp;
    private BigDecimal currencyRate;
    private String currencyDescription;

    public Currency(Long id, String currencyCode, LocalDate currencyRateDate, String currencyTableOnNbp, BigDecimal currencyRate, String currencyDescription) {
        this.id = id;
        this.currencyCode = currencyCode;
        this.currencyRateDate = currencyRateDate;
        this.currencyTableOnNbp = currencyTableOnNbp;
        this.currencyRate = currencyRate;
        this.currencyDescription = currencyDescription;
    }

    public Currency() {
    }

    public Currency(String currencyCode, String currencyRateDate, String currencyTableOnNbp, BigDecimal currencyRate, String currencyDescription) {
        this.currencyCode = currencyCode;
        this.currencyRateDate = LocalDate.parse(currencyRateDate);
        this.currencyTableOnNbp = currencyTableOnNbp;
        this.currencyRate = currencyRate;
        this.currencyDescription = currencyDescription;
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

    public LocalDate getCurrencyRateDate() {
        return currencyRateDate;
    }

    public void setCurrencyRateDate(LocalDate currencyRateDate) {
        this.currencyRateDate = currencyRateDate;
    }

    public String getCurrencyTableOnNbp() {
        return currencyTableOnNbp;
    }

    public void setCurrencyTableOnNbp(String currencyTableOnNbp) {
        this.currencyTableOnNbp = currencyTableOnNbp;
    }

    public BigDecimal getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(BigDecimal currencyRate) {
        this.currencyRate = currencyRate;
    }

    public String getCurrencyDescription() {
        return currencyDescription;
    }

    public void setCurrencyDescription(String currencyDescription) {
        this.currencyDescription = currencyDescription;
    }
}
