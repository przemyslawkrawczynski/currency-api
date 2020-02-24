package pl.ss.currency.dtos.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CurrencyInfo {

    private String currencyCode;
    private BigDecimal currencyRate;
    private LocalDate rateDate;

    private CurrencyInfo(String currencyCode, BigDecimal currencyRate, LocalDate checkingDate) {

        this.currencyCode = currencyCode;
        this.currencyRate = currencyRate;
        this.rateDate = checkingDate;
    }

    public static class CurrencyInfoBuilder {
        private String currencyCode;
        private BigDecimal currencyRate;
        private LocalDate checkingDate;

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
            return new CurrencyInfo(currencyCode, currencyRate, checkingDate);
        }

    }



    public String getCurrencyCode() {
        return currencyCode;
    }

    public BigDecimal getCurrencyRate() {
        return currencyRate;
    }

    public LocalDate getCheckingDate() {
        return rateDate;
    }


	@Override
	public String toString() {
		return getCheckingDate()+ "| Kod Waluty: " + getCurrencyCode() + " | Średni kurs: " + getCurrencyRate();
	}
}
