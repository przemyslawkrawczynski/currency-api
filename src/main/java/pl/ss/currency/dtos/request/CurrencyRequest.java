package pl.ss.currency.dtos.request;

import java.time.LocalDate;

public class CurrencyRequest {
		
    private String tableRequest;
    private String currencyCode;
    private LocalDate onDate;
       
    public CurrencyRequest(String tableRequest, String currencyCode, LocalDate onDate) {
		super();
		this.tableRequest = tableRequest;
		this.currencyCode = currencyCode;
		this.onDate = onDate;
	}
    
	public static class CurrencyRequestBuilder {
	    private String tableRequest;
	    private String currencyCode;
	    private LocalDate onDate;
        
        public CurrencyRequestBuilder setTable(String table) {
        	this.tableRequest = table;
        	return this;
        }
        
        public CurrencyRequestBuilder currencyCode(String code) {
        	this.currencyCode = code;
        	return this;
        }
        
        public CurrencyRequestBuilder setDate(LocalDate date) {
        	this.onDate = date;
        	return this;
        }
        
        public CurrencyRequest build() {
        	return new CurrencyRequest(tableRequest, currencyCode, onDate);
        }
    }
    
	public String getCurrencyCode() {
		return currencyCode;
	}
	
	public String getTableName() {
		return tableRequest;
	}

	public LocalDate getOnDate() {
		return onDate;
	}
	
	
  
    
 }
