package com.alti.baseTemplate.controllers;

import org.openapitools.api.TutorialsApi;
import org.openapitools.model.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.alti.baseTemplate.service.impl.TutorialService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TutorialController implements TutorialsApi {
	
	@Autowired
	TutorialService tutorialService;

	@Override
	public Flux<Tutorial> getAllTutorials(String title, final ServerWebExchange exchange) {
		if (title == null)
			return tutorialService.findAll();
		else
			return tutorialService.findByTitleContaining(title);
	}

	@Override
	public Mono<Tutorial> getTutorialById(Integer id, final ServerWebExchange exchange) {
		return tutorialService.findById(id);
	}

	@Override
	public Mono<Tutorial> createTutorial(Mono<Tutorial> createTutorialRequest, final ServerWebExchange exchange) {

		return tutorialService.save(createTutorialRequest);
	}

	@Override
	public Mono<Tutorial> updateTutorialById(Integer id, Mono<Tutorial> updateTutorialRequest,
			final ServerWebExchange exchange) {

		return tutorialService.update(id, updateTutorialRequest);
	}

	@Override
	public Mono<Void> deleteTutorial(Integer id, final ServerWebExchange exchange) {
		return tutorialService.deleteById(id);
	}

	@Override
	public Mono<Void> deleteAllTutorials(final ServerWebExchange exchange) {
		return tutorialService.deleteAll();
	}

	@Override
	public Flux<Tutorial> findByPublished(final ServerWebExchange exchange) {
		return tutorialService.findByPublished(true);
	}
}
