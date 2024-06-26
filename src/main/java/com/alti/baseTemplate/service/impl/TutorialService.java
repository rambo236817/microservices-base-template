package com.alti.baseTemplate.service.impl;

import org.openapitools.model.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alti.baseTemplate.entity.TutorialDomain;
import com.alti.baseTemplate.mapper.TutorialMapper;
import com.alti.baseTemplate.service.repo.TutorialRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TutorialService {

	@Autowired
	TutorialRepository tutorialRepository;

	private final TutorialMapper tutorialMapper = TutorialMapper.INSTANCE;

	public Flux<Tutorial> findAll() {
		return tutorialRepository.findAll().map(tutorialMapper::toModel);

	}

	public Flux<Tutorial> findByTitleContaining(String title) {
		return tutorialRepository.findByTitleContaining(title).map(tutorialMapper::toModel);
	}

	public Mono<Tutorial> findById(int id) {
		return tutorialRepository.findById(id).map(tutorialMapper::toModel);
	}

	public Mono<Tutorial> save(Mono<Tutorial> createTutorialRequest) {
		Mono<TutorialDomain> tutDomain = createTutorialRequest.map(tutorialMapper::toDomain);

		return tutDomain.flatMap(tutorialRepository::save).map(tutorialMapper::toModel);
	}

	public Mono<Tutorial> update(int id, Mono<Tutorial> updateMono) {

		return updateMono.flatMap(model -> {
			// Retrieve existing entity or handle not found scenario
			return tutorialRepository.findById(id).flatMap(existingEntity -> {
				// Update existing entity with model data
				existingEntity.setDescription(model.getDescription());
				existingEntity.setPublished(model.getPublished());
				existingEntity.setTitle(model.getTitle());
				// Save updated entity
				return tutorialRepository.save(existingEntity);
			}).map(tutorialMapper::toModel);
		});
	}

	public Mono<Void> deleteById(int id) {
		return tutorialRepository.deleteById(id);
	}

	public Mono<Void> deleteAll() {
		return tutorialRepository.deleteAll();
	}

	public Flux<Tutorial> findByPublished(boolean isPublished) {
		return tutorialRepository.findByPublished(isPublished).map(tutorialMapper::toModel);
	}
}
