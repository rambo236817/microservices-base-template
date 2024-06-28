package com.alti.baseTemplate.exception;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.error.ErrorAttributeOptions.Include;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;

import reactor.core.publisher.Mono;

@Component
@Order(-2)
public class GlobalExceptionHandler extends AbstractErrorWebExceptionHandler {

	private final ObjectProvider<ViewResolver> viewResolvers;
	private final ServerCodecConfigurer serverCodecConfigurer;

	public GlobalExceptionHandler(ObjectProvider<ViewResolver> viewResolvers,
			ServerCodecConfigurer serverCodecConfigurer, ErrorAttributes errorAttributes,
			WebProperties.Resources resources, ApplicationContext applicationContext) {
		super(errorAttributes, resources, applicationContext);
		this.viewResolvers = viewResolvers;
		this.serverCodecConfigurer = serverCodecConfigurer;
		super.setMessageWriters(serverCodecConfigurer.getWriters());
		super.setMessageReaders(serverCodecConfigurer.getReaders());
		super.setViewResolvers(viewResolvers.orderedStream().collect(Collectors.toList()));
	}

	@Override
	protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {

		return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
	}

	private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
		Set<Include> options = new HashSet<Include>();
		options.add(Include.MESSAGE);
		options.add(Include.EXCEPTION);
		options.add(Include.PATH);
		
		ErrorAttributeOptions attr = ErrorAttributeOptions.of(options);
		Map<String, Object> errorPropertiesMap = getErrorAttributes(request, attr);

		return ServerResponse.badRequest().body(BodyInserters.fromValue(errorPropertiesMap));
	}

}
