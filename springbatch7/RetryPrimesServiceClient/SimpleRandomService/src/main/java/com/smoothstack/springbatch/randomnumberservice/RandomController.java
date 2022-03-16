package com.smoothstack.springbatch.randomnumberservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class RandomController {

    private final Random rand = new Random();

    @GetMapping("/random/")
    public Integer getProduct(){
        return rand.nextInt(1000) + 1;
    }
}
