package com.alti.baseTemplate.service.repo;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.alti.baseTemplate.entity.MerchantStore;

@Repository
public interface MerchantRepository extends R2dbcRepository<MerchantStore, Integer> {

}
