package com.colak.springrequestscopetutorial.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

@Service
@RequestScope

@Slf4j
public class ReqService {

    private int count = 0;

    public void reqServiceMethod() {
        count++;
        log.info("ReqService: {}", count);
    }

}
