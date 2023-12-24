//package com.btl.api;
//
//import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.gson.GsonFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Arrays;
//
//@Configuration
//public class GoogleSignInConfiguration {
//    private final String clientId;
//    private final String clientSecret;
//    public GoogleSignInConfiguration(@Value("${clientId}") String clientId,
//                                     @Value("${clientSecret}") String clientSecret) {
//        this.clientId = clientId;
//        this.clientSecret = clientSecret;
//    }
////     Exchange code from redirect url against access token
//    @Bean
//    public GoogleAuthorizationCodeFlow authorizationCodeFlow() {
//        HttpTransport transport = new NetHttpTransport();
//        GsonFactory jsonFactory = new GsonFactory();
//        return new GoogleAuthorizationCodeFlow.Builder(
//                transport, jsonFactory, clientId, clientSecret,
//                Arrays.asList("profile", "email")
//        ).build();
//    }
//}