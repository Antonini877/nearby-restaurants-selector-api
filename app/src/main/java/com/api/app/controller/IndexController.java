package com.api.app.controller;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.api.app.view.RunningAppStatus;

@RestController
@RequestMapping("/api/v1/")
public class IndexController {

    @Value("${ENV}")
    private String environment;

    @GetMapping("/")
    @ResponseBody
    public RunningAppStatus index() {
        return new RunningAppStatus(environment, "up");
    }

}