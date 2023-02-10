package com.baustro.posicionconsolidada.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.annotation.Generated;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"usr",
"sid",
"tip",
"sub",
"trn",
"can",
"fcn",
"ctl",
"tbl",
"codRespuesta",
"msgUsuario"
})
@Generated("jsonschema2pojo")
public class FITBANKPSCRespModel {

@JsonProperty("usr")
private String usr;
@JsonProperty("sid")
private String sid;
@JsonProperty("tip")
private String tip;
@JsonProperty("sub")
private String sub;
@JsonProperty("trn")
private String trn;
@JsonProperty("can")
private String can;
@JsonProperty("fcn")
private String fcn;

//@JsonProperty("tbl")
//@JsonIgnore
//private TBL tbl;

/*
public TBL getTbl() {
    return tbl;
}

public void setTbl(TBL tbl) {
    this.tbl = tbl;
}
*/

@JsonProperty("codRespuesta")
private String codRespuesta;
@JsonProperty("msgUsuario")
private String msgUsuario;

@JsonProperty("usr")
public String getUsr() {
return usr;
}

@JsonProperty("usr")
public void setUsr(String usr) {
this.usr = usr;
}

@JsonProperty("sid")
public String getSid() {
return sid;
}

@JsonProperty("sid")
public void setSid(String sid) {
this.sid = sid;
}

@JsonProperty("tip")
public String getTip() {
return tip;
}

@JsonProperty("tip")
public void setTip(String tip) {
this.tip = tip;
}

@JsonProperty("sub")
public String getSub() {
return sub;
}

@JsonProperty("sub")
public void setSub(String sub) {
this.sub = sub;
}

@JsonProperty("trn")
public String getTrn() {
return trn;
}

@JsonProperty("trn")
public void setTrn(String trn) {
this.trn = trn;
}

@JsonProperty("can")
public String getCan() {
return can;
}

@JsonProperty("can")
public void setCan(String can) {
this.can = can;
}

@JsonProperty("fcn")
public String getFcn() {
return fcn;
}

@JsonProperty("fcn")
public void setFcn(String fcn) {
this.fcn = fcn;
}

@JsonProperty("codRespuesta")
public String getCodRespuesta() {
return codRespuesta;
}

@JsonProperty("codRespuesta")
public void setCodRespuesta(String codRespuesta) {
this.codRespuesta = codRespuesta;
}

@JsonProperty("msgUsuario")
public String getMsgUsuario() {
return msgUsuario;
}

@JsonProperty("msgUsuario")
public void setMsgUsuario(String msgUsuario) {
this.msgUsuario = msgUsuario;
}


public class TBL {

    
}

}