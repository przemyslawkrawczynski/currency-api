package pl.ss.currency.dtos.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Rates")
@XmlAccessorType(XmlAccessType.FIELD)
public class Rates {
	
	private List<RateDto> Rate;

	public Rates() {
	}

	public Rates(List<RateDto> rate) {
		super();
		Rate = rate;
	}

	public List<RateDto> getRate() {
		return Rate;
	}

	@XmlElement(name = "Rate")
	public void setRate(List<RateDto> rate) {
		Rate = rate;
	}

	@Override
	public String toString() {
		return "ClassPojo [Rate = " + Rate + "]";
	}
}
