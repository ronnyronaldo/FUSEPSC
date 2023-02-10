package com.baustro.posicionconsolidada.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.annotation.Generated;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"tip",
"sub",
"trn",
"can",
"ctl"
})
@Generated("jsonschema2pojo")
public class FITBANKPSCReqModel {

@JsonProperty("tip")
private String tip;
@JsonProperty("sub")
private String sub;
@JsonProperty("trn")
private String trn;
@JsonProperty("can")
private String can;

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


}
