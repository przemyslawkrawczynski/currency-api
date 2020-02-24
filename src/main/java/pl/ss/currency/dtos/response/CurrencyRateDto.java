package pl.ss.currency.dtos.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CurrencyRateDto {
	
	private Long currencyCodeId;
	private String currencyCode;
	private LocalDate rateDate;
	private BigDecimal rate;

	public CurrencyRateDto(Long currencyCodeIdString, String currencyCode, LocalDate rateDate, BigDecimal rate) {
		this.currencyCodeId = currencyCodeIdString;
		this.currencyCode = currencyCode;
		this.rateDate = rateDate;
		this.rate = rate;
	}

	public Long getCurrencyCodeIdString() {
		return currencyCodeId;
	}

	public void setCurrencyCodeIdString(Long currencyCodeIdString) {
		this.currencyCodeId = currencyCodeIdString;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public LocalDate getRateDate() {
		return rateDate;
	}

	public void setRateDate(LocalDate rateDate) {
		this.rateDate = rateDate;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}	
	
	@Override
	public String toString() {
		return  "[" + this.currencyCodeId + "] - " + currencyCode + " | " + rateDate.toString() + " | Kurs: " + rate; 
	}
	
}
