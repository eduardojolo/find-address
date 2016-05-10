package br.com.find.address.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.find.address.dto.ErrorResponseDTO;
import br.com.find.address.exception.AddressNotFoundException;

/**
 * Exception handler controller.
 * 
 * @author Eduardo
 *
 */
@ControllerAdvice
public class ExceptionHandlerController {

	/**
	 * Handle the AddressNotFoundException returning a better message to the requester.
	 * 
	 * @param addressNotFoundException Address not found exception.
	 * @return ErrorResponseDTO
	 */
	@ExceptionHandler(AddressNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorResponseDTO requestHandlingNoHandlerFound(AddressNotFoundException addressNotFoundException) {
		return new ErrorResponseDTO(addressNotFoundException.getMessage());
	}
}
