package com.alti.baseTemplate.service.impl;

import org.openapitools.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alti.baseTemplate.entity.MerchantStore;
import com.alti.baseTemplate.mapper.MerchantMapper;
import com.alti.baseTemplate.exception.MerchantNotFoundException;
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

	public Mono<Merchant> saveMerchant(Merchant dto) throws RuntimeException {

		MerchantStore merchantStore = mapper.toEntity(dto);

		return repository.save(merchantStore).map(mapper::toDto);

	}

	public Mono<Void> deleteMerchantById(int id) throws MerchantNotFoundException {
		return repository.deleteById(id).switchIfEmpty(Mono.error(new MerchantNotFoundException("Merchant Not Found for id " + id)));
	}

	public Mono<Merchant> getMerchantById(int id) throws MerchantNotFoundException {
		// Mono<MerchantStore> found = repository.findById(id);

		return repository.findById(id).map(mapper::toDto)
				.switchIfEmpty(Mono.error(new MerchantNotFoundException("Merchant Not Found for id " + id)));
	}
}
