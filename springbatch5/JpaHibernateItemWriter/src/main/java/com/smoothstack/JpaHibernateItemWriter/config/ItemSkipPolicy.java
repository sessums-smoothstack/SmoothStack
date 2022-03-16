package com.smoothstack.JpaHibernateItemWriter.config;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.file.transform.FlatFileFormatException;

public class ItemSkipPolicy implements SkipPolicy {

    public boolean shouldSkip(final Throwable t, final int skipCount) throws SkipLimitExceededException {
        if (t instanceof FlatFileFormatException || skipCount < 500) {
            return true;
        }
        else return false;

    }
}