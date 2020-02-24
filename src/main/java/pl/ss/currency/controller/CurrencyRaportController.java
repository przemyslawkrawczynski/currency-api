package pl.ss.currency.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.ss.currency.dtos.response.raport.CurrencyRateValueDto;
import pl.ss.currency.repository.CurrencyDifferenceValue;
import pl.ss.currency.service.CurrencyReportService;

@RestController
@RequestMapping("/app/report")
public class CurrencyRaportController {
	
	private final CurrencyReportService currencyReportService;
	
	public CurrencyRaportController(CurrencyReportService currencyReportService) {
		super();
		this.currencyReportService = currencyReportService;
	}


	@GetMapping("/min/{currencyCode}/{dateFrom}/{dateTo}")
	public ResponseEntity<CurrencyRateValueDto> getMinValue(@PathVariable String currencyCode, @PathVariable String dateFrom, @PathVariable String dateTo) {
		
		CurrencyRateValueDto result = currencyReportService.getMinValueByCodeAndRange(currencyCode, LocalDate.parse(dateFrom), LocalDate.parse(dateTo));
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/max/{currencyCode}/{dateFrom}/{dateTo}")
	public ResponseEntity<CurrencyRateValueDto> getMaxValue(@PathVariable String currencyCode, @PathVariable String dateFrom, @PathVariable String dateTo) {
		
		CurrencyRateValueDto result = currencyReportService.getMaxValueByCodeAndRange(currencyCode, LocalDate.parse(dateFrom), LocalDate.parse(dateTo));
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/diff/{dateFrom}/{dateTo}")
	public ResponseEntity<CurrencyDifferenceValue> getCurrencyWhereMaxDiffrenceInTime(@PathVariable String dateFrom, @PathVariable String dateTo) {	
				
		CurrencyDifferenceValue dto = currencyReportService.getCurrencyWhereMaxDiffrenceRateOfTime(LocalDate.parse(dateFrom),LocalDate.parse(dateTo));
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/best5/{currencyCode}")
	public ResponseEntity<List<CurrencyRateValueDto>> get5BestRates(@PathVariable String currencyCode) {
		
		List<CurrencyRateValueDto> result = currencyReportService.get5BestRates(currencyCode);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/lowest5/{currencyCode}")
	public ResponseEntity<List<CurrencyRateValueDto>> get5LowestRate(@PathVariable String currencyCode) {
		
		List<CurrencyRateValueDto> result = currencyReportService.get5LowestRates(currencyCode);
		return ResponseEntity.ok(result);
	}
	
}
