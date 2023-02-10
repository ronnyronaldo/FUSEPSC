package com.baustro.posicionconsolidada.Route;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.baustro.posicionconsolidada.Utils.EncryptDecrypt;
import com.baustro.posicionconsolidada.Utils.EncryptDecrypt256;
import com.baustro.posicionconsolidada.models.ConfigProperties;
import com.baustro.posicionconsolidada.models.FITBANKPSCRespModel;
import com.baustro.posicionconsolidada.models.FUSEPSCRespModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

//***TEMPORAL***//
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.core.type.TypeReference;

@Slf4j
@Component
public class ProcesadorTramaOut implements Processor {

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
        String tramaOutFIT = exchange.getIn().getBody(String.class);

        log.info(uuid + " - Trama de respuesta FITBANK (ENC): " + tramaOutFIT);

        //***TEMPORAL***//
        if(fitSecurityActivated)
        {
            EncryptDecrypt.setGetPHRASE(llaveEncriptacion);
            tramaOutFIT = EncryptDecrypt.decryptAES(tramaOutFIT.getBytes());
        }

        String tramaencriptada = "";
        if (configProperties.getEncriptarLog()) {
            tramaencriptada = EncryptDecrypt256.encryptAES(tramaOutFIT,configProperties.getSemillaToken());
            log.info(uuid + " - Trama de respuesta FITBANK (claro): " + tramaencriptada);
        } else {
            log.info(uuid + " - Trama de respuesta FITBANK (claro): " + tramaOutFIT);
        }

        ObjectMapper mapper = new ObjectMapper();
        FITBANKPSCRespModel respFIT = mapper.readValue(tramaOutFIT, new TypeReference<FITBANKPSCRespModel>() {});

        String CodRespuestaFIT = respFIT.getCodRespuesta();

        FUSEPSCRespModel respFUSE = new FUSEPSCRespModel();
        respFUSE.setCodRespuesta(CodRespuestaFIT);
        respFUSE.setMsgRespuestaUsuario(respFIT.getMsgUsuario());
        respFUSE.setGUID(exchange.getProperty("GUID", String.class));

        if(CodRespuestaFIT.equals("000"))
        {
            FUSEPSCRespModel.DatosRespuesta datosResp = respFUSE.new DatosRespuesta();

            datosResp.setFecha_Contable(respFIT.getFcn());

            respFUSE.setDatosRespuesta(datosResp);
        }

        String respFUSEJsonString = new ObjectMapper().writeValueAsString(respFUSE);

        exchange.getOut().setBody(respFUSEJsonString);

        if (configProperties.getEncriptarLog()) {
            tramaencriptada = EncryptDecrypt256.encryptAES(respFUSEJsonString.toString(),configProperties.getSemillaToken());
            log.info(uuid + " - Trama de respuesta FUSE: " + tramaencriptada);
        } else {
            log.info(uuid + " - Trama de respuesta FUSE: " + respFUSEJsonString.toString());
        }


    }

}
