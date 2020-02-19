package pl.ss.currency.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.ss.currency.dtos.request.CurrencyRequest;
import pl.ss.currency.dtos.response.CurrencyInfo;
import pl.ss.currency.service.CurrencyReportService;
import pl.ss.currency.service.CurrencyService;

@RestController
@RequestMapping("/app/currency")
public class CurrencyController {
	
	
	private final CurrencyService currencyService;

	public CurrencyController(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}
	
	@GetMapping	
	public CurrencyInfo getInfoByDate(@RequestBody CurrencyRequest currencyRequest) {		
		return currencyService.getCurrencyByRequest(currencyRequest);
		
	}
	
	
}
