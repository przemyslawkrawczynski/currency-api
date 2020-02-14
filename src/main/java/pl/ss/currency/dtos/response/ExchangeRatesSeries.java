package pl.ss.currency.dtos.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ExchangeRatesSeries")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExchangeRatesSeries {
	private Rates Rates;
	private String Table;
	private String Currency;
	private String Code;

	public ExchangeRatesSeries(Rates rates, String table, String currency, String code) {
		super();
		Rates = rates;
		Table = table;
		Currency = currency;
		Code = code;
	}

	public ExchangeRatesSeries() {
		super();
	}

	public Rates getRates() {
		return Rates;
	}

	@XmlElement
	public void setRates(Rates rates) {
		Rates = rates;
	}

	public String getTable() {
		return Table;
	}

	@XmlElement
	public void setTable(String table) {
		Table = table;
	}

	public String getCurrency() {
		return Currency;
	}

	@XmlElement
	public void setCurrency(String currency) {
		Currency = currency;
	}

	public String getCode() {
		return Code;
	}

	@XmlElement
	public void setCode(String code) {
		Code = code;
	}

}
