package com.formacionbdi.springboot.app.gateway.springbootserviciogatewayserv.filters.factory;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class EjemploGatewayFilterFactory extends AbstractGatewayFilterFactory<EjemploGatewayFilterFactory.Configuracion> {

  private final Logger logger = LoggerFactory.getLogger(EjemploGatewayFilterFactory.class);

  public EjemploGatewayFilterFactory() {
    super(Configuracion.class);
  }

  @Override
  public List<String> shortcutFieldOrder() {
    return Arrays.asList("mensaje", "cookieNombre", "cookieValor");
  }

  @Override
  public String name() {
    return "EjemploCookie";
  }

  @Override
  public GatewayFilter apply(Configuracion config) {
    return (exchange, chain) -> {

      logger.info("Ejecutando PRE Gateway Filter Factory: " + config.mensaje);
      return chain.filter(exchange).then(Mono.fromRunnable(() -> {

        Optional.ofNullable(config.cookieValor).ifPresent(cookie -> {
          exchange.getResponse().addCookie(ResponseCookie.from(config.cookieNombre, cookie).build());
        });

        logger.info("Ejecutando POST Gateway Filter Factory: " + config.mensaje);
      }));
    };
  }

  @Getter
  @Setter
  public static class Configuracion {
    private String mensaje;
    private String cookieValor;
    private String cookieNombre;
  }
}
