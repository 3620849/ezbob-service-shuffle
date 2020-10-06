package com.ezbob.shuffle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Service
public class LogService {
    @Autowired
    RestTemplate restTemplate;
    @Value("${app.logApplicationName}")
    String LOG_APP_NAME;

    @Async
    public void logRequest (String request){
        restTemplate.postForObject("http://"+LOG_APP_NAME+"/log",request, ResponseEntity.class);
    }
}
