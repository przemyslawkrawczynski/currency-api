package pl.ss.currency.validator;

import org.springframework.stereotype.Service;
import pl.ss.currency.dtos.request.CurrencyRequest;
import pl.ss.currency.exception.ValidationException;

import java.time.LocalDate;
import java.util.Arrays;

@Service
public class CurrencyRequestValidator implements RequestValidator {

    private final String[] currencyCodes = {"USD", "SEK", "EUR", "CHF"};

    public void validateCode(String code) {
        if (!Arrays.asList(currencyCodes).contains(code)) {
            throw new ValidationException("Wrong argument CurrencyCode: " + code);
        }
    }


    private void validateDate(LocalDate localDate) {
    	if (localDate == null) { 
    		throw new ValidationException("Wrong argument date on request: " + localDate );
    	}
        if (localDate.isAfter(LocalDate.now())) {
            throw new ValidationException("Wrong argument date: " + localDate + ". Date can't be after " + LocalDate.now());
        }
        
    }

    @Override
    public void validate(CurrencyRequest requestDto) throws ValidationException {
        validateDate(requestDto.getOnDate());
        validateCode(requestDto.getCurrencyCode());
    }

}

