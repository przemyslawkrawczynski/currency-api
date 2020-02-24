package pl.ss.currency.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface RateValueDto {
	
	LocalDate getRateDate();
	BigDecimal getRateValue();
}
