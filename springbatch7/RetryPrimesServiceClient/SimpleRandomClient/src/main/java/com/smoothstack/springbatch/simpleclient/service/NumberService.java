package com.smoothstack.springbatch.simpleclient.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NumberService {


    public Integer nextInteger(){
        RestTemplate restTemplate = new RestTemplate();
        String url ="http://localhost:8090/random/";
        Integer i = restTemplate.getForObject(url, Integer.class);
        return i;
    }
}
