package pl.ss.currency.validator;

import pl.ss.currency.dtos.request.CurrencyRequest;

public interface RequestValidator {
	
	void validate(CurrencyRequest requestDto);
}
