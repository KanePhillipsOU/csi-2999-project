package com.example.csi2999;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Controller
public class MainController {

    @Autowired
    private SupabaseClient supabaseClient;

    @GetMapping("/")
    public String getStartup() {
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
        //System.out.println("Fetched sites: " + siteArray.toString());

        // Convert JSONArray to a list of Site objects
        List<Site> siteList = convertJsonArrayToSiteList(siteArray);
        //System.out.println("Converted sites: " + siteList.toString());

        // Filter the siteList based on the amenities specified in the 'sites' parameter
        List<Site> filteredSites = filterSitesByAmenitiesAndAvailability(siteList, sites, startDate, endDate);
        //System.out.println("Filtered sites: " + filteredSites.toString());

        // Add the filtered sites to the model
        model.addAttribute("sites", filteredSites);
        //System.out.println("Model attributes: " + model.asMap().toString());
        return "search";
    }

    @PostMapping("/reservation")
    public ResponseEntity<String> createReservation(@RequestBody ReservationForm reservationForm) {
        JSONArray reservations = supabaseClient.getReservationsBySiteAndDate(
                reservationForm.getSelectedSiteId(),
                reservationForm.getStartDate(),
                reservationForm.getEndDate()
        );

        if (reservations.length() == 0) {
            boolean success = supabaseClient.createReservation(reservationForm);
            if (success) {
                return ResponseEntity.ok("Reservation successful.");
            } else {
                return ResponseEntity.status(500).body("Reservation could not be processed.");
            }
        } else {
            return ResponseEntity.status(400).body("Selected site is not available for the given dates.");
        }
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

    private boolean siteAvailable(Site site, JSONArray reservations, String startDate, String endDate) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M-d-yyyy");
        
        LocalDateTime newStartDateTime = LocalDate.parse(startDate, dateFormatter).atTime(14, 0); // Default check-in time 2:00 PM
        LocalDateTime newEndDateTime = LocalDate.parse(endDate, dateFormatter).atTime(12, 0); // Default check-out time 12:00 PM

        //System.out.println("Checking availability for site ID: " + site.getSite_id());
        //System.out.println("New reservation start: " + newStartDateTime);
        //System.out.println("New reservation end: " + newEndDateTime);

        for (int i = 0; i < reservations.length(); i++) {
            JSONObject reservation = reservations.getJSONObject(i);
            if (reservation.getInt("selected_site_id") == site.getSite_id()) {
                LocalDate resStartDate = LocalDate.parse(reservation.getString("start_date"), dateFormatter);
                LocalDate resEndDate = LocalDate.parse(reservation.getString("end_date"), dateFormatter);
                
                // Check if start_time and end_time are present
                LocalTime resStartTime = reservation.isNull("start_time") ? LocalTime.of(14, 0) : LocalTime.parse(reservation.getString("start_time"));
                LocalTime resEndTime = reservation.isNull("end_time") ? LocalTime.of(12, 0) : LocalTime.parse(reservation.getString("end_time"));
                
                LocalDateTime resStartDateTime = resStartDate.atTime(resStartTime);
                LocalDateTime resEndDateTime = resEndDate.atTime(resEndTime);

                //System.out.println("Existing reservation start: " + resStartDateTime);
                //System.out.println("Existing reservation end: " + resEndDateTime);

                if (datesOverlap(resStartDateTime, resEndDateTime, newStartDateTime, newEndDateTime)) {
                    System.out.println("Overlap detected");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean datesOverlap(LocalDateTime startDateTime1, LocalDateTime endDateTime1, LocalDateTime startDateTime2, LocalDateTime endDateTime2) {
        return !(endDateTime1.isBefore(startDateTime2) || startDateTime1.isAfter(endDateTime2));
    }

    private List<Site> filterSitesByAmenitiesAndAvailability(List<Site> siteList, String amenities, String startDate, String endDate) {
        String[] amenitiesArray = amenities.split(",");
        JSONArray reservations = supabaseClient.getReservations();  // Fetch all reservations once

        return siteList.stream()
                .filter(site -> {
                    boolean matches = Arrays.stream(amenitiesArray)
                            .allMatch(amenity -> {
                                switch (amenity.trim().toLowerCase()) {
                                    case "fullhookup":
                                        return site.isFull_hookup();
                                    case "rustic":
                                        return site.isRustic();
                                    case "waterandelectric":
                                        return site.isWater_and_electric();
                                    default:
                                        return false;
                                }
                            });
                    if (matches) {
                        matches = siteAvailable(site, reservations, startDate, endDate);
                    }
                    return matches;
                })
                .collect(Collectors.toList());
    }
}
