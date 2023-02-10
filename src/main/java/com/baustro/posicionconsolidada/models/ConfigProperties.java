package com.baustro.posicionconsolidada.models;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class ConfigProperties {
    
    public FITBANK fitbank = new FITBANK();
    public DEBITOCREDITO debitocredito = new DEBITOCREDITO();
    public Boolean EncriptarLog;
    public String SemillaToken;
    public CodigosRespuesta codigosrespuesta =  new CodigosRespuesta();


    public FITBANK getFitbank() {
        return fitbank;
    }

    public void setFitbank(FITBANK fitbank) {
        this.fitbank = fitbank;
    }

    public DEBITOCREDITO getDebitocredito() {
        return debitocredito;
    }

    public void setDebitocredito(DEBITOCREDITO debitocredito) {
        this.debitocredito = debitocredito;
    }

    public Boolean getEncriptarLog() {
        return EncriptarLog;
    }

    public void setEncriptarLog(Boolean encriptarLog) {
        EncriptarLog = encriptarLog;
    }

    
    public CodigosRespuesta getCodigosrespuesta() {
        return codigosrespuesta;
    }

    public void setCodigosrespuesta(CodigosRespuesta codigosrespuesta) {
        this.codigosrespuesta = codigosrespuesta;
    }

    public String getSemillaToken() {
        return SemillaToken;
    }

    public void setSemillaToken(String semillaToken) {
        SemillaToken = semillaToken;
    }

    public class CodigosRespuesta {

        public String ERROR_GENERAL;
        public String errCampoNoEspecificado;
        public String errCampoConFormatoIncorrecto;
        public String errValorCampoIncorrecto;

        public String getERROR_GENERAL() {
            return ERROR_GENERAL;
        }

        public void setERROR_GENERAL(String eRROR_GENERAL) {
            ERROR_GENERAL = eRROR_GENERAL;
        }


        public String getErrCampoNoEspecificado() {
            return errCampoNoEspecificado;
        }

        public void setErrCampoNoEspecificado(String errCampoNoEspecificado) {
            this.errCampoNoEspecificado = errCampoNoEspecificado;
        }

                
        public String getErrCampoConFormatoIncorrecto() {
            return errCampoConFormatoIncorrecto;
        }

        public void setErrCampoConFormatoIncorrecto(String errCampoConFormatoIncorrecto) {
            this.errCampoConFormatoIncorrecto = errCampoConFormatoIncorrecto;
        }


        public String getErrValorCampoIncorrecto() {
            return errValorCampoIncorrecto;
        }

        public void setErrValorCampoIncorrecto(String errValorCampoIncorrecto) {
            this.errValorCampoIncorrecto = errValorCampoIncorrecto;
        }

        
    }
    


    public class FITBANK{
        public String FITURL;
        public int TimeOut;
        public String llaveUCIFIT;

        public String getFITURL() {
            return FITURL;
        }
        public void setFITURL(String fITURL) {
            FITURL = fITURL;
        }
        
        public int getTimeOut() {
            return TimeOut;
        }
        public void setTimeOut(int timeOut) {
            TimeOut = timeOut;
        }
        public String getLlaveUCIFIT() {
            return llaveUCIFIT;
        }
        public void setLlaveUCIFIT(String llaveUCIFIT) {
            this.llaveUCIFIT = llaveUCIFIT;
        }

    }

    public class DEBITOCREDITO {
        public String tip;
        public String subDebitoCredito;
        public String trnDebitoCredito;
        public String can;
        public String tipREV;
        public String conceptoSCI1;
        public String conceptoSPI1;
        public String conceptoDevolucion;
        public String getTip() {
            return tip;
        }
        public void setTip(String tip) {
            this.tip = tip;
        }
        public String getSubDebitoCredito() {
            return subDebitoCredito;
        }
        public void setSubDebitoCredito(String subDebitoCredito) {
            this.subDebitoCredito = subDebitoCredito;
        }
        public String getTrnDebitoCredito() {
            return trnDebitoCredito;
        }
        public void setTrnDebitoCredito(String trnDebitoCredito) {
            this.trnDebitoCredito = trnDebitoCredito;
        }
        public String getCan() {
            return can;
        }
        public void setCan(String can) {
            this.can = can;
        }
        public String getTipREV() {
            return tipREV;
        }
        public void setTipREV(String tipREV) {
            this.tipREV = tipREV;
        }
        public String getConceptoSCI1() {
            return conceptoSCI1;
        }
        public void setConceptoSCI1(String conceptoSCI1) {
            this.conceptoSCI1 = conceptoSCI1;
        }
        public String getConceptoSPI1() {
            return conceptoSPI1;
        }
        public void setConceptoSPI1(String conceptoSPI1) {
            this.conceptoSPI1 = conceptoSPI1;
        }
        public String getConceptoDevolucion() {
            return conceptoDevolucion;
        }
        public void setConceptoDevolucion(String conceptoDevolucion) {
            this.conceptoDevolucion = conceptoDevolucion;
        }
    }

    
}
