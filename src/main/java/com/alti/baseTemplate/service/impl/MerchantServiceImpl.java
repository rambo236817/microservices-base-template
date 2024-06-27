package com.alti.baseTemplate.service.impl;

import org.openapitools.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alti.baseTemplate.entity.MerchantStore;
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

	public Mono<Merchant> saveMerchant(Merchant dto) {

		MerchantStore merchantStore = mapper.toEntity(dto);

		return repository.save(merchantStore).map(mapper::toDto);

	}

	public Mono<Void> deleteMerchantById(int id){
		return repository.deleteById(id);
	}
	
	public Mono<Merchant> getMerchantById(int id){
		return repository.findById(id).map(mapper::toDto);
	}
}
