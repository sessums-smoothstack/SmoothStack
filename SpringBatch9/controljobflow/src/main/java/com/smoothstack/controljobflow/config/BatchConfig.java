package com.smoothstack.controljobflow.config;


import com.smoothstack.controljobflow.models.Transaction;
import com.smoothstack.controljobflow.processors.Processor;
import com.smoothstack.controljobflow.steps.ReaderStep;
import com.smoothstack.controljobflow.tasklets.*;
import com.smoothstack.controljobflow.writers.ConsoleItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.jsr.step.PartitionStep;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.RecordFieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.AbstractItemStreamItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    StepBuilderFactory stepsFactory;

    @Autowired
    JobBuilderFactory jobsFactory;

    @Autowired
    DataSource dataSource;

    @Bean
    public Job job1() {
        return jobsFactory.get("job1")
                .start(readerJob())
                .from(readerJob()).on("*").to(processStep())
                .from(processStep()).on("*").to(mootStep())
                .from(processStep()).on("FAILED").to(itemFailStep())
                .end()
                .build();
    }

/*    @Bean
    public Job job2() {
        return jobsFactory.get("job2")
                .start(flow())
                .from(flow()).on("*").to(mootStep())
                .from(flow()).on("FAILED").to(itemFailStep())
                .end()
                .build();
    }*/


    @Bean
    public Flow readerJob() {
        return new FlowBuilder<SimpleFlow>("readerJob")
                .start(transactionReadStep())
                .build();
    }


    @Bean
    public Flow flow() {
        return new FlowBuilder<SimpleFlow>("the Flow")
                .start(stepToBeFailed())
                .build();
    }

    @Bean
    public Flow anotherFlow() {
        return new FlowBuilder<SimpleFlow>("the Flow")
                .start(new ReaderStep())
                .build();
    }

    @Bean
    public Step itemFailStep() {
        return stepsFactory.get("itemFailStep")
                .tasklet(new PagerDutyStep())
                .build();
    }

    @Bean
    public Step mootStep() {
        return stepsFactory.get("itemFailStep")
                .tasklet(new PointlessTasklet())
                .build();
    }

    @Bean
    public Step readerStep() {
        return stepsFactory.get("readerStep")
                .tasklet(new ReaderTasklet())
                .build();
    }


    @Bean
    public Step processStep() {
        return stepsFactory.get("processStep")
                .tasklet(new ProcessTasklet())
                .build();
    }

    @Bean
    public Step transactionReadStep() {
        return stepsFactory.get("transactionReadStep")
                .tasklet(new TransactionReader())
                .build();
    }


    @Bean
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

    @Bean
    public Step stepToBeFailed() {
        return stepsFactory.get("stepToBeFailed")
                .<Transaction, Transaction>chunk(1)
                .reader(csvReader())
                .processor(new Processor())
                .writer(new ConsoleItemWriter())
                .faultTolerant()
                .skipPolicy(new ItemSkipPolicy())
                .build();
    }

    @Bean
    public Step step1() {
        return stepsFactory.get("step1")
                .tasklet(new ReaderTasklet())
                .tasklet(new ReaderTasklet())
                .build();
    }

    @Bean
    AbstractItemStreamItemWriter mootWriter() {
        AbstractItemStreamItemWriter itemWriter = new AbstractItemStreamItemWriter() {
            @Override
            public void write(List items) throws Exception {}
        };
        return itemWriter;
    }

    @Bean
    public ItemReader mootReader() {
        ItemReader itemReader = new ItemReader() {
            @Override
            public Object read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                return null;
            }
        };
        return itemReader;
    }



    @Bean
    public Step readerStep2() {
        return stepsFactory.get("readerStep2")
                .<Transaction, Transaction>chunk(50)
                .reader(csvReader())
                .processor(new Processor())
                .writer(mootWriter())
                .faultTolerant()
                .skipPolicy(new ItemSkipPolicy())
                .build();
    }

//    @Bean
//    public Step writerStep2() {
//        return stepsFactory.get
//    }




}
