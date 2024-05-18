package com.example.csi2999;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MainController {
    
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String home() {

      return "index";
      
    }

    @RequestMapping(method = RequestMethod.GET, value = "/about")
    public String about() {

      return "about";
      
    }

}