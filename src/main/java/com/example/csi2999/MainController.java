package com.example.csi2999;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {
    
    @RequestMapping("/")
    public String home() {

      return "index";
      
    }

    @RequestMapping("/about")
    public String about() {

      return "about";
      
    }

}