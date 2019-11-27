package com.example.ems;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;



public class visitor implements Serializable {
    private String v_name;
    private String v_contact;
    private String v_email;
    private String v_checkin;
    private String v_checkout;

    public visitor(String v_name, String v_email, String v_contact){
        this.v_name = v_name;
        this.v_contact = v_contact;
        this.v_email = v_email;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        this.v_checkin = formatter.format(date);
    }

    public String getV_name() {
        return v_name;
    }

    public void setV_name(String v_name) {
        this.v_name = v_name;
    }

    public String getV_contact() {
        return v_contact;
    }

    public void setV_contact(String v_contact) {
        this.v_contact = v_contact;
    }

    public String getV_email() {
        return v_email;
    }

    public void setV_email(String v_email) {
        this.v_email = v_email;
    }

    public String getV_checkin() {
        return v_checkin;
    }

    public void setV_checkin(String v_checkin) {
        this.v_checkin = v_checkin;
    }

    public String getV_checkout() {
        return v_checkout;
    }

    public void setV_checkout() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        this.v_checkout = formatter.format(date);
    }
}
