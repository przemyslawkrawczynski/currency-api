package pl.ss.currency.dataprovider;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pl.ss.currency.dataprovider.XmlFromNbpApiDataProvider;
import pl.ss.currency.dtos.request.CurrencyRequest;

public class XmlFromNbpApiDataProviderTestSuits {
	
	XmlFromNbpApiDataProvider dataProvider = new XmlFromNbpApiDataProvider();

	@Test
	public void shouldGetXmlStringResponseFromNbpApiWhenRequestIsNotSundayOrSaturday() {

		// Given
		String requestTable = "A";
		String requestCurrencyCode = "USD";
		LocalDate requestDateOnDayOtherThanSaturdayAndSunday = LocalDate.of(2020, 02, 13);

		CurrencyRequest preparedRequest = new CurrencyRequest.CurrencyRequestBuilder().currencyCode(requestCurrencyCode)
				.setDate(requestDateOnDayOtherThanSaturdayAndSunday).setTable(requestTable).build();

		// When
		String response = dataProvider.getCurrencyDataByRequest(preparedRequest);

		// Then
		Assert.assertTrue(response instanceof String);

	}

	@Test
	public void shouldGetNullResponseFromNbpApiWhenRequestDependsWeekendDays() {

		// Given
		String requestTable = "A";
		String requestCurrencyCode = "USD";
		LocalDate requestDateOnSaturdayInPast = LocalDate.of(2020, 02, 8);

		CurrencyRequest preparedRequest = new CurrencyRequest.CurrencyRequestBuilder().currencyCode(requestCurrencyCode)
				.setDate(requestDateOnSaturdayInPast).setTable(requestTable).build();

		// When
		String response = dataProvider.getCurrencyDataByRequest(preparedRequest);

		// Then
		Assert.assertNull(response);

	}

}
