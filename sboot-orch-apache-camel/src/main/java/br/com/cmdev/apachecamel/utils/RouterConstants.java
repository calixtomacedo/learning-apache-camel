package br.com.cmdev.apachecamel.utils;

public class RouterConstants {

    public static final String ROUTE_PERSON = "direct:getPerson";
    public static final String ROUTE_ADDRESS = "direct:getAddress";

    public static final String REQUEST_PARAM_PERSON_ID = "{idPerson}";
    public static final String REQUEST_PARAM_ADDRESS_ID = "{idAddress}";

    public static final String HEADER_PARAM_ID = "${header.id}";

    public static final String BOBY_ID_PERSON = "${body.idAddress}";
}

