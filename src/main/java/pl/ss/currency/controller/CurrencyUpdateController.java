package pl.ss.currency.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.ss.currency.domain.Currency;
import pl.ss.currency.service.CurrencyUpdateInformationService;
import pl.ss.currency.validator.CurrencyRequestValidator;

@RestController
@RequestMapping("/app/currency/update")
public class CurrencyUpdateController {

	private final CurrencyUpdateInformationService service;
	private final CurrencyRequestValidator validator;

	public CurrencyUpdateController(CurrencyUpdateInformationService service, CurrencyRequestValidator validator) {
		super();
		this.service = service;
		this.validator = validator;
	}

	
	@PutMapping("/{code}/{dateFrom}/{dateTo}")
	public ResponseEntity<Currency> updateCurrencyByCodeAndRangeDate(@PathVariable String code,@PathVariable String dateFrom,@PathVariable String dateTo) {
		validator.validateCode(code);
		Currency updatedCurrency = service.updateDataInDatabase(code, LocalDate.parse(dateFrom), LocalDate.parse(dateTo));
		return ResponseEntity.ok(updatedCurrency);		
	}
		
	
	
}
