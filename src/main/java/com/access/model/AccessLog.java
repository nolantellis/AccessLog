package com.access.model;

import java.time.LocalDateTime;

public class AccessLog {

    String ipAddress;

    String url;

    public AccessLog(String ipAddress, String url) {
        this.ipAddress = ipAddress;
        this.url = url;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
