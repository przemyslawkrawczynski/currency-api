package pl.ss.currency.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CurrencyInfo {

    private String currencyName;
    private String currencyCode;
    private BigDecimal currencyRate;
    private LocalDate checkingDate;

    private CurrencyInfo(String currencyName, String currencyCode, BigDecimal currencyRate, LocalDate checkingDate) {
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
        this.currencyRate = currencyRate;
        this.checkingDate = checkingDate;
    }

    public static class CurrencyInfoBuilder {
        private String currencyName;
        private String currencyCode;
        private BigDecimal currencyRate;
        private LocalDate checkingDate;

        public CurrencyInfoBuilder setCurrencyName(String name) {
            this.currencyName = name;
            return this;
        }
        public CurrencyInfoBuilder setCurrencyCode(String code) {
            this.currencyCode = code;
            return this;
        }
        public CurrencyInfoBuilder setCurrencyRate(BigDecimal rate) {
            this.currencyRate = rate;
            return this;
        }
        public CurrencyInfoBuilder setCheckingDate(String date) {
            this.checkingDate = parseDateFromString(date);
            return this;
        }

        private LocalDate parseDateFromString(String date) {
            int year = Integer.parseInt(date.substring(0,4));
            int month = Integer.parseInt(date.substring(5,7));
            int day = Integer.parseInt(date.substring(8,10));
            return LocalDate.of(year, month, day);
        }

        public CurrencyInfo build() {
            return new CurrencyInfo(currencyName, currencyCode, currencyRate, checkingDate);
        }

    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public BigDecimal getCurrencyRate() {
        return currencyRate;
    }

    public LocalDate getCheckingDate() {
        return checkingDate;
    }


	@Override
	public String toString() {
		return getCheckingDate()+ "| Kod Waluty: " + getCurrencyCode() + " | Nazwa waluty: " + getCurrencyName() + " | Średni kurs: " + getCurrencyRate();
	}
}
