package pl.ss.currency.dtos.request;

import java.time.LocalDate;

public class CurrencyRequest {
		
    private String currencyCode;
    private LocalDate onDate;
       
    public CurrencyRequest(String currencyCode, LocalDate onDate) {
		super();

		this.currencyCode = currencyCode;
		this.onDate = onDate;
	}
    
    public CurrencyRequest() {}
    
	public static class CurrencyRequestBuilder {

	    private String currencyCode;
	    private LocalDate onDate;
        
        public CurrencyRequestBuilder currencyCode(String code) {
        	this.currencyCode = code;
        	return this;
        }
        
        public CurrencyRequestBuilder setDate(LocalDate date) {
        	this.onDate = date;
        	return this;
        }
        
        public CurrencyRequest build() {
        	return new CurrencyRequest(currencyCode, onDate);
        }
    }
    
	public String getCurrencyCode() {
		return currencyCode;
	}
	

	public LocalDate getOnDate() {
		return onDate;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public void setOnDate(LocalDate onDate) {
		this.onDate = onDate;
	}
	
	
  
    
 }
