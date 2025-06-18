package com.guido.guzman.msv.gatewaymvc.filters;

import jakarta.servlet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SampleGlobalFilter implements Filter, Ordered {
    private final Logger logger = LoggerFactory.getLogger(SampleGlobalFilter.class);

    @Override
    public int getOrder() {
        return 100;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("Calling SampleGlobalFilter::doFilter filter method");
        chain.doFilter(request, response);
    }
}


//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseCookie;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.util.Optional;
//
//@Component
//public class SampleGlobalFilter implements GlobalFilter, Ordered {
//    private final Logger logger = LoggerFactory.getLogger(SampleGlobalFilter.class);
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        //PRE:
//        logger.info("Ejecutando el filtro antes del request (PRE)");
//
//        exchange.getRequest().mutate().headers(h -> h.add("Token", "abcdefg")).build();
//
//        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//            logger.info("Ejecutando el filtro después del request (POST)");
//            String token = exchange.getRequest().getHeaders().getFirst("Token");
//            logger.info("token: {}", token);
//            Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("Token"))
//                    .ifPresent(value -> {
//                            logger.info("Token found: {}", value);
//                            exchange.getResponse().getHeaders().add("Token", value);
//                        }
//                    );
//            exchange.getResponse().getCookies().add("color", ResponseCookie.from("color", "red").build());
//            exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
//        }));
//    }
//
//    @Override
//    public int getOrder() {
//        return 100; // Order of execution, lower values are executed first
//    }
//}
