package com.smoothstack.springbatch.CSVReader.config;

import com.smoothstack.springbatch.CSVReader.listener.HwJobExecutionListener;
import com.smoothstack.springbatch.CSVReader.listener.HwStepExecutionListener;
import com.smoothstack.springbatch.CSVReader.model.Transaction;
import com.smoothstack.springbatch.CSVReader.writer.ConsoleItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@EnableBatchProcessing
@org.springframework.context.annotation.Configuration
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private HwJobExecutionListener hwJobExecutionListener;

    @Autowired
    private HwStepExecutionListener hwStepExecutionListener;

    @Autowired
    private DataSource dataSource;


    @Bean
    public JdbcCursorItemReader jdbcCursorItemReader(){
        JdbcCursorItemReader reader = new JdbcCursorItemReader();
        reader.setDataSource(this.dataSource);
        reader.setSql("select * from transaction_pool.transactions");
        reader.setRowMapper(new BeanPropertyRowMapper(){
            {
                setMappedClass(Transaction.class);
            }
        });
        return reader;
    }

    @Bean
    public Step jdbcCursorStep(){
        return steps.get("jdbcCursorStep")
                .listener(hwStepExecutionListener)
                .<Integer,Integer>chunk(1000)
                 .reader(jdbcCursorItemReader())
                .writer(new ConsoleItemWriter())
                .build();
    }

    @Bean
    public Job jdbcCursorJob(){
        return jobs.get("jdbcCursorJob")
                .incrementer(new RunIdIncrementer())
                .listener(hwJobExecutionListener)
                .start(jdbcCursorStep())
                .build();
    }
}
