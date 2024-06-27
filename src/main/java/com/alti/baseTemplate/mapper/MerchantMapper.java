package com.alti.baseTemplate.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.Merchant;

import com.alti.baseTemplate.entity.MerchantStore;

@Mapper
public interface MerchantMapper {

	MerchantMapper INSTANCE = Mappers.getMapper(MerchantMapper.class);

	MerchantStore toEntity(Merchant dto);

	Merchant toDto(MerchantStore entity);

}
