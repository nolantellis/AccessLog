package com.access.service;

import com.access.model.AccessLog;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccessLogParserTest {

    AccessLogParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new AccessLogParser();
    }

    @Test
    public void testParsingLine() {

//Given
        String line = "177.71.128.21 - - [10/Jul/2018:22:21:28 +0200] \"GET /intranet-analytics/ HTTP/1.1\" 200 3574 \"-\" \"Mozilla/5.0 (X11; U; Linux x86_64; fr-FR) AppleWebKit/534.7 (KHTML, like Gecko) Epiphany/2.30.6 Safari/534.7\"";

//When
        AccessLog log = parser.parse(line);

//Then
        assertThat(log.getIpAddress()).isEqualTo("177.71.128.21");
        assertThat(log.getUrl()).isEqualTo("/intranet-analytics/");
    }
}
