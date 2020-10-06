package com.ezbob.shuffle.config;

import com.ezbob.shuffle.services.LogService;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
@Repository
public class CustomTraceRepository implements HttpTraceRepository {
    @Autowired
    LogService logService;

    AtomicReference<HttpTrace> lastTrace = new AtomicReference<>();

    @Override
    public List<HttpTrace> findAll() {
        return Collections.singletonList(lastTrace.get());
    }

    /**
     * Using actuator we intercept all POST requests and send them to service-log
     */
    @Override
    public void add(HttpTrace trace) {
        if ("POST".equals(trace.getRequest().getMethod())) {
            logService.logRequest(ToStringBuilder.reflectionToString(trace.getRequest()));
        }
        lastTrace.set(trace);
    }

}
