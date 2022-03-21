package com.smoothstack.controljobflow.tasklets;

import com.smoothstack.controljobflow.models.Transaction;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.AbstractItemStreamItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class ReaderTasklet implements Tasklet {

    private ChunkContext chunkContext;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        FlatFileItemReader<Transaction> reader = csvReader();
        reader.open(chunkContext.getStepContext().getStepExecution().getExecutionContext());
        reader.read();
        reader.close();
        return null;
    }

    public FlatFileItemReader<Transaction> csvReader() {
        return new FlatFileItemReaderBuilder<Transaction>()
                .name("csvflatfileitemreader")
                .resource(new FileSystemResource("src/main/resources/files/input/transactions.csv"))
                .linesToSkip(1)
                .delimited()
                .names(new String[]{ "date", "county", "area", "number", "ttl_area", "avg_area", "ttl_trans_amt", "min_trans_amt", "max_trans_amt", "unit_price_min", "unit_price_max", "unit_price_med", "unit_price_avg", "unit_price_std", "month", "year", "index"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Transaction>() {{
                    setTargetType(Transaction.class);
                }})
                .build();
    }

}
