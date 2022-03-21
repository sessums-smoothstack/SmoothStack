package com.smoothstack.controljobflow.processors;


import com.smoothstack.controljobflow.models.Transaction;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.listener.ItemListenerSupport;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.jsr.item.ItemProcessorAdapter;


public class Processor extends ItemListenerSupport<Transaction, Transaction> implements ItemProcessor<Transaction, Transaction> {

    private StepExecution stepExecution;

    String MATCH_DATE = "2018-05-01";

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
        this.stepExecution.setExitStatus(new ExitStatus("COMPLETED"));
    }

    @Override
    public Transaction process(Transaction transaction) throws Exception {
        if (transaction.getDate().equals(MATCH_DATE)) {
            stepExecution.setExitStatus(new ExitStatus("FAILED"));
            transaction = null;
        }
        return transaction;
        // return the transaction or throw exception
    }

    @Override
    public void afterProcess(Transaction input, Transaction output) {
        if (input.getDate().equals(MATCH_DATE)) {
            stepExecution.setExitStatus(new ExitStatus("FAILED"));
        }
        super.afterProcess(input, output);
    }
}
