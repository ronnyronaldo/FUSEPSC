package com.baustro.posicionconsolidada.Route;

import com.baustro.posicionconsolidada.Utils.PSCException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.baustro.posicionconsolidada.Utils.EncryptDecrypt256;
import com.baustro.posicionconsolidada.models.ConfigProperties;
import com.baustro.posicionconsolidada.models.FUSEPSCRespModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

//@Slf4j
@Component
public class ProcesadorException implements Processor {

    private static final Logger logger = LoggerFactory.getLogger(ProcesadorException.class);

    @Autowired
    public ConfigProperties configProperties;

    @Override
    public void process(Exchange exchange) throws Exception {

        String uuid = exchange.getProperty("UUID", String.class);
        logger.error(uuid + " - ProcesadorException.");

        PSCException ex = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, PSCException.class);

        FUSEPSCRespModel respObject = new FUSEPSCRespModel();

        if (ex != null) {
            logger.error(uuid + " - Tipo PSCException.");
            respObject.setCodRespuesta(ex.getCodigoError());
            respObject.setMsgRespuestaUsuario("Error al procesar Posicion Consolidada: " + ex.getMessage());
        } else {
            Throwable exT = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);
            logger.error(uuid + " - Tipo Throwable.");

            logger.error(uuid + " - Exception: " + exT.toString());
            String stacktrace = ExceptionUtils.getStackTrace(exT);
            logger.debug(uuid + " - Exception completa: " + stacktrace);

            respObject.setCodRespuesta(configProperties.getCodigosrespuesta().getERROR_GENERAL());
            respObject.setMsgRespuestaUsuario("Error al Posicion Consolidada: " + exT.getMessage());
        }

        // exchange.getIn().removeHeader("Authorization");
        exchange.getOut().removeHeader(Exchange.HTTP_METHOD);

        String respObjectJsonString = new ObjectMapper().writeValueAsString(respObject);

        exchange.getOut().setBody(respObjectJsonString);

        if (configProperties.EncriptarLog.equals("1")) {
            String tramaencriptada = EncryptDecrypt256.encryptAES(respObjectJsonString.toString(),
                    configProperties.SemillaToken);
                    logger.error(uuid + " - Trama de respuesta FUSE: " + tramaencriptada);
        } else {
            logger.error(uuid + " - Trama de respuesta FUSE: " + respObjectJsonString);
        }

    }

}
