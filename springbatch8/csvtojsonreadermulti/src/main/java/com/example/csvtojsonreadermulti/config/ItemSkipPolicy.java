package com.example.csvtojsonreadermulti.config;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;

public class ItemSkipPolicy implements SkipPolicy {
    @Override
    public boolean shouldSkip(Throwable t, int skipCount) throws SkipLimitExceededException {
        if(t instanceof Exception) return true;
        return false;
    }
}
