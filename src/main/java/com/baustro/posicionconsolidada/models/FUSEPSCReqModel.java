package com.baustro.posicionconsolidada.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModel;

import javax.annotation.Generated;


//  Modelo
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "GUID",
        "IdTransaccion",
        "pIdentificacion",
        "CTA",
        "TC",
        "PR",
        "INV"
})


// Constructures
@Generated("jsonschema2pojo")
@ApiModel(value = "DatosTransaccion")
public class FUSEPSCReqModel {

    @JsonProperty("GUID")
    private String guid;
    @JsonProperty("IdTransaccion")
    private String idTransaccion;
    @JsonProperty("pIdentificacion")
    private String pIdentificacion;
    @JsonProperty("CTA")
    private String CTA;
    @JsonProperty("TC")
    private String TC;
    @JsonProperty("PR")
    private String PR;
    @JsonProperty("INV")
    private String INV;


    //Getters and Setters
    @JsonProperty("GUID")
    public String getGuid() {
        return guid;
    }

    @JsonProperty("GUID")
    public void setGuid(String guid) {
        this.guid = guid;
    }

    @JsonProperty("IdTransaccion")
    public String getIdTransaccion() {
        return idTransaccion;
    }

    @JsonProperty("IdTransaccion")
    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    @JsonProperty("pIdentificacion")
    public String getpIdentificacion() {
        return pIdentificacion;
    }

    @JsonProperty("pIdentificacion")
    public void setpIdentificacion(String pIdentificacion) {
        this.pIdentificacion = pIdentificacion;
    }

    @JsonProperty("CTA")
    public String getCTA() {
        return CTA;
    }

    @JsonProperty("CTA")
    public void setCTA(String CTA) {
        this.CTA = CTA;
    }

    @JsonProperty("TC")
    public String getTC() {
        return TC;
    }

    @JsonProperty("TC")
    public void setTC(String TC) {
        this.TC = TC;
    }

    @JsonProperty("PR")
    public String getPR() {
        return PR;
    }

    @JsonProperty("PR")
    public void setPR(String PR) {
        this.PR = PR;
    }

    @JsonProperty("INV")
    public String getINV() {
        return INV;
    }

    @JsonProperty("INV")
    public void setINV(String INV) {
        this.INV = INV;
    }

    // ToString


    @Override
    public String toString() {
        return "FUSEPSCReqModel{" +
                "guid='" + guid + '\'' +
                ", idTransaccion='" + idTransaccion + '\'' +
                ", pIdentificacion='" + pIdentificacion + '\'' +
                ", CTA='" + CTA + '\'' +
                ", TC='" + TC + '\'' +
                ", PR='" + PR + '\'' +
                ", INV='" + INV + '\'' +
                '}';
    }
}
