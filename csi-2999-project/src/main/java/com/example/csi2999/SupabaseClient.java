package com.example.csi2999;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SupabaseClient {
    
    @Value("${supabase.url}")
    private String url;

    @Value("${supabase.apikey}")
    private String apiKey;

    @Value("${supabase.service_role}") 
    private String authToken;
    
    public JSONArray getReservations() {
        return makeHttpGetRequest("Customer");
    }

    public JSONArray getSites() {
        return makeHttpGetRequest("Site");
    }
    
    private String reservationFormToJson(ReservationForm reservationForm) {
        return "{\"first_name\":\"" + reservationForm.getFirstName() + "\"," +
                "\"last_name\":\"" + reservationForm.getLastName() + "\"," +
                "\"email\":\"" + reservationForm.getEmail() + "\"," +
                "\"phone_number\":\"" + reservationForm.getPhoneNumber() + "\"" +
                // "\"agreedToTerms\":" + reservationForm.isAgreedToTerms() +
                "}";
    }
    
    public boolean createReservation(ReservationForm reservationForm) {
        String requestBody = reservationFormToJson(reservationForm);
        return makeHttpPostRequest("Customer", requestBody);
    }

    private JSONArray makeHttpGetRequest(String tableName) {
        JSONArray jsonArr = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url + "/rest/v1/" + tableName))
                    .timeout(Duration.ofSeconds(10))
                    .headers("Content-Type", "application/json", "apikey", apiKey, "Authorization", "Bearer " + authToken)
                    .GET()
                    .build();
                    
            HttpResponse<String> response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            jsonArr = new JSONArray(response.body());
        } catch(URISyntaxException | IOException | InterruptedException e) {
            System.out.println("Error: " + e);
        }
        return jsonArr;
    }

    private boolean makeHttpPostRequest(String tableName, String requestBody) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url + "/rest/v1/" + tableName))
                    .timeout(Duration.ofSeconds(10))
                    .headers("Content-Type", "application/json", "apikey", apiKey, "Authorization", "Bearer " + authToken)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
                    
            HttpResponse<String> response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return response.statusCode() == 201; // 201 indicates successful creation
        } catch (URISyntaxException | IOException | InterruptedException e) {
            System.out.println("Error: " + e);
            return false;
        }
    }
    
    
}
