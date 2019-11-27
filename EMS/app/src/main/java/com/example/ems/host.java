package com.example.ems;

import java.io.Serializable;

public class host implements Serializable {

    private static host app_host = null;
    private String h_name;
    private String h_email;
    private String h_contact;
    private String h_pswd;

    private host(){
        this.h_name = "h_name";
        this.h_contact = "9999999999";
        this.h_email = "mohit17067@iiitd.ac.in";
        this.h_pswd = "host";
    }

    public static host getApp_host() {
        if (app_host == null)
            app_host = new host();
        return app_host;
    }



    public String getH_name() {
        return h_name;
    }

    public void setH_name(String h_name) {
        this.h_name = h_name;
    }

    public String getH_email() {
        return h_email;
    }

    public void setH_email(String h_email) {
        this.h_email = h_email;
    }

    public String getH_contact() {
        return h_contact;
    }

    public void setH_contact(String h_contact) {
        this.h_contact = h_contact;
    }

    public String getH_pswd() {
        return h_pswd;
    }

    public void setH_pswd(String h_pswd) {
        this.h_pswd = h_pswd;
    }
}
