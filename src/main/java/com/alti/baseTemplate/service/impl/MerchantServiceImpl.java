package com.alti.baseTemplate.service.impl;

import org.openapitools.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alti.baseTemplate.entity.MerchantStore;
import com.alti.baseTemplate.exception.MerchantNotFoundException;
import com.alti.baseTemplate.mapper.MerchantMapper;
import com.alti.baseTemplate.service.repo.MerchantRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class MerchantServiceImpl {

	@Autowired
	MerchantRepository repository;

	private static MerchantMapper mapper = MerchantMapper.INSTANCE;

	public Flux<Merchant> getAllMerchants() {
		log.info("MerchantService findAll() method is called");
		return repository.findAll().map(mapper::toDto);
	}

	public Mono<Merchant> saveMerchant(Merchant dto) throws RuntimeException {

		log.info("Creating new merchants..");
		MerchantStore merchantStore = mapper.toEntity(dto);

		return repository.save(merchantStore).map(mapper::toDto);

	}

	public Mono<Void> deleteMerchantById(int id) throws MerchantNotFoundException {
		log.info("Check and delete merchants specified by id..");
		return repository.existsById(id).flatMap(exists -> {
			if (exists) {
				return repository.deleteById(id);
			} else {
				return Mono.error(new MerchantNotFoundException("Merchant not found with id: " + id));
			}
		});

	}

	public Mono<Merchant> getMerchantById(int id) throws MerchantNotFoundException {
		log.info("Finding merchants by id...");

		return repository.findById(id).map(mapper::toDto)
				.switchIfEmpty(Mono.error(new MerchantNotFoundException("Merchant Not Found for id " + id)));
	}
}
