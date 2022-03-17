package com.example.csvtojsonreadermulti.config;

import com.example.csvtojsonreadermulti.listener.JobListener;
import com.example.csvtojsonreadermulti.listener.StepListener;
import com.example.csvtojsonreadermulti.models.Transaction;
import jdk.jfr.DataAmount;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@EnableBatchProcessing
@Configuration
public class BatchConfig {


    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    JobListener jobListener;

    @Autowired
    StepListener stepListener;


    @Bean
    @StepScope
    public FlatFileItemReader<Transaction> reader() {
        return new FlatFileItemReaderBuilder<Transaction>()
                .name("csvflatfileitemreader")
                .resource(new FileSystemResource("src/main/java/com/example/csvtojsonreadermulti/input/transactions.csv"))
                .delimited()
                .names(new String[]{ "date", "county", "area", "number", "ttl_area", "avg_area", "ttl_trans_amt", "min_trans_amt", "max_trans_amt", "unit_price_min", "unit_price_max", "unit_price_med", "unit_price_avg", "unit_price_std", "month", "year", "index"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Transaction>() {{
                    setTargetType(Transaction.class);
                }})
                .build();
    }


    @Bean
    @StepScope
    public JsonFileItemWriter<Transaction> writer() {
        return new JsonFileItemWriterBuilder<Transaction>()
                .jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
                .resource(new FileSystemResource("src/main/java/com/example/csvtojsonreadermulti/output/transactions.json"))
                .name("transactionJsonFileItemWriter")
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor("csvtojson");
    }


    @Bean
    public Step transactionStep(TaskExecutor taskExecutor){
        return steps.get("transactionStep")
                .<Transaction, Transaction>chunk(200)
                .reader(reader())
                .faultTolerant()
                .skipPolicy(new ItemSkipPolicy())
                .skip(FlatFileParseException.class)
                .writer(writer())
                .taskExecutor(taskExecutor)
                .build();
    }


    @Bean
    public Job transactionCsvOutFileJob(){
        return jobs.get("transactionJsonOutFileJob")
                .start(transactionStep(taskExecutor()))
                .build();
    }

}
