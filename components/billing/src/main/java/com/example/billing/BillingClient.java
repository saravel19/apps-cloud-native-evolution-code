package com.example.billing;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

public class BillingClient {

    private final RestOperations restTemplate;

    //private final String serviceEndpoint;

    /*public BillingClient(String serviceEndpoint) {
        this.restTemplate = new RestTemplate();
        this.serviceEndpoint = serviceEndpoint;

    }*/

    public BillingClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

    }
    @HystrixCommand(fallbackMethod = "billUserFallback")
    public void billUser(String userId,int amount){
        restTemplate.postForEntity( "//billing/reocurringPayment", amount, String.class);

    }
    public void billUserFallback(String userId, int amount) {
               System.out.println("Executing fallback method for user: " + userId + " and amount: " + amount);
    }

}
