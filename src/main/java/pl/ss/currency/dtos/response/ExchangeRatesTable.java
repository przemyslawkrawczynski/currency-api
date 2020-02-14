package pl.ss.currency.dtos.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ExchangeRatesTable")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExchangeRatesTable {

    private String Table;
    private String No;
    private String EffectiveDate;
    private Rates Rates;

    public ExchangeRatesTable() {
        super();
    }

    public ExchangeRatesTable(String table, String no, String effectiveDate, Rates rates) {
        super();
        Table = table;
        No = no;
        EffectiveDate = effectiveDate;
        this.Rates = rates;
    }

    public Rates getRates() {
        return Rates;
    }

    public void setRates(Rates rates) {
        this.Rates = rates;
    }

    public String getTable() {
        return Table;
    }

    public void setTable(String table) {
        Table = table;
    }

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public String getEffectiveDate() {
        return EffectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        EffectiveDate = effectiveDate;
    }

}
