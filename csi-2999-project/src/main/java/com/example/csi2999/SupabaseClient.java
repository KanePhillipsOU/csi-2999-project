package com.example.csi2999;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import com.example.csi2999.ReservationForm;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class SupabaseClient {

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.apikey}")
    private String supabaseApiKey;

    @Value("${supabase.service_role}")
    private String supabaseAuthToken;

    @Value("${supabase.tablename}")
    private String supabaseTableName;

    /**
     * @return All sites in the database.
     */
    public JSONArray getSites() {
        return makeHttpGetRequest("Site");
    }

    /**
     * @return All reservations in the database.
     */
    public JSONArray getReservations() {
        return makeHttpGetRequest("Reservation");
    }

    /**
     * Sends a GET request to the database to get all data back from a table.
     * 
     * @param tableName - The name of the table you want the data from, such as
     *                  "Customer".
     * @return A JSONArray containing all rows in the table.
     */
    public JSONArray makeHttpGetRequest(String tableName) {
        JSONArray jsonArr = null;

        try {
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(supabaseUrl + "/rest/v1/" + tableName))
                    .timeout(Duration.ofSeconds(10))
                    .headers("Content-Type", "application/json", "apikey", supabaseApiKey, "Authorization",
                            "Bearer " + supabaseAuthToken)
                    .GET().build();

            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, BodyHandlers.ofString());

            jsonArr = new JSONArray(response.body());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return jsonArr;
    }
    
    /**
     * Sends a POST request to the database to store data in a table.
     * 
     * @param tableName - The name of the table where the data will be stored.
     * @param reservationForm      - The data to be stored in the table as a JSONObject.
     * @return A response message indicating success or failure.
     */
    public String makeHttpPostRequest(String tableName, ReservationForm reservationForm) {
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(supabaseUrl + "/rest/v1/" + tableName))
                    .timeout(Duration.ofSeconds(10))
                    .headers("Content-Type", "application/json", "apikey", supabaseApiKey, "Authorization",
                            "Bearer " + supabaseAuthToken)
                    .POST(HttpRequest.BodyPublishers.ofString(reservationForm.toString())).build();

            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, BodyHandlers.ofString());

            if (response.statusCode() == 201) {
                return "Data successfully stored in the database.";
            } else {
                throw new RuntimeException(
                        "Failed to store data in the database. Status code: " + response.statusCode());
            }
        } catch (URISyntaxException | IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
            return "Failed to store data in the database.";
        }
    }
    
}
