package com.alti.baseTemplate.controllers;

import org.openapitools.api.MerchantsApi;
import org.openapitools.model.Merchant;
import org.openapitools.model.MerchantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.alti.baseTemplate.service.impl.MerchantServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class MerchantController implements MerchantsApi {
	@Autowired
	MerchantServiceImpl service;

	@Override
	public Mono<Merchant> createMerchant(Mono<Merchant> merchant, ServerWebExchange exchange) {
		return merchant.flatMap(service::saveMerchant);
//		return Mono.just(new MerchantResponse("Created Merchant"));
	}

	@Override
	public Mono<String> deleteMerchantById(String merchantId, ServerWebExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<Merchant> listMerchants(ServerWebExchange exchange) {
		return service.getAllMerchants();
	}

	@Override
	public Mono<Merchant> showMerchantById(String merchantId, ServerWebExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

}
