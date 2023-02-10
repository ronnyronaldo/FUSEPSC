package com.baustro.posicionconsolidada.Route;

import com.baustro.posicionconsolidada.Utils.PSCException;
import com.baustro.posicionconsolidada.models.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import org.springframework.stereotype.Component;

//***TEMPORAL***//
import com.baustro.posicionconsolidada.Utils.EncryptDecrypt;

import com.baustro.posicionconsolidada.Utils.EncryptDecrypt256;
import com.baustro.posicionconsolidada.models.FUSEPSCReqModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.type.TypeReference;

import org.springframework.beans.factory.annotation.Value;


@Slf4j
@Component
public class ProcesadorTramaIn implements Processor {

    @Autowired
    public ConfigProperties configProperties;

    //***TEMPORAL***//
    @Value("${fitSecurityActivated}")
    private boolean fitSecurityActivated;
    @Value("${llaveEncriptacion}")
    private String llaveEncriptacion;

    @Override
    public void process(Exchange exchange) throws Exception {

        String uuid = exchange.getProperty("UUID", String.class);
        String trama_In = exchange.getIn().getBody(String.class);
        log.info(uuid + " -------------------------------------------");

        String tramaencriptada = "";
        if (configProperties.EncriptarLog.equals("1")) {
            tramaencriptada = EncryptDecrypt256.encryptAES(trama_In, configProperties.SemillaToken);
            log.info(uuid + " - Trama de entrada FUSE: " + tramaencriptada);
        } else {
            log.info(uuid + " - Trama de entrada FUSE: " + trama_In);
        }

        /**/
        HttpServletRequest request = exchange.getIn().getBody(HttpServletRequest.class);
        String remoteAddress = request.getRemoteAddr();
        log.info(uuid + " - remoteAddress: " + remoteAddress); // IP del Ingress
        /**/
        // log.info(uuid + " - " + exchange.getIn().getHeaders());
        log.info(uuid + " - " + exchange.getIn().getHeader("CamelHttpServletRequest"));
        // .log("Ruta: ${in.headers.CamelHttpServletRequest}")

        ObjectMapper mapper = new ObjectMapper();
        FUSEPSCReqModel tramaFUSE = mapper.readValue(trama_In, new TypeReference<FUSEPSCReqModel>() {
        });

        ValidarCampos(tramaFUSE);

        FITBANKPSCReqModel tramaFitbank = ArmarTramaFIT(tramaFUSE);

        exchange.setProperty("GUID", tramaFUSE.getGuid());

        String tramaFitbankJsonString = new ObjectMapper().writeValueAsString(tramaFitbank);


        if(fitSecurityActivated)
        {
            EncryptDecrypt.setGetPHRASE(llaveEncriptacion);
            String tramaFitbankENC = EncryptDecrypt.encryptAES(tramaFitbankJsonString);
            exchange.getOut().setBody(tramaFitbankENC);
        }
        else{
            exchange.getOut().setBody(tramaFitbankJsonString);
        }

        if (configProperties.EncriptarLog.equals("1")) {
            tramaencriptada = EncryptDecrypt256.encryptAES(tramaFitbankJsonString.toString(), configProperties.SemillaToken);
            log.info(uuid + " - Trama al FITBANK: " + tramaencriptada);
        } else {
            log.info(uuid + " - Trama al FITBANK: " + tramaFitbankJsonString.toString());
        }

    }

    private FITBANKPSCReqModel ArmarTramaFIT(FUSEPSCReqModel tramaFUSE) {

        String idTransaccion = tramaFUSE.getIdTransaccion();

        FITBANKPSCReqModel tramaFitbank = new FITBANKPSCReqModel();

        tramaFitbank.setTip(configProperties.getDebitocredito().getTip());
        tramaFitbank.setSub(configProperties.getDebitocredito().getSubDebitoCredito());
        tramaFitbank.setTrn(configProperties.getDebitocredito().getTrnDebitoCredito());
        tramaFitbank.setCan(configProperties.getDebitocredito().getCan());

        return tramaFitbank;
    }

    public void ValidarCampos(FUSEPSCReqModel tramaFUSE) throws PSCException {

        // Parametros requeridos

        String GUID = tramaFUSE.getGuid();
        if (GUID == null || GUID.trim().isEmpty()) {
            throw new PSCException(configProperties.getCodigosrespuesta().getErrCampoNoEspecificado(),
                    String.format("Parametro %s requerido", "GUID"));
        }

        String IdTransaccion = tramaFUSE.getIdTransaccion();
        if (IdTransaccion == null || IdTransaccion.trim().isEmpty()) {
            throw new PSCException(configProperties.getCodigosrespuesta().getErrCampoNoEspecificado(),
                    String.format("Parametro %s requerido", "IdTransaccion"));
        }


        if (!tramaFUSE.getIdTransaccion().equals("001") && !tramaFUSE.getIdTransaccion().equals("002"))
            throw new PSCException(configProperties.getCodigosrespuesta().getErrValorCampoIncorrecto(),
                    String.format("El argumento %s tiene valor incorrecto", "IdTransaccion"));

    }

}
