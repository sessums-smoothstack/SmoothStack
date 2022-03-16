package com.smoothstack.springbatch.simpleclient.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;

public class ItemSkipPolicy implements SkipPolicy {
    Logger logger = LoggerFactory.getLogger(ItemSkipPolicy.class);

    @Override
    public boolean shouldSkip(final Throwable t, final int skipCount) throws SkipLimitExceededException {

        logger.info("skip count: " + skipCount);

        if (t instanceof IllegalArgumentException && skipCount < 20) {

            return true;
        }
        return false;

    }
}