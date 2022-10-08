package com.example.emergon;

public class hospital_dataholder {

    String hos_name, hos_reg, hos_pass;

    public hospital_dataholder(String Hos_name,String Hos_pass,String Hos_reg){
        hos_name = Hos_name;
        hos_pass = Hos_pass;
        hos_reg = Hos_reg;

    }
    public String getHos_name() {
        return hos_name;
    }

    public void setHos_name(String Hos_name) {
        hos_name = Hos_name;
    }

    public String getHos_pass() {
        return hos_pass;
    }

    public void setHos_pass(String Hos_pass) {
        hos_pass = Hos_pass;
    }

    public String getHos_reg() {
        return hos_reg;
    }

    public void setHos_reg(String Hos_reg) {
        hos_reg = Hos_reg;
    }

}
