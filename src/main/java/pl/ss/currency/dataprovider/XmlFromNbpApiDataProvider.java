package pl.ss.currency.dataprovider;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.ss.currency.dataprovider.DataProvider;
import pl.ss.currency.dtos.request.CurrencyRequest;
import pl.ss.currency.exception.NbpApiConnectionException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Service
@Primary
public class XmlFromNbpApiDataProvider implements DataProvider {

	private final String NBP_URL = "http://api.nbp.pl/api/exchangerates/rates/";
	private final String RESPONSE_FORMAT = "?format=xml";

	@Value("${api.nbp.table}")
	private String tableSoruceName;

	@Override
	public String getCurrencyDataByRequest(CurrencyRequest request) throws NbpApiConnectionException {
		URL urlFromRequest = generateUrlFromCurrencyRequest(request);
		return getDataFromApi(urlFromRequest);

	}
	
	public String getCurrencyDataByRangeDateAndCurrencyCode(String currencyCode, LocalDate dateFrom, LocalDate dateTo) {
		URL urlForRangeAndCodeUrl = generateUrlFforDataRangeAndCurrencyCode(currencyCode, dateFrom, dateTo);
		return getDataFromApi(urlForRangeAndCodeUrl);
	}

	public String getDataFromApi(URL preparedUrl) {

		HttpURLConnection connection = null;
		String currencyDataResponse;

		try {
			connection = (HttpURLConnection) preparedUrl.openConnection();
			if (isCorrectApiResponse(connection) == false)
				return null;
		} catch (IOException ex) {
			throw new NbpApiConnectionException("Can't connect to NBP Api.");
		}

		try {
			InputStream currencyStream = connection.getInputStream();
			currencyDataResponse = IOUtils.toString(currencyStream, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new NbpApiConnectionException("Can't parse response from NBP Api!");
		} finally {
			connection.disconnect();
		}

		return currencyDataResponse;
	}

	private boolean isCorrectApiResponse(HttpURLConnection connection) throws IOException {
		int status = connection.getResponseCode();
		if (status != 200 && status != 304 && status != 404) {
			String msg = (status == 403) ? "Nbp Api returns 403: Bad Request"
					: "NBP Server exception - server return code: " + status;
			throw new NbpApiConnectionException(msg);
		}

		return (status != 404);
	}

	private URL generateUrlFromCurrencyRequest(CurrencyRequest request) {

		URL url = null;

		try {
			url = new URL(NBP_URL + tableSoruceName + "/" + request.getCurrencyCode() + "/" + request.getOnDate()
					+ RESPONSE_FORMAT);
		} catch (MalformedURLException e) {
			throw new NbpApiConnectionException("Can`t prepare url from request data [Currency Code: "
					+ request.getCurrencyCode() + "] [Date: " + request.getOnDate());
		}

		return url;
	}
	
	private URL generateUrlFforDataRangeAndCurrencyCode(String code, LocalDate dateFrom, LocalDate dateTo) {

		URL url = null;

		try {
			url = new URL(NBP_URL + tableSoruceName + "/" + code + "/" + dateFrom.toString() + "/" + dateTo.toString()
					+ RESPONSE_FORMAT);
		} catch (MalformedURLException e) {
			throw new NbpApiConnectionException("Can`t prepare url from range of dates [Currency Code: "
					+ code + "] [Date: " + dateFrom + "/" + dateTo );
		}

		return url;
	}
}
