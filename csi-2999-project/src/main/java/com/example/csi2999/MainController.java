package com.example.csi2999;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @Autowired
    private final SupabaseClient supabaseClient = new SupabaseClient();

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String getStartup() {
        System.out.println(supabaseClient.getSites());
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
        // Hard-coded attributes for example sites to test Thymeleaf
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
    
    @PostMapping("/reservation")
    public String createReservation(ReservationForm reservationForm, Model model) {
        boolean success = supabaseClient.createReservation(reservationForm);
        if (success) {
            model.addAttribute("message", "Reservation created successfully!");
            return "success";
        } else {
            model.addAttribute("message", "Failed to create reservation.");
            return "error";
        }
    }

    // Thymeleaf test
    @RequestMapping(method = RequestMethod.GET, value = "/thymeleaftest")
    public String getThymeTest(Model model){
        model.addAttribute("message", "main controller message");
        return "thymeleaftest";
    }
}
