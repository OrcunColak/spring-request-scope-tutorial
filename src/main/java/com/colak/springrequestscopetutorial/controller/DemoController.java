package com.colak.springrequestscopetutorial.controller;

import com.colak.springrequestscopetutorial.service.DemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

@RequiredArgsConstructor
public class DemoController {

    private final DemoService demoService;

    // http://localhost:8080/api/demo
    @GetMapping("/demo")
    public String demo() {
        this.demoService.callReqService();
        return "DONE";
    }

    // http://localhost:8080/api/demo-thread
    @GetMapping("/demo-thread")
    public String demoThread() {
        this.demoService.callReqServiceWithThread();
        return "DONE";
    }

}
