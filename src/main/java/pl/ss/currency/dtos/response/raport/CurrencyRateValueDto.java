package pl.ss.currency.dtos.response.raport;

import java.math.BigDecimal;

public class CurrencyRateValueDto {
	
	private String currencyCode;
	private BigDecimal currencyRateValue;
	
	public CurrencyRateValueDto(String currencyCode, BigDecimal currencyRateValue) {
		super();
		this.currencyCode = currencyCode;
		this.currencyRateValue = currencyRateValue;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public BigDecimal getCurrencyRateValue() {
		return currencyRateValue;
	}
	public void setCurrencyRateValue(BigDecimal currencyRateValue) {
		this.currencyRateValue = currencyRateValue;
	}
	
	
}
