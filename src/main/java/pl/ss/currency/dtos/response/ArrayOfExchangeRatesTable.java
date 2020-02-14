package pl.ss.currency.dtos.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ArrayOfExchangeRatesTable")
@XmlAccessorType(XmlAccessType.FIELD)
public class ArrayOfExchangeRatesTable {
	
	private ExchangeRatesTable ExchangeRatesTable;
	
	public ArrayOfExchangeRatesTable() {};

	public ArrayOfExchangeRatesTable(ExchangeRatesTable exchangeRatesTable) {
		super();
		ExchangeRatesTable = exchangeRatesTable;
	}


	public ExchangeRatesTable getExchangeRatesTable() {
		return ExchangeRatesTable;
	}
	
	@XmlElement
	public void setExchangeRatesTable(ExchangeRatesTable exchangeRatesTable) {
		ExchangeRatesTable = exchangeRatesTable;
	}
	
	

}
