/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.access;

import com.access.model.AccessLog;
import com.access.service.AccessLogParser;
import com.access.service.AccessLogSummaryService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class AccessLogParsingLauncher {

    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.out.println("Please provide access log file");
            System.exit(0);
        }
        List<String> content = Files.readAllLines(Paths.get(args[0]));
        AccessLogParsingLauncher launcher = new AccessLogParsingLauncher();
        List<AccessLog> accessLogs = launcher.getAccessLogs(content);
        launcher.getAccessLogStats(accessLogs);
    }

    private void getAccessLogStats(List<AccessLog> accessLogs) {
        AccessLogSummaryService summaryService = new AccessLogSummaryService(accessLogs);
        System.out.println("Unique Ips = " + summaryService.getUniqueIps());
        System.out.println("Top Active Ips = " + summaryService.getTopActiveIps(3));
        System.out.println("Top Visited Urls = " + summaryService.getTopVisitedUrls(3));
    }

    private List<AccessLog> getAccessLogs(List<String> content) {
        AccessLogParser parser = new AccessLogParser();
        return content.stream().map(log -> parser.parse(log)).collect(Collectors.toList());
    }
}