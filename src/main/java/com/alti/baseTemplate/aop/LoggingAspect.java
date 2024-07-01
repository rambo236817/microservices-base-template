package com.alti.baseTemplate.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@Aspect
@Component
@Slf4j
public class LoggingAspect {
	@Around("execution(* com.alti..*(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		log.info("Method {} called with arguments {}", joinPoint.getSignature().toShortString(),
				joinPoint.getArgs());

		Object result = joinPoint.proceed();

		long elapsedTime = System.currentTimeMillis() - start;
		log.info("Method {} executed in {} ms", joinPoint.getSignature().toShortString(), elapsedTime);

		if (result instanceof Mono) {
			return ((Mono<?>) result).doOnTerminate(() -> log.info("Method {} completed in {} ms",
					joinPoint.getSignature().toShortString(), elapsedTime));
		} else {
			return result;
		}
	}
}
