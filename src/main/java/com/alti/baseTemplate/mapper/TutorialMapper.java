package com.alti.baseTemplate.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.Tutorial;

import com.alti.baseTemplate.entity.TutorialDomain;

@Mapper
public interface TutorialMapper {

	TutorialMapper INSTANCE = Mappers.getMapper(TutorialMapper.class);

	TutorialDomain toDomain(Tutorial tutorial);

	Tutorial toModel(TutorialDomain tutorialDomain);

}
