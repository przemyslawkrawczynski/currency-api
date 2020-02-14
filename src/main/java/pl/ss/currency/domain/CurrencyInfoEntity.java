package pl.ss.currency.domain;

public class CurrencyInfoEntity {

    String currencyCode;
    String currencyTable;
    String currencyCheckDate;
    String currencyRate;

    public CurrencyInfoEntity(String currencyCode, String currencyDate, String currencyCheckDate, String currencyRate) {
        this.currencyCode = currencyCode;
        this.currencyTable = currencyDate;
        this.currencyCheckDate = currencyCheckDate;
        this.currencyRate = currencyRate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

        public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyTable() {
        return currencyTable;
    }

    public void setCurrencyTable(String currencyDate) {
        this.currencyTable = currencyDate;
    }

    public String getCurrencyCheckDate() {
        return currencyCheckDate;
    }

    public void setCurrencyCheckDate(String currencyCheckDate) {
        this.currencyCheckDate = currencyCheckDate;
    }

    public String getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(String currencyRate) {
        this.currencyRate = currencyRate;
    }
}
