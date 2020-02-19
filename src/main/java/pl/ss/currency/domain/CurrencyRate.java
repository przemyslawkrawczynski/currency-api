package pl.ss.currency.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class CurrencyRate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "currency_id")
	@JsonBackReference
	private Currency currency;
	private LocalDate rateDate;
	@Column(precision = 5, scale = 4, columnDefinition = "DECIMAL(5,4)")
	private BigDecimal rateValue;
	
	public CurrencyRate(Long id, Currency currency, LocalDate rateDate, BigDecimal rateValue) {
		super();
		this.id = id;
		this.currency = currency;
		this.rateDate = rateDate;
		this.rateValue = rateValue;
	}
	
	public CurrencyRate() {}	
	
	public CurrencyRate(Currency currency, LocalDate rateDate, BigDecimal rateValue) {
		this.currency = currency;
		this.rateDate = rateDate;
		this.rateValue = rateValue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public LocalDate getRateDate() {
		return rateDate;
	}

	public void setRateDate(LocalDate rateDate) {
		this.rateDate = rateDate;
	}

	public BigDecimal getRateValue() {
		return rateValue;
	}

	public void setRateValue(BigDecimal rateValue) {
		this.rateValue = rateValue;
	}
		
	
	
}
