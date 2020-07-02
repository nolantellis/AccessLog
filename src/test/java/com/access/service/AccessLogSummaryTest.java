package com.access.service;

import com.access.model.AccessLog;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class AccessLogSummaryTest {

    AccessLogSummaryService service;

    AccessLogBuilder builder;

    @Before
    public void setUp() throws Exception {
        builder = new AccessLogBuilder();
    }

    @Test
    public void testUniqueIp() {

//        Given
        List<AccessLog> accessLogs=builder
                .withIp("1.2.3.4")
                .withIp("1.2.3.4")
                .withIp("2.3.4.5")
                .build();
        service=new AccessLogSummaryService(accessLogs);

//        When
        Set<String> uniqueIps = service.getUniqueIps();

//        Then
        assertThat(uniqueIps).hasSize(2);
        assertThat(uniqueIps).containsExactly("1.2.3.4","2.3.4.5");

    }

    @Test
    public void testTop3Ip() {

//        Given
        List<AccessLog> accessLogs=builder
                .withIp("1.2.3.4")
                .withIp("1.2.3.4")
                .withIp("2.3.4.5")
                .withIp("2.3.4.5")
                .withIp("2.3.4.6")
                .withIp("2.3.4.6")
                .withIp("2.3.4.7")
                .build();
        service=new AccessLogSummaryService(accessLogs);

//        When
        Set<String> uniqueIps = service.getTopActiveIps(3);

//        Then
        assertThat(uniqueIps).hasSize(3);
        assertThat(uniqueIps).containsExactly("1.2.3.4","2.3.4.5","2.3.4.6");

    }

    @Test
    public void testTop3Url() {

//        Given
        List<AccessLog> accessLogs=builder
                .withUrl("http://abc")
                .withUrl("http://abc")
                .withUrl("http://abc")
                .withUrl("http://xyz")
                .withUrl("http://xyz")
                .withUrl("http://xyz")
                .withUrl("http://pqr")
                .withUrl("http://pqr")
                .withUrl("http://bad")
                .withUrl("http://bad")
                .build();
        service=new AccessLogSummaryService(accessLogs);

//        When
        Set<String> visitedUrls = service.getTopVisitedUrls(3);

//        Then
        assertThat(visitedUrls).hasSize(3);
        assertThat(visitedUrls).containsExactly("http://abc","http://xyz","http://bad");

    }
}
