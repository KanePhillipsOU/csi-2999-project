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

/**
 * This class is for all things related to interacting with the Supabase database.
 * Requests are sent to Supabase using PostgREST.
 */
@Service
public class SupabaseClient {

    @Value("${supabase.url}")
    private String url;
    @Value("${supabase.apikey}")
    private String apiKey;
    @Value("${supabase.service_role}") 
    private String authToken;



    /**
     * @return All revervations in the database.
     */
    public JSONArray getReservations(){

        return makeHttpGetRequest("Reservation");

    }

    /**
     * @return All sites in the database.
     */
    public JSONArray getSites(){
        return makeHttpGetRequest("Site");
    }

    /**
     * stub
     */
    public void createCustomer(){

    }

    /**
     * stub
     */
    public void createReservation(){

    }

    /**
     * Sends a GET request to the database to get all data back from a table.
     * @param tableName - The name of the table you want the data from, such as "Customer".
     * @return A JSONArray conaining all rows in the table.
     */
    private JSONArray makeHttpGetRequest(String tableName){

        JSONArray jsonArr = null;

        try{
            HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(url + "/rest/v1/" + tableName))
            .timeout(Duration.ofSeconds(10))
            .headers("Content-Type", "application/json", "apikey", apiKey, "Authorization", "Bearer " + authToken)
            .GET()
            .build();

            HttpResponse<String> response = HttpClient.newBuilder()
            .build()
            .send(request, BodyHandlers.ofString());


            jsonArr = new JSONArray(response.body());

        }
         catch(URISyntaxException e){
            System.out.println("There was an error with the request. Error message: " + e);
        } catch(IOException | InterruptedException e){
            System.out.println("Error: " + e);

        }

        return jsonArr;
    }
}