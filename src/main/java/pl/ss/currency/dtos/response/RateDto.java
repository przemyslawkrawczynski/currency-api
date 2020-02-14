package pl.ss.currency.dtos.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class RateDto {

	private String No;
	private BigDecimal Mid;
	private String EffectiveDate;	
	private String Currency;
	private String Code;
	
	public RateDto(String no, BigDecimal mid, String effectiveDate) {
		super();
		No = no;
		Mid = mid;
		EffectiveDate = effectiveDate;
	}
	
	
	public RateDto(String no, BigDecimal mid, String effectiveDate, String currency, String code) {
		super();
		No = no;
		Mid = mid;
		EffectiveDate = effectiveDate;
		Currency = currency;
		Code = code;
	}

	public RateDto() {}
	
	
	public String getNo() {
		return No;
	}

	@XmlElement(required = false)
	public void setNo(String no) {
		No = no;
	}


	public BigDecimal getMid() {
		return Mid;
	}

	@XmlElement(required = false)
	public void setMid(BigDecimal mid) {
		Mid = mid;
	}

	public String getEffectiveDate() {
		return EffectiveDate;
	}

	@XmlElement(required = false)
	public void setEffectiveDate(String effectiveDate) {
		EffectiveDate = effectiveDate;
	}


	public String getCurrency() {
		return Currency;
	}

	@XmlElement(required = false)
	public void setCurrency(String currency) {
		Currency = currency;
	}


	public String getCode() {
		return Code;
	}

	@XmlElement(required = false)
	public void setCode(String code) {
		Code = code;
	}


	@Override
	public String toString() {
		return "ClassPojo [No = " + No + ", Mid = " + Mid + ", EffectiveDate = " + EffectiveDate + "]";
	}
}
