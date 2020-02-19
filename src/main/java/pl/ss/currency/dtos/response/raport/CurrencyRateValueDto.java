package pl.ss.currency.dtos.response.raport;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CurrencyRateValueDto {
	
	private String currencyCode;
	private LocalDate rateDate;
	private BigDecimal queryValue;

	public CurrencyRateValueDto(String currencyCode, BigDecimal currencyRateValue) {
		super();
		this.currencyCode = currencyCode;
		this.queryValue = currencyRateValue;
	}
	
	
	public CurrencyRateValueDto(String currencyCode, LocalDate rateDate, BigDecimal queryValue) {
		super();
		this.currencyCode = currencyCode;
		this.rateDate = rateDate;
		this.queryValue = queryValue;
	}

	public LocalDate getRateDate() {
		return rateDate;
	}


	public void setRateDate(LocalDate rateDate) {
		this.rateDate = rateDate;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}
	
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	public BigDecimal getCurrencyRateValue() {
		return queryValue;
	}
	
	public void setCurrencyRateValue(BigDecimal currencyRateValue) {
		this.queryValue = currencyRateValue;
	}
	
	
}
