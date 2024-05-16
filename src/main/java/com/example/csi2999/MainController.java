package com.example.csi2999;

import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.model.Model;

public class MainController {
    
    @RequestMapping("/")
    public String home(Model model) {

      return "index.html";
    }

}