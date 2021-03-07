package com.kpi.booknet.booknet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("")
    public String frontEnd() {
        return "forward:/index.html";
    }
}
