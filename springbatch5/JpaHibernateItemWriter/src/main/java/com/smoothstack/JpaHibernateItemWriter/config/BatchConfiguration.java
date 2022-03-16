package com.smoothstack.JpaHibernateItemWriter.config;

import com.smoothstack.JpaHibernateItemWriter.listener.HwJobExecutionListener;
import com.smoothstack.JpaHibernateItemWriter.listener.HwStepExecutionListener;
import com.smoothstack.JpaHibernateItemWriter.model.Transaction;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

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

    public JpaItemWriter writer() {
        return new JpaItemWriterBuilder<Transaction>()
                .entityManagerFactory(entityManagerFactory().getObject())
                .build();
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");

        return properties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        System.out.println("ENTER CONFIG");
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "com.smoothstack.springbatch.hibernateitemwriter" });

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("sam");
        dataSource.setPassword("3301");
        dataSource.setUrl(
                "jdbc:mysql://localhost:3306/transactions_pool2?createDatabaseIfNotExist=true");

        return dataSource;
    }

    @Bean
    public Step jpaHibernateStep(){
        return steps.get("jpaHibernateReaderStep")
                .listener(hwStepExecutionListener)
                .<Integer,Integer>chunk(1000)
                 .reader(reader(null))
                .writer(writer())
                .build();
    }

    @Bean
    public Job jdbcCursorJob(){
        return jobs.get("jdbcCursorJob")
                .incrementer(new RunIdIncrementer())
                .listener(hwJobExecutionListener)
                .start(jpaHibernateStep())
                .build();
    }
}
