package com.smoothstack.springbatch.simpleclient.processor;

import com.smoothstack.springbatch.simpleclient.reader.NumberServiceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.math.BigInteger;


public class IntegerProcessor implements ItemProcessor<Integer, Integer> {
    Logger logger = LoggerFactory.getLogger(IntegerProcessor.class);

    @Override
    public Integer process(Integer number) throws Exception {
        logger.info("number is: " + number);

        if(BigInteger.valueOf(number).isProbablePrime(1)) {
            logger.info("number is prime: " + number);
            return number;
        }
        else {

            throw new IllegalArgumentException();
        }

    }
}