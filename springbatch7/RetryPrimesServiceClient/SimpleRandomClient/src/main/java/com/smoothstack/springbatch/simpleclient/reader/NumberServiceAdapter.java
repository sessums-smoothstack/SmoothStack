package com.smoothstack.springbatch.simpleclient.reader;


import com.smoothstack.springbatch.simpleclient.service.NumberService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NumberServiceAdapter {
    Logger logger = LoggerFactory.getLogger(NumberServiceAdapter.class);

    @Autowired
    NumberService service;

    public Integer nextInteger() throws InterruptedException {

        Integer i = null;
        Thread.sleep(100);
        try {
            i = service.nextInteger();
            logger.info("connected web service .... ok");
        }catch(Exception e){
            logger.info("exception ..." + e.getMessage());
            throw e;
        }
        return i;
    }

}
