package br.com.find.address.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Address not found exception. It is thrown when an address can not be found with the given postal code.
 * 
 * @author Eduardo
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AddressNotFoundException extends RuntimeException {
	
	/**
	 * UID
	 */
	private static final long serialVersionUID = -7688292650960634068L;
	
	/**
	 * Exception message.
	 */
	private static final String EXCEPTION_MESSAGE = "No address was found for: ";
	
	/**
	 * Composed constructor.
	 * 
	 * @param postalCode Postal code
	 */
	public AddressNotFoundException(String postalCode) {
		super(EXCEPTION_MESSAGE + postalCode);
	}

}
