package com.baustro.posicionconsolidada.Route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baustro.posicionconsolidada.models.ConfigProperties;
import com.baustro.posicionconsolidada.models.FUSEPSCReqModel;
import com.baustro.posicionconsolidada.models.FUSEPSCRespModel;

//import io.swagger.converter.ModelConverter;
//import io.swagger.converter.ModelConverters;

import org.springframework.context.annotation.Bean;
import org.apache.camel.component.http.HttpClientConfigurer;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import java.util.UUID;

@Component
public class PSCroute extends RouteBuilder {

        @Autowired
        public ConfigProperties configProperties;

        @Autowired
        ProcesadorTramaOut procesadorTramaOut;

        @Autowired
        ProcesadorTramaIn procesadorTramaIn;

        @Autowired
        ProcesadorException procesadorException;

        @Override
        public void configure() {

                /******** Procesar PSC ********/

                String contexto = "/consultar";

                rest(contexto).description("Consultar la Posicion Consolidada")
                                .consumes("application/json")
                                .produces("application/json")
                                .id("CONSULTA")
                                .post().description("la Posicion Consolidada")
                                .type(FUSEPSCReqModel.class)
                                .outType(FUSEPSCRespModel.class)

                                //.param().name("trama").type(RestParamType.body).description("TRAMA")
                                //.dataType("string").dataFormat("string").endParam()

                                .to("direct:procesarDebCred");

                onException(Exception.class)
                                .process(procesadorException)
                                .handled(true);

                from("direct:procesarDebCred").routeId("procesarDebCred")
                                .removeHeader(Exchange.HTTP_URI)
                                .removeHeader("CamelHttp")
                                .process(creatrUUID())
                                .process(procesadorTramaIn)
                                .to(configProperties.getFitbank().getFITURL()
                                                + "?throwExceptionOnFailure=false&httpClientConfigurer=noRetryHttpConfigurer")
                                .process(procesadorTramaOut);

        }


        @Bean
        public HttpClientConfigurer noRetryHttpConfigurer() {
                return (HttpClient client) -> {
                        client.getParams().setParameter("http.method.retry-handler",
                                        new DefaultHttpMethodRetryHandler(0, false));
                        client.setTimeout(configProperties.getFitbank().getTimeOut());
                        client.setConnectionTimeout(configProperties.getFitbank().getTimeOut());
                };
        }

        public Processor creatrUUID() {
                return (Exchange exchange) -> {
                        exchange.setProperty("UUID", generateUUID());
                };
        }

        public static String generateUUID() {
                try {
                        return UUID.randomUUID().toString();
                } catch (Exception e) {
                        return "UUID-no-definido";
                }
        }
}
