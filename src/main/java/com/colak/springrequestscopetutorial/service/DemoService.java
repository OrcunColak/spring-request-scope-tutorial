package com.colak.springrequestscopetutorial.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor

@Slf4j
public class DemoService {

    private final ReqService reqService;
    private int count = 0;
    private final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

    public void callReqService() {
        log.info("DemoService: {}", this.count);
        this.reqService.reqServiceMethod();
        this.count++;
    }

    // Every scope in spring besides singleton and prototype will be backed by a Scope implementation.
    // RequestScope beans are taken from RequestAttributes.
    // The RequestAttributes will be stored in the ThreadLocal such that each thread will have its own instance.
    // If no beans can be retrieved , it will instantiate a new one and store it to the RequestAttributes .
    public void callReqServiceWithThread() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        executorService.submit(() -> {
            RequestContextHolder.setRequestAttributes(requestAttributes);
            callReqService();
        });
    }

    // or
    // public void callReqServiceWithThread() {
    //     ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
    //     RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
    //     RequestContextHolder.setRequestAttributes(requestAttributes, true);
    //     executorService.submit(() -> {
    //         System.out.println("DemoService: " + this.count);
    //         this.reqService.reqServiceMethod();
    //         this.count++;
    //     });
    // }
}
