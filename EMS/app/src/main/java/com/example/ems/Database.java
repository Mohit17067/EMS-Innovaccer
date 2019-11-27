package com.example.ems;

import java.io.Serializable;
import java.util.HashMap;

public class Database implements Serializable {
    public host Host;
    public HashMap<String, visitor> visitors;

    public host getHost() {
        return Host;
    }

    public void setHost(host host) {
        Host = host;
    }

    public HashMap<String, visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(HashMap<String, visitor> visitors) {
        this.visitors = visitors;
    }

    public Database(){
        this.Host = host.getApp_host();
        this.visitors = new HashMap<>();
    }
}
