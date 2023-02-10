package com.baustro.posicionconsolidada.Swagger;

import org.springframework.context.annotation.Configuration;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

@Configuration
public class CamelRouteConfig extends RouteBuilder {
    
    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.off)
                //Enable swagger endpoint.
                .apiContextPath("/api-doc") //swagger endpoint path
                .apiContextRouteId("swagger") //id of route providing the swagger endpoint
                //Swagger properties
                .contextPath("/api/v1") //base.path swagger property; use the mapping path set for CamelServlet
                .apiProperty("api.description", "FUSE para POSICION CONSOLIDAD")
                .apiProperty("api.title", "PSC (Posicion Consolidada)")
                .apiProperty("api.version", "1.0")
                .apiProperty("api.contact.name", "BANCO AUSTRO")
                .apiProperty("host", "") //by default 0.0.0.0
                .apiProperty("port", "8080")
                .apiProperty("schemes", "http")
                .apiProperty("consumes", "application/json")
                .apiProperty("produces", "application/json")

        ;
    }

}