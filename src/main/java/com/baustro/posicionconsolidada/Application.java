package com.baustro.posicionconsolidada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.baustro.posicionconsolidada.models.ConfigProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
public class Application {

    /**
     * A main method to start this application.
     */
    public static void main(String[] args) {
        log.info("Iniciando");
        SpringApplication.run(Application.class, args);
        log.info("*/ Running");
    }
}
