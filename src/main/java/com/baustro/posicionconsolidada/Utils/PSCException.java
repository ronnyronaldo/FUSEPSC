package com.baustro.posicionconsolidada.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import com.baustro.posicionconsolidada.models.ConfigProperties;

public class PSCException extends Exception{
    
    @Autowired
    public ConfigProperties configProperties;

    private final String codigoError;

    public String getCodigoError() {
        return codigoError;
    }

    public PSCException(String codigoError, String mensaje){
        super(mensaje);
        this.codigoError = codigoError;
    }

    public PSCException(String mensaje){
        super(mensaje);
        this.codigoError = configProperties.getCodigosrespuesta().getERROR_GENERAL();
    }

}
