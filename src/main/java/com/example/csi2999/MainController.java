package com.example.csi2999;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class MainController {

  @Autowired
  private final SupabaseClient supabaseClient = new SupabaseClient();



  @RequestMapping(method = RequestMethod.GET, value = "/")
  public String startup() {
    
    System.out.println(supabaseClient.getSites());

    return "reservations";  //will navigate to home, but for sake of readability take to reservations for now
    
  }
    
    @RequestMapping(method = RequestMethod.GET, value = "/home")
    public String home() {

      return "home";   //I will get rid od this method later too
      
    }

    @RequestMapping(method = RequestMethod.GET, value = "/about")
    public String about() {

      return "about";
      
    }

    @RequestMapping(method = RequestMethod.GET, value = "/moreparks")
    public String moreparks() {

      return "moreparks";
    }
      

       @RequestMapping(method = RequestMethod.GET, value = "/reservations")
    public String reservations() {

      return "reservations"; 
    } 


}