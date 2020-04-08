package pl.ss.currency.dataprovider;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.ss.currency.dataprovider.XmlFromNbpApiDataProvider;
import pl.ss.currency.dtos.request.CurrencyRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XmlFromNbpApiDataProviderTestSuits {
	
	XmlFromNbpApiDataProvider dataProvider = new XmlFromNbpApiDataProvider();

	@Test
	public void shouldGetXmlStringResponseFromNbpApiWhenRequestIsNotSundayOrSaturday() {

		// Given
		String requestTable = "A";
		String requestCurrencyCode = "USD";
		LocalDate requestDateOnDayOtherThanSaturdayAndSunday = LocalDate.of(2020, 02, 13);

		CurrencyRequest preparedRequest = new CurrencyRequest.CurrencyRequestBuilder().currencyCode(requestCurrencyCode)
				.setDate(requestDateOnDayOtherThanSaturdayAndSunday).build();

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
				.setDate(requestDateOnSaturdayInPast).build();

		// When
		String response = dataProvider.getCurrencyDataByRequest(preparedRequest);

		// Then
		Assert.assertNull(response);

	}

}
