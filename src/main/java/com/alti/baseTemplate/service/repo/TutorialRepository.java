package com.alti.baseTemplate.service.repo;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.alti.baseTemplate.entity.TutorialDomain;

import reactor.core.publisher.Flux;

@Repository
public interface TutorialRepository extends R2dbcRepository<TutorialDomain, Integer> {
	Flux<TutorialDomain> findByTitleContaining(String title);

	Flux<TutorialDomain> findByPublished(boolean isPublished);
}
