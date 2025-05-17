package com.eazybytes.gatewayserver;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p -> p
						.path("/ngleanhvu/accounts/**")
						.filters(f -> f.rewritePath("/ngleanhvu/accounts/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								.circuitBreaker(config -> config.setName("accountsCircuitBreaker")
										.setFallbackUri("forward:/contactSupport")))
						.uri("lb://ACCOUNTS"))
				.route(p -> p
						.path("/ngleanhvu/cards/**")
						.filters(f -> f.rewritePath("/ngleanhvu/cards/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								.requestRateLimiter(config -> config
										.setRateLimiter(redisRateLimiter())
										.setKeyResolver(keyResolver())))
						.uri("lb://CARDS"))

				.route(p -> p
						.path("/ngleanhvu/loans/**")
						.filters(f -> f.rewritePath("/ngleanhvu/loans/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								.retry(retryConfig -> retryConfig
										.setRetries(3)
										.setMethods(HttpMethod.GET)
										.setBackoff(Duration.ofSeconds(100), Duration.ofSeconds(1000),2,true))
						)
						.uri("lb://LOANS"))
				.build();
	}

	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
				.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
				.timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build()).build());
	}

	@Bean
	public KeyResolver keyResolver() {
		return exchange -> Mono
				.justOrEmpty(exchange.getRequest()
				.getHeaders()
				.getFirst("user"))
				.defaultIfEmpty("anonymous");
	}

	@Bean
	public RedisRateLimiter redisRateLimiter() {
		return new RedisRateLimiter(1,1,1);
	}
}
