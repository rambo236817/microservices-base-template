package com.alti.baseTemplate.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;

@JsonSerialize(using = ErrorDetailsSerializer.class)
public enum ErrorDetails {
	API_MERCHANT_NOT_FOUND(123, "Merchant not found", "http://localhost:8082.com/merchants"),
	API_DUPLICATE_ID_FOUND(123, "Duplicate merchant id found", "http://localhost:8082.com/merchants/create");

	@Getter
	private Integer errorCode;
	@Getter
	private String errorMessage;
	@Getter
	private String referenceUrl;

	ErrorDetails(Integer errorCode, String errorMessage, String referenceUrl) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.referenceUrl = referenceUrl;
	}
}
