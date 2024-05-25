package com.example.csi2999;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class MainController {

  @Autowired
  private final SupabaseClient supabaseClient = new SupabaseClient();



  @RequestMapping(method = RequestMethod.GET, value = "/")
  public String getStartup() {
    
    System.out.println(supabaseClient.getSites());

    return "home";  //will navigate to home, but for sake of readability take to reservations for now
    
  }
    
    @RequestMapping(method = RequestMethod.GET, value = "/home")
    public String getHome() {

      return "home";   //I will get rid od this method later too
      
    }

    @RequestMapping(method = RequestMethod.GET, value = "/about")
    public String getAbout() {

      return "about";
      
    }

    @RequestMapping(method = RequestMethod.GET, value = "/moreparks")
    public String getMoreParks() {

      return "moreparks";
    }
      

    @RequestMapping(method = RequestMethod.GET, value = "/reservations")
    public String getReservations() {

      return "reservations"; 
    } 

    @RequestMapping(method = RequestMethod.GET, value = "/pagelayout")
    public String getPageLayout() {

      return "pagelayout"; 
    } 

    
    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public String getSearch() {

      // Get the search results and generate an HTML page with them

      return "search"; 
    } 

  
    @RequestMapping(method = RequestMethod.POST, value = "/reservation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postReservation(@RequestBody ReservationForm reservationForm) {

      System.out.println("First Name: " + reservationForm.getFirstName());

      
      // store reservation and customer in the db


      // assuming everything went okay
      ResponseEntity<String> response = new ResponseEntity<>("Data successfully stored in the database.", HttpStatus.OK);

      return response; 
    } 


}