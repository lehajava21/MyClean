package com.leha.myclean.data;

public class Message {
    private String type;
    private String mes;

    public Message(String type,String mes)
    {
        this.type = type;
        this.mes = mes;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
