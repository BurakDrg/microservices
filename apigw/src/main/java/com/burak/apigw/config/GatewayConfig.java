package com.burak.apigw.config;

import com.burak.apigw.filter.JwtAuthenticationFilter;
import com.burak.apigw.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@AllArgsConstructor
public class GatewayConfig {

	private final JwtAuthenticationFilter filter;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("customer", r -> r.path("/customer/**").filters(f -> f.filter(filter)).uri("lb://CUSTOMER"))
				.route("book", r -> r.path("/book/**").filters(f -> f.filter(filter)).uri("lb://BOOK"))
				.route("order", r -> r.path("/order/**").filters(f -> f.filter(filter)).uri("lb://ORDER"))
				.route("statistics", r -> r.path("/statistics/**").filters(f -> f.filter(filter)).uri("lb://STATISTICS"))
				.build();
	}

}
