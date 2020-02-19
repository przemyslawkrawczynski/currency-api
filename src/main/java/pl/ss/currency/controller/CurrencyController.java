package pl.ss.currency.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.ss.currency.dtos.request.CurrencyRequest;
import pl.ss.currency.dtos.response.CurrencyInfo;
import pl.ss.currency.dtos.response.raport.CurrencyRateValueDto;
import pl.ss.currency.service.CurrencyReportService;
import pl.ss.currency.service.CurrencyService;

@RestController
@RequestMapping("/app/currency")
public class CurrencyController {
	
	
	private final CurrencyService currencyService;
	private final CurrencyReportService currencyReportService;

	public CurrencyController(CurrencyService currencyService, CurrencyReportService currencyReportService) {
		this.currencyReportService = currencyReportService;
		this.currencyService = currencyService;
	}
	
	@GetMapping	
	public CurrencyInfo getInfoByDate(@RequestBody CurrencyRequest currencyRequest) {		
		return currencyService.getCurrencyByRequest(currencyRequest);
		
	}
	
	@GetMapping("/diff/{dateFrom}/{dateTo}")
	public ResponseEntity<CurrencyRateValueDto> getCurrencyWhereMaxDiffrenceInTime(@PathVariable String dateFrom, @PathVariable String dateTo) {	
				
		CurrencyRateValueDto dto = currencyReportService.getCurrencyWhereMaxDiffrenceRateOfTime(LocalDate.parse(dateFrom),LocalDate.parse(dateTo));
		return ResponseEntity.ok(dto);
	}
	
}
