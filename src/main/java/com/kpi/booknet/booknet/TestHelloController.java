package com.kpi.booknet.booknet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestHelloController {

    @GetMapping("/")
    public String method(){
        return "test app running";
    }
}
