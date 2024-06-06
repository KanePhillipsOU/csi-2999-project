package com.example.csi2999;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    private SupabaseClient supabaseClient;

    @GetMapping("/")
    public String getStartup() {
        System.out.println(supabaseClient.getSites());
        return "home";
    }

    @GetMapping("/home")
    public String getHome() {
        return "home";
    }

    @GetMapping("/about")
    public String getAbout() {
        return "about";
    }

    @GetMapping("/moreparks")
    public String getMoreParks() {
        return "moreparks";
    }

    @GetMapping("/reservations")
    public String getReservations() {
        return "reservations";
    }

    @GetMapping("/pagelayout")
    public String getPageLayout() {
        return "pagelayout";
    }
    
    @GetMapping("/search")
    public String getSearch(@RequestParam String startDate, @RequestParam String endDate, @RequestParam String sites, Model model) {
        // Fetch sites from the Supabase client
        JSONArray siteArray = supabaseClient.getSites();
        System.out.println("Fetched sites: " + siteArray.toString());

        // Convert JSONArray to a list of Site objects
        List<Site> siteList = convertJsonArrayToSiteList(siteArray);
        System.out.println("Converted sites: " + siteList.toString());

        // Filter the siteList based on the amenities specified in the 'sites' parameter
        List<Site> filteredSites = filterSitesByAmenities(siteList, sites);
        System.out.println("Filtered sites: " + filteredSites.toString());

        // Add the filtered sites to the model
        model.addAttribute("sites", filteredSites);
        System.out.println("Model attributes: " + model.asMap().toString());
        return "search";
    }
    
    
    @PostMapping("/reservation")
    public ResponseEntity<String> createReservation(@RequestBody ReservationForm reservationForm) {

        boolean success = supabaseClient.createReservation(reservationForm);
        if (success) {
            return ResponseEntity.ok("Reservation successful.");
        } else {
            return ResponseEntity.status(500).body("Reservation could not be processed.");
        }
    }

    @GetMapping("/thymeleaftest")
    public String getThymeTest(Model model) {
        model.addAttribute("message", "main controller message");
        return "thymeleaftest";
    }

    private List<Site> convertJsonArrayToSiteList(JSONArray siteArray) {
        List<Site> siteList = new ArrayList<>();
        for (int i = 0; i < siteArray.length(); i++) {
            JSONObject siteJson = siteArray.getJSONObject(i);
            Site site = new Site();
            site.setSite_id(siteJson.getInt("site_id"));
            site.setName(siteJson.getString("name"));
            site.setDescription(siteJson.getString("description"));

            if (siteJson.isNull("cost_per_day")) {
                site.setCost_per_day("N/A");
            } else {
                site.setCost_per_day(String.valueOf(siteJson.getInt("cost_per_day")));
            }

            site.setFull_hookup(siteJson.getBoolean("full_hookup"));
            site.setRustic(siteJson.getBoolean("rustic"));
            site.setWater_and_electric(siteJson.getBoolean("water_and_electric"));
            
            // Set image_name property
            site.setPicture_name(siteJson.getString("picture_name"));
            
            siteList.add(site);
        }
        return siteList;
    }

    private List<Site> filterSitesByAmenities(List<Site> siteList, String amenities) {
        if (amenities == null || amenities.isEmpty()) {
            return siteList;
        }

        String[] amenitiesArray = amenities.split(",");
        System.out.println("Filtering amenities: " + Arrays.toString(amenitiesArray));

        List<Site> filteredList = siteList.stream()
                .filter(site -> {
                    boolean matches = true;
                    for (String amenity : amenitiesArray) {
                        switch (amenity.trim().toLowerCase()) {
                            case "fullhookup":
                                matches = matches && site.isFull_hookup();
                                break;
                            case "rustic":
                                matches = matches && site.isRustic();
                                break;
                            case "waterandelectric":
                                matches = matches && site.isWater_and_electric();
                                break;
                            default:
                                matches = false;
                        }
                        if (!matches) break;
                    }
                    System.out.println("Site: " + site.getName() + ", matches: " + matches);
                    return matches;
                })
                .collect(Collectors.toList());

        System.out.println("Filtered sites: " + filteredList);
        return filteredList;
    }
}
