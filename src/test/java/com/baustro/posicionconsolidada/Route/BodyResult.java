package com.baustro.posicionconsolidada.Route;


public class BodyResult {

    // Generico

    public int status;
    public String error;
    public String mensaje;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Messages messages;

    public Messages getMessages() {
        return messages;
    }

    public class Messages {
        public String error;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }

    public Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {
        // Token
        public String tkn_token;
        public String tkn_fecha_vencimiento;

        public String getTkn_token() {
            return tkn_token;
        }

        public void setTkn_token(String tkn_token) {
            this.tkn_token = tkn_token;
        }

        public String getTkn_fecha_vencimiento() {
            return tkn_fecha_vencimiento;
        }

        public void setTkn_fecha_vencimiento(String tkn_fecha_vencimiento) {
            this.tkn_fecha_vencimiento = tkn_fecha_vencimiento;
        }

        // Biometrico
        public String codigo;
        public String identificacion;
        public String url;

        public String getCodigo() {
            return codigo;
        }

        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }

        public String getIdentificacion() {
            return identificacion;
        }

        public void setIdentificacion(String identificacion) {
            this.identificacion = identificacion;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }

}
