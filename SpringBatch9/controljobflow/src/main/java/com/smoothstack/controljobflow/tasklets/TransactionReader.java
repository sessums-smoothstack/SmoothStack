package com.smoothstack.controljobflow.tasklets;

import com.smoothstack.controljobflow.models.Transaction;
import com.smoothstack.controljobflow.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.ArrayList;
import java.util.List;

public class TransactionReader implements Tasklet, StepExecutionListener {

    private final Logger logger = LoggerFactory
            .getLogger(TransactionReader.class);

    private List<Transaction> transactions;
    private FileUtils fu;
    private Transaction transaction;

    @Override
    public void beforeStep(StepExecution stepExecution) {
//        transactions = new ArrayList<>();
        fu = new FileUtils(
                "src/main/resources/files/input/transactions.csv");
        logger.debug("Lines Reader initialized.");
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution,
                                ChunkContext chunkContext) throws Exception {
        transaction = fu.readLine();
        logger.info(transaction.toString());
        //Transaction transaction = fu.readLine();
        /*if(transaction == null) {
            return RepeatStatus.FINISHED;
        }*/
        /*while (transaction != null) {
            transactions.add(transaction);
            logger.debug("Read line: " + transaction.toString());
            transaction = fu.readLine();
        }*/
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        fu.closeReader();
        stepExecution
                .getJobExecution()
                .getExecutionContext()
                .put("transactions", this.transaction);
        logger.debug("Lines Reader ended.");
        return ExitStatus.COMPLETED;
    }
}