package com.smoothstack.springbatch.simpleclient.config;



import com.smoothstack.springbatch.simpleclient.processor.IntegerProcessor;
import com.smoothstack.springbatch.simpleclient.reader.NumberServiceAdapter;

import com.smoothstack.springbatch.simpleclient.writer.ConsoleItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.MissingResourceException;


@EnableBatchProcessing
@Configuration
public class BatchConfig {

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    NumberServiceAdapter adapter;

    public ItemReaderAdapter serviceAdapter() {
        ItemReaderAdapter readerAdapter = new ItemReaderAdapter();
        readerAdapter.setTargetObject(adapter);
        readerAdapter.setTargetMethod("nextInteger");
        return readerAdapter;
    }



    @Bean
    public Step retryStep() {
        return steps.get("retryStep")
                .<Integer, Integer>chunk(1)
                .reader(serviceAdapter())
                .writer(new ConsoleItemWriter())
                .faultTolerant()
                .skipLimit(2)
                .skipPolicy(new ItemSkipPolicy())
                .retryLimit(10)
                .retry(IllegalArgumentException.class)
                .processor(new IntegerProcessor())
                .build();
    }

    @Bean
    public Job retryJob() {
        return jobs.get("retryJob")
                .incrementer(new RunIdIncrementer())
                .start(retryStep())
                .build();
    }

}
