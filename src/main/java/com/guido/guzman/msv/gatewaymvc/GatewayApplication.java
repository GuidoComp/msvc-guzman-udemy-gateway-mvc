package com.guido.guzman.msv.gatewaymvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates.path;
import static org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions.lb;
import static org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions.circuitBreaker;
import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.stripPrefix;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	RouterFunction<ServerResponse> routerConfig() {
		return route("products")
				.route(path("/api/products/**"), http())
				.filter((request, next) -> {
					ServerRequest requestModified = ServerRequest.from(request)
							.header("message-request", "Message in request")
							.build();
					ServerResponse response = next.handle(requestModified);
					response.headers().add("message-response", "Message in response");
					return response;
				})
				.filter(lb("products"))
				.filter(circuitBreaker(config -> config
						.setId("products")
						.setStatusCodes("500")
						.setFallbackPath("forward:/api/items/5"))
				)
				.before(stripPrefix(2))
				.build();
	}
}
