package br.com.cmdev.demo.apache.camel.spring.router;

public class RouterConstants {

    private RouterConstants() {}

    /**
     * Sessão de itens das rotas
     */
    public static final Integer MAXIMUM_REDELIVERIES = 3;
    public static final Integer REDELIVERIES_DELAY = 1000;
    public static final String MAXIMUM_REDELIVERIES_EXCEEDED = "camelMaximumRedeliveriesExceeded";


    public static final String ERRO_ROUTER_NAME = "direct:error-router";
    public static final String ERRO_ROUTER_ID = "error-router";
    public static final String ERROR_ROUTER_DESCRIPTION = "ERROR";

    public static final String GET_PRODUCT_ROUTER = "/product/{productId}";
    public static final String ID_PRODUCT_ROUTER = "product-id";
    public static final String TAG_PRODUCT_ROUTER = "product";
    public static final String DESCRIPTION_PRODUCT_ROUTER = "Product by Apache Camel";

    public static final String HOME_ROUTER_NAME = "direct:home-router";
    public static final String HOME_ROUTER_ID = "home-router";

    /**
     *
     */
    public static String MEDIA_TYPE_APPLICATION_JSON = "application/json";

    /**
     * Sessão de URIs
     */
    public static final String PRODUCT_URI = "/v1/api/product/";


    /**
     * Sessão de headers
     */
    public static final String PROP_HEADER_PRODUCT_ID = "${header.productId}";

    /**
     * Sessão de properties
     */
    public static final String PROP_PARAM_PRODUCT_ID = "productId";
}
