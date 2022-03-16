package com.smoothstack.springbatch.HibernateReader.config;

import com.smoothstack.springbatch.HibernateReader.listener.HwJobExecutionListener;
import com.smoothstack.springbatch.HibernateReader.listener.HwStepExecutionListener;
import com.smoothstack.springbatch.HibernateReader.model.Transaction;
import com.smoothstack.springbatch.HibernateReader.writer.ConsoleItemWriter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.HibernateCursorItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.hibernate.cfg.Configuration;
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
    public HibernateCursorItemReader hibernateCursorItemReader() {
        HibernateCursorItemReader<Transaction> reader = new HibernateCursorItemReader<>();
        reader.setQueryString("FROM Transaction");
        Configuration configuration = new Configuration();

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Transaction.class)
                .buildSessionFactory();
        reader.setSessionFactory(factory);
        return reader;
    }

    @Bean
    public Step jdbcCursorStep(){
        return steps.get("jdbcCursorStep")
                .listener(hwStepExecutionListener)
                .<Integer,Integer>chunk(1000)
                 .reader(hibernateCursorItemReader())
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
