package com.baustro.posicionconsolidada.models;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "RespTransaccion")
public class FUSEPSCRespModel {

    private String CodRespuesta;
    private String MsgRespuestaUsuario;
    private String GUID;

    private DatosRespuesta DatosRespuesta;

    public FUSEPSCRespModel() {
    }

    public FUSEPSCRespModel(String codRespuesta, String msgRespuestaUsuario, String gUID,
                            FUSEPSCRespModel.DatosRespuesta datosRespuesta) {
        CodRespuesta = codRespuesta;
        MsgRespuestaUsuario = msgRespuestaUsuario;
        GUID = gUID;
        DatosRespuesta = datosRespuesta;
    }

    //@ApiModelProperty(value = "Codigo de respuesta", required = true)
    public String getCodRespuesta() {
        return CodRespuesta;
    }

    public void setCodRespuesta(String codRespuesta) {
        CodRespuesta = codRespuesta;
    }

    //@ApiModelProperty(value = "Mensaje de respuesta", required = true)
    public String getMsgRespuestaUsuario() {
        return MsgRespuestaUsuario;
    }

    public void setMsgRespuestaUsuario(String msgRespuestaUsuario) {
        MsgRespuestaUsuario = msgRespuestaUsuario;
    }

    public DatosRespuesta getDatosRespuesta() {
        return DatosRespuesta;
    }

    public void setDatosRespuesta(DatosRespuesta datosRespuesta) {
        DatosRespuesta = datosRespuesta;
    }

    public String getGUID() {
        return GUID;
    }

    public void setGUID(String gUID) {
        GUID = gUID;
    }

    public class DatosRespuesta {
        private String Fecha_Contable;
        private String ReferenciaUCIFIT;

        public String getFecha_Contable() {
            return Fecha_Contable;
        }

        public void setFecha_Contable(String fecha_Contable) {
            Fecha_Contable = fecha_Contable;
        }

        public String getReferenciaUCIFIT() {
            return ReferenciaUCIFIT;
        }

        public void setReferenciaUCIFIT(String referenciaUCIFIT) {
            ReferenciaUCIFIT = referenciaUCIFIT;
        }

    }

}
