package com.access.service;

import com.access.model.AccessLog;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
String regex1 = "^([\\d.]+)";                         // Client IP
        String regex2 = " (\\S+)";                             // -
        String regex3 = " (\\S+)";                             // -
        String regex4 = " \\[([\\w:/]+\\s[+\\-]\\d{4})\\]"; // Date
        String regex5 = " \"(.+?)\"";                       // request method and url
        String regex6 = " (\\d{3})";                           // HTTP code
        String regex7 = " (\\d+|(.+?))";                     // Number of bytes
        String regex8 = " \"([^\"]+|(.+?))\"";                 // Referer
        String regex9 = " \"([^\"]+|(.+?))\"";                // Agent
* */
public class AccessLogParser {

    private static final StringBuilder regexBuilder = new StringBuilder();

    private final static Pattern pattern;
    private static final int IP_GROUP = 1;
    private static final int URL_GROUP = 5;

    static {
        regexBuilder.append("^([\\d.]+)") // IpAddress
                .append(" (\\S+)") // -
                .append(" (\\S+)") // -
                .append(" \\[([\\w:/]+\\s[+\\-]\\d{4})\\]") // TimeStamp
                .append(" \"(.+?)\"") // http request url
                .append(" (\\d{3})") //code
                .append(" (\\d+|(.+?))") // number of bytes
                .append(" \"([^\"]+|(.+?))\"") // ref
                .append(" \"([^\"]+|(.+?))\""); // agent
        pattern = Pattern.compile(regexBuilder.toString());

    }

    public AccessLog parse(String line) {
        if (Objects.isNull(line)) {
            return null;
        }
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return new AccessLog(matcher.group(IP_GROUP), matcher.group(URL_GROUP).split(" ")[1]);

        }
        return null;
    }
}
