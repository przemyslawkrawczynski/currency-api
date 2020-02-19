package pl.ss.currency.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataValueException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DataValueException(String msg) {
		super(msg);
	}
}
