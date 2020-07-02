package com.access.service;

import com.access.model.AccessLog;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AccessLogSummaryService {

    List<AccessLog> accessLogs;

    public AccessLogSummaryService(List<AccessLog> accessLogs) {
        this.accessLogs = accessLogs;
    }

    public Set<String> getUniqueIps() {

        return accessLogs.stream()
                .map(AccessLog::getIpAddress)
                .collect(Collectors.toSet());
    }

    public Set<String> getTopActiveIps(int count) {

        return getTopValues(AccessLog::getIpAddress, count);
    }

    public Set<String> getTopVisitedUrls(int count) {

        return getTopValues(AccessLog::getUrl, count);
    }


    private Set<String> getTopValues(Function<AccessLog, String> keyFunction, int count) {

        Map<String, Long> keyMap = accessLogs.stream()
                .collect(Collectors.groupingBy(keyFunction, Collectors.counting()));

        return keyMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(Map.Entry::getKey).limit(count)
                .collect(LinkedHashSet::new, LinkedHashSet::add,LinkedHashSet::addAll);

    }


}
