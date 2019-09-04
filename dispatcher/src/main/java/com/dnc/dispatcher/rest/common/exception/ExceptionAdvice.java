/**
 * 
 */
package com.dnc.dispatcher.rest.common.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dnc.dispatcher.rest.common.response.model.CommonResult;
import com.dnc.dispatcher.rest.common.response.service.ResponseService;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * com.dnc.dispatcher.rest.common.exception
 * ExceptionAdvice.java
 * </pre>
 *
 * @author : mkbok
 * @date    : 2019. 9. 4.
 *
 */
@RestControllerAdvice
public class ExceptionAdvice {
	
	@Autowired
	ResponseService responseService;

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected CommonResult defaultException(HttpServletRequest request, Exception e) {
		if ( e instanceof CUserNotFoundException )
			return responseService.getUserNotFoundFailResult();
		else if ( e instanceof CEmailSignupFailedException )
			return responseService.getSignUserExistFailResult();
		else if ( e instanceof CEmailSigninFailedException )
			return responseService.getSigninUserNotFoundFailResult();
		else if ( e instanceof CRequestExistByDispatcher )
			return responseService.getRequestByDispatcherExistFailResult();
		else if ( e instanceof CDispatchException )
			return responseService.getRequestDispatchFailResult();
		else if ( e instanceof CRequestExistByRequester )
			return responseService.getExistRequestFailResult();
		else
			return responseService.getFailResult();
	}

}
