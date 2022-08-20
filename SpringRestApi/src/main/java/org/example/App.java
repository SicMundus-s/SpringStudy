package org.example;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> JsonToSend = new HashMap<>();
        JsonToSend.put("name", "Test name");
        JsonToSend.put("job", "Test job");

        HttpEntity<Map<String, String>> request = new HttpEntity<>(JsonToSend);

        String url = "https://reqres.in/api/users/";
        String response = restTemplate.postForObject(url, request, String.class);
        System.out.println(response);
    }
}
