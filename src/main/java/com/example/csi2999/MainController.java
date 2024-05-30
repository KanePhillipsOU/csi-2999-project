package com.example.csi2999;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class MainController {

  @Autowired
  private final SupabaseClient supabaseClient = new SupabaseClient();



  @RequestMapping(method = RequestMethod.GET, value = "/")
  public String getStartup() {
    
    // System.out.println(supabaseClient.getSites());

    return "home"; 
    
  }
    
    @RequestMapping(method = RequestMethod.GET, value = "/home")
    public String getHome() {

      return "home"; 
      
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
    public String getSearch(Model model1, Model model2, Model model3) {

      // Get the search results and generate an HTML page with them


      //Hard-coded attributes for example sites to test thymeleaf
      model1.addAttribute("name1", "Fake Site 1");
      model1.addAttribute("price1", 0.01);
      model1.addAttribute("description1", "fake site description");
      model1.addAttribute("water1", "Has Water");
      model1.addAttribute("electric1", "Has Electricity");
      model1.addAttribute("reservation1", "Not currently reserved");
      model2.addAttribute("name2", "Fake Site 2");
      model2.addAttribute("price2", 0.02);
      model3.addAttribute("name3", "Fake Site 3");
      model3.addAttribute("price3", 0.03);
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


    //Thymeleaf test
    @RequestMapping(method = RequestMethod.GET, value = "/thymeleaftest")
    public String getThymeTest(Model model){
      model.addAttribute("message", "main controller message");
      return "thymeleaftest";
    }


}