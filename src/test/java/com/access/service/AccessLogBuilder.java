package com.access.service;

import com.access.model.AccessLog;

import java.util.ArrayList;
import java.util.List;

public class AccessLogBuilder {

    List<AccessLog> accessLogs=new ArrayList();


    public AccessLogBuilder withIp(String ip) {
        accessLogs.add(new AccessLog(ip,null));
        return this;
    }

    public AccessLogBuilder withUrl(String url) {
        accessLogs.add(new AccessLog(null,url));
        return this;
    }

    public List<AccessLog> build()
    {
        return accessLogs;
    }
}
