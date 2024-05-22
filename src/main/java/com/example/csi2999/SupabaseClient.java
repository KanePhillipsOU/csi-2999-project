package com.example.csi2999;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SupabaseClient {

    @Value("${supabase.url: j}")
    private String url;
    @Value("${supabase.apikey}")
    private String apiKey;
    @Value("${supabase.service_role}") 
    private String authToken;

    public JSONObject getReservations(){
        return new JSONObject();
    }

    public JSONObject getSites(){
        return new JSONObject();
    }

    public void createCustomer(){

    }

    public void createReservation(){

    }
    //change to private
    public JSONObject makeHTTPGETRequest(String tableName){


        try{
            System.out.println("url: " +this.url);
            HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(url + "/" + tableName))
            .timeout(Duration.ofSeconds(10))
            .headers("Content-Type", "application/json", "apikey", apiKey, "Authorization", "Bearer " + authToken)
            .GET()
            .build();

            HttpResponse<String> response = HttpClient.newBuilder()
            .build()
            .send(request, BodyHandlers.ofString());

            System.out.println("response: " + response);

        }
         catch(URISyntaxException e){
            System.out.println("There was an error with the request. Error message: " + e);
        } catch(IOException | InterruptedException e){
            System.out.println("Error: " + e);

        }

        return new JSONObject();
    }
}