package com.smoothstack.springbatch6.config;

import com.smoothstack.springbatch6.models.Transaction;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.item.file.*;
import org.springframework.batch.item.file.mapping.*;
import org.springframework.batch.item.file.transform.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.core.io.FileSystemResource;
import com.smoothstack.springbatch6.processor.TransactionProcessor;


@EnableBatchProcessing
@Configuration
public class BatchConfig {

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private JobBuilderFactory jobs;

    @Bean
    @StepScope
    public FlatFileItemReader reader(
            @Value("#{jobParameters['inFile']}")
                    FileSystemResource inputFile
    ){
        FlatFileItemReader reader = new FlatFileItemReader();
        reader.setResource(inputFile);
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<Transaction>(){
            {
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Transaction>(){
                    {
                        setTargetType(Transaction.class);
                    }
                });

                setLineTokenizer(new DelimitedLineTokenizer(){
                    {
                        setNames(new String[]{ "date", "county", "area", "number", "ttl_area", "avg_area", "ttl_trans_amt", "min_trans_amt", "max_trans_amt", "unit_price_min", "unit_price_max", "unit_price_med", "unit_price_avg", "unit_price_std", "month", "year", "index"});
                        setDelimiter(",");
                    }
                });
            }
        });
        return reader;
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<Transaction> writer(
            @Value("#{jobParameters['outFile']}") FileSystemResource outputFile
    ) {
        FlatFileItemWriter<Transaction> writer = new FlatFileItemWriter<>();

        writer.setResource(outputFile);
        // append once
        writer.setAppendAllowed(false);

        writer.setLineAggregator(new DelimitedLineAggregator<Transaction>() {
            {
                setDelimiter("*");
                setFieldExtractor(new BeanWrapperFieldExtractor<Transaction>() {
                    {
                        setNames(new String[]{ "date", "county", "area", "number", "ttl_area", "avg_area", "ttl_trans_amt", "min_trans_amt", "max_trans_amt", "unit_price_min", "unit_price_max", "unit_price_med", "unit_price_avg", "unit_price_std", "month", "year", "index"});
                    }
                });
            }
        });
        return writer;
    }
    

    @Bean
    public Step transactionStep(){
        return steps.get("jdbcItemWriterStep")
                .<Transaction, Transaction>chunk(200)
                .reader(reader(null))
                .faultTolerant()
                .skipPolicy(new ItemSkipPolicy())
                .skip(FlatFileParseException.class)
                .skipLimit(500)
                .processor(new TransactionProcessor())
                .writer(writer(null))
                .build();
    }


    @Bean
    public Job transactionOutFileJob(){
        return jobs.get("transactionOutFileJob")
                .start(transactionStep())
                .build();
    }

}