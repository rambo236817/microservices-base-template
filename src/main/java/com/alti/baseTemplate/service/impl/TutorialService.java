package com.alti.baseTemplate.service.impl;

import org.openapitools.model.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alti.baseTemplate.entity.TutorialDomain;
import com.alti.baseTemplate.exception.TutorialNotFoundException;
import com.alti.baseTemplate.mapper.TutorialMapper;
import com.alti.baseTemplate.service.repo.TutorialRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class TutorialService {

	@Autowired
	TutorialRepository tutorialRepository;

	private final TutorialMapper tutorialMapper = TutorialMapper.INSTANCE;

	public Flux<Tutorial> findAll() {
		log.info("TutorialService findAll() method called");
		return tutorialRepository.findAll().map(tutorialMapper::toModel);
	}

	public Flux<Tutorial> findByTitleContaining(String title) {
		log.info("TutorialService findByTitleContaining() method called with title {}", title);
		return tutorialRepository.findByTitleContaining(title).map(tutorialMapper::toModel);
	}

	public Mono<Tutorial> findById(int id) {
		log.info("TutorialService findById() method called with id {}", id);
		return tutorialRepository.findById(id)
				.switchIfEmpty(Mono.error(new TutorialNotFoundException("Tutorial not found with ID: " + id)))
				.flatMap(entity -> tutorialRepository.findById(id).map(tutorialMapper::toModel));

	}

	public Mono<Tutorial> save(Mono<Tutorial> createTutorialRequest) {
		log.info("TutorialService save() method called with createTutorialRequest {}", createTutorialRequest);

		Mono<TutorialDomain> tutDomain = createTutorialRequest.map(tutorialMapper::toDomain);

		return tutDomain.flatMap(tutorialRepository::save).map(tutorialMapper::toModel);
	}

	public Mono<Tutorial> update(int id, Mono<Tutorial> updateMono) {
		log.info("TutorialService update() method called with id {} updateRequest {} ", id, updateMono);

		return updateMono.flatMap(model -> {
			// Retrieve existing entity or handle not found scenario
			return tutorialRepository.findById(id)
					.switchIfEmpty(Mono.error(new TutorialNotFoundException("Tutorial not found with ID: " + id)))
					.flatMap(existingEntity -> {
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
		log.info("TutorialService deleteById() method called with id {} ", id);

		return tutorialRepository.existsById(id).flatMap(exists -> {
			if (exists) {
				return tutorialRepository.deleteById(id);
			} else {
				return Mono.error(new TutorialNotFoundException("Tutorial not found with ID: " + id));
			}
		});

	}

	public Mono<Void> deleteAll() {
		log.info("TutorialService deleteAll() method called");

		return tutorialRepository.deleteAll();
	}

	public Flux<Tutorial> findByPublished(boolean isPublished) {
		log.info("TutorialService findByPublished() method called with isPublished {} " + isPublished);

		return tutorialRepository.findByPublished(isPublished).map(tutorialMapper::toModel);
	}
}
