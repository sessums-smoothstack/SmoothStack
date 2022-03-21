package com.smoothstack.controljobflow.steps;

import org.springframework.batch.core.JobInterruptedException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;

public class ReaderStep implements Step {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean isAllowStartIfComplete() {
        return false;
    }

    @Override
    public int getStartLimit() {
        return 0;
    }

    @Override
    public void execute(StepExecution stepExecution) throws JobInterruptedException {
        System.out.println(stepExecution.getSummary());
    }
}
