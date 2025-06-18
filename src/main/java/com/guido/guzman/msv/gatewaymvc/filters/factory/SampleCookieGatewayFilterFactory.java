package com.guido.guzman.msv.gatewaymvc.filters.factory;

public class SampleCookieGatewayFilterFactory {

}

//import lombok.Getter;
//import lombok.Setter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.http.ResponseCookie;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class SampleCookieGatewayFilterFactory extends AbstractGatewayFilterFactory<SampleCookieGatewayFilterFactory.ConfigurationCookie> {
//    private final Logger logger = LoggerFactory.getLogger(SampleCookieGatewayFilterFactory.class);
//
//    public SampleCookieGatewayFilterFactory() {
//        super(ConfigurationCookie.class);
//    }
//
//    @Override
//    public String name() {
//        return "EjemploCookie";
//    }
//
//    @Override
//    public List<String> shortcutFieldOrder() {
//        return Arrays.asList("message", "name", "value");
//    }
//
//    @Override
//    public GatewayFilter apply(ConfigurationCookie config) {
//        return new OrderedGatewayFilter((exchange, chain) -> {
//            //PRE: antes del request
//            logger.info("ejecutando pre gateway filter factory: ", config.getMessage());
//            return chain.filter(exchange).then(/*POST*/Mono.fromRunnable(() -> {
//                Optional.ofNullable(config.value).ifPresent(cookie -> {
//                    exchange.getResponse().addCookie(ResponseCookie.from(config.getName(), cookie).build());
//                });
//                logger.info("ejecutando post gateway filter factory: {}", config.getMessage());
//            }));
//        }, 100);
//    }
//
//    @Getter
//    @Setter
//    public static class ConfigurationCookie {
//        private String name;
//        private String value;
//        private String message;
//    }
//}
