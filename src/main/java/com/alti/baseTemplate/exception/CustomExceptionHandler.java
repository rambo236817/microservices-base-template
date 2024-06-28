package com.alti.baseTemplate.exception;

import java.net.URI;
import java.util.List;

import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import reactor.core.publisher.Mono;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadParameterException.class)
	public Mono<String> invalidInputBodyHandler(BadParameterException e) {
		return Mono.just(e.getMessage() + HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(TransientDataAccessResourceException.class)
	protected ProblemDetail handleDuplicateIdException(RuntimeException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
		problemDetail.setTitle(ex.getMessage());
		problemDetail.setType(URI.create("https://localhost:8082/problems/duplicate-id-found"));
		problemDetail.setProperty("errors", List.of(ErrorDetails.API_DUPLICATE_ID_FOUND));
		return problemDetail;
	}

	/*
	 * @ResponseStatus(HttpStatus.BAD_REQUEST)
	 * 
	 * @ExceptionHandler(MerchantNotFoundException.class) public Mono<String>
	 * handleMerchantNotFound(MerchantNotFoundException e) { return
	 * Mono.just(e.getMessage()); }
	 * 
	 */
	@ExceptionHandler(MerchantNotFoundException.class)
	protected ProblemDetail handleNotFound(RuntimeException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
		problemDetail.setTitle(ex.getMessage());
		problemDetail.setType(URI.create("https://localhost:8082/problems/merchant-not-found"));
		problemDetail.setProperty("errors", List.of(ErrorDetails.API_MERCHANT_NOT_FOUND));
		return problemDetail;
	}
}
