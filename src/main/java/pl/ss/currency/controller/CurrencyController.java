package pl.ss.currency.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.ss.currency.domain.CurrencyRate;
import pl.ss.currency.dtos.request.CurrencyRequest;
import pl.ss.currency.dtos.response.CurrencyInfo;
import pl.ss.currency.repository.CurrencyRateRaportRepository;
import pl.ss.currency.service.CurrencyService;

@RestController
@RequestMapping("/app/currency")
public class CurrencyController {
	
	
	private final CurrencyService currencyService;
	private final CurrencyRateRaportRepository raportRepository;


	public CurrencyController(CurrencyService currencyService, CurrencyRateRaportRepository raportRepository) {
		super();
		this.currencyService = currencyService;
		this.raportRepository = raportRepository;
	}

	@GetMapping	
	public CurrencyInfo getInfoByDate(@RequestBody CurrencyRequest currencyRequest) {		
		return currencyService.getCurrencyByRequest(currencyRequest);
		
	}
	
	@GetMapping("/list")
	public List<CurrencyRate> getList() {
		return raportRepository.get5maxResults("USD");
	}
}
