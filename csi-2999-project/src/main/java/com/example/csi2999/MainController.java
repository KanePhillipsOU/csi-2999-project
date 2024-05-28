package com.example.csi2999;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;

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
    public String getSearch() {
        return "search"; 
    } 
    public int generateRandomId() {
        // Generate a random UUID
        UUID uuid = UUID.randomUUID();
        // Get the UUID as a string and take the first 8 characters
        String uuidString = uuid.toString().substring(0, 8);
        // Convert the substring to an integer
        return Integer.parseInt(uuidString, 16);
    }
    @PostMapping(value = "/reservation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postReservation(@RequestBody ReservationForm reservationForm) {
    try {
        // Generate a unique ID for the reservation
        int id = generateRandomId();
        reservationForm.setId(id);

        // Get the current timestamp
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedTimestamp = timestamp.format(formatter);
        reservationForm.setCreatedAt(formattedTimestamp);
        
        // Store the reservation in Supabase
        String result = supabaseClient.makeHttpPostRequest(formattedTimestamp, reservationForm);
        return ResponseEntity.ok(result);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to store data in the database.");
    }
}
}
