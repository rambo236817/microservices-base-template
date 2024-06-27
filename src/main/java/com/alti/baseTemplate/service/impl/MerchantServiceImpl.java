package com.alti.baseTemplate.service.impl;

import org.openapitools.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alti.baseTemplate.mapper.MerchantMapper;
import com.alti.baseTemplate.service.repo.MerchantRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MerchantServiceImpl {

	@Autowired
	MerchantRepository repository;

	private static MerchantMapper mapper = MerchantMapper.INSTANCE;

	public Flux<Merchant> getAllMerchants() {
		return repository.findAll().map(mapper::toDto);
	}
	
	public Mono<Merchant> saveMerchant(Merchant dto){
		return Mono.just(dto).map(mapper::toEntity).flatMap(repository::save).map(mapper::toDto);
		
	}
}
