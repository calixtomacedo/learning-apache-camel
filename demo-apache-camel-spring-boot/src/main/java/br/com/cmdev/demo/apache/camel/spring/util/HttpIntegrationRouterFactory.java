package br.com.cmdev.demo.apache.camel.spring.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.rest.RestConstants;
import org.apache.camel.model.RouteDefinition;

import static org.apache.camel.Exchange.CONTENT_TYPE;
import static org.apache.camel.builder.Builder.constant;


public class HttpIntegrationRouterFactory {

    public static final String APPLICATION_JSON = "application/json";
    public static final String HTTP_METHOD_GET = "GET";
    public static final String HTTP_METHOD_POST = "POST";
    public static final String BRIGDE_ENDPOINT = "bridgeEndpoint=true";

    public static void createPostRestJsonIntegration(ObjectMapper objectMapper, RouteDefinition route, String endpoint, Class<?> response) {
        route.removeHeader(Exchange.HTTP_URI)
                .setHeader(CONTENT_TYPE, constant(APPLICATION_JSON))
                .setHeader(RestConstants.ACCEPT, constant(APPLICATION_JSON))
                .setHeader(Exchange.HTTP_METHOD, constant(HTTP_METHOD_POST))
                .setHeader(Exchange.HTTP_QUERY, constant(BRIGDE_ENDPOINT))
                .toD(endpoint)
                .unmarshal(new JacksonDataFormat(objectMapper, response))
        ;
    }

    public static void createGetRestJsonIntegration(ObjectMapper objectMapper, RouteDefinition route, String endpoint, Class<?> response) {
        route.removeHeader(Exchange.HTTP_URI)
                .setHeader(CONTENT_TYPE, constant(APPLICATION_JSON))
                .setHeader(RestConstants.ACCEPT, constant(APPLICATION_JSON))
                .setHeader(Exchange.HTTP_METHOD, constant(HTTP_METHOD_GET))
                .setHeader(Exchange.HTTP_QUERY, constant(BRIGDE_ENDPOINT))
                .process(exchange -> {
                    System.out.println("Endpoint: "+endpoint);
                })
                .toD(endpoint)
                .process(exchange -> {
                    System.out.println("Endpoint: "+endpoint);
                })
        ;
    }

}
