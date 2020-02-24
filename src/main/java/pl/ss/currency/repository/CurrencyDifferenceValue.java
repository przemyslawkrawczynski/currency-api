package pl.ss.currency.repository;

import java.math.BigDecimal;

public interface CurrencyDifferenceValue {
	
	String getCurrencyCode();
	BigDecimal getQueryValue();

}
