package br.com.cmdev.demo.apache.camel.spring.util;

import br.com.cmdev.demo.apache.camel.spring.error.DemoCamelException;

import java.util.HashMap;
import java.util.Map;

public record CamelRouteFactory<P, R>(String uri, String id, Class<P> parameterClazz, Class<R> responseClazz) {

    private static final Map<String, CamelRouteFactory<?, ?>> routes = new HashMap<>();

    public static <P, R> CamelRouteFactory<P, R> createDirect(String name, Class<P> parameterClazz, Class<R> responseClazz) {
        if (routes.get(name) != null) {
            throw new DemoCamelException("A rota de nome: " + name + " já esta em uso.");
        }
        String uri = "direct:".concat(name);
        var camelRoute = new CamelRouteFactory<>(uri, name, parameterClazz, responseClazz);
        routes.put(name, camelRoute);
        return camelRoute;
    }

    @Override
    public String toString() {
        return this.uri;
    }
}
