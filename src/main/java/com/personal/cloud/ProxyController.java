package com.personal.cloud;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by josgar on 07/06/2017.
 */

@RestController
public class ProxyController {

    private RestTemplate restTemplate;

    @Autowired
    public ProxyController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getFallBackGreeting")
    @GetMapping(path = "/saludos/{name}")
    public String getGreeting(@PathVariable String name){

        ResponseEntity<Greeting> response = restTemplate.exchange("http://greetings-service/greetings/{name}",
                HttpMethod.GET, null, Greeting.class, name);

        Greeting greeting = response.getBody();

        return greeting.getMessage();
    }

    public String getFallBackGreeting(String name){
        return "Well, hello then " + name;
    }
}
