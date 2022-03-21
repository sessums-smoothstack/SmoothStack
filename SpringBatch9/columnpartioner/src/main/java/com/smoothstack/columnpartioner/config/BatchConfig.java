package com.smoothstack.columnpartioner.config;


import com.smoothstack.columnpartioner.model.Transaction;
import com.smoothstack.columnpartioner.reader.ColumnRangePartitioner;
import com.smoothstack.columnpartioner.writers.ConsoleItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    JobBuilderFactory jobsFactory;

    @Autowired
    StepBuilderFactory stepsFactory;

    @Autowired
    DataSource dataSource;


    @Bean
    @StepScope
    public JdbcPagingItemReader pagingItemReader(
            @Value("#{stepExecutionContext['minValue']}") Integer minValue,
            @Value("#{stepExecutionContext['maxValue']}") Integer maxValue
    ) {
        System.out.println("From " + minValue + "to " + maxValue);
        Map<String, Order> sortKey = new HashMap<>();
        sortKey.put("id", Order.ASCENDING);

        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause("id, dte, county, area, number, ttl_area, avg_area, ttl_trans_amt, min_trans_amt, max_trans_amt, unit_price_min, unit_price_max, unit_price_med, unit_price_avg, unit_price_std, mnth, yer, indx"
        );
        queryProvider.setFromClause("from transactions");
        queryProvider.setWhereClause("where id >=" + minValue + " and id <" + maxValue);
        queryProvider.setSortKeys(sortKey);

        JdbcPagingItemReader reader = new JdbcPagingItemReader();
        reader.setDataSource(this.dataSource);
        reader.setQueryProvider(queryProvider);
        reader.setFetchSize(1000);

        reader.setRowMapper(new BeanPropertyRowMapper() {
            {
                setMappedClass(Transaction.class);
            }
        });

        return reader;
    }

    public ColumnRangePartitioner columnRangePartitioner() {
        ColumnRangePartitioner columnRangePartitioner = new ColumnRangePartitioner();
        columnRangePartitioner.setColumn("id");
        ;
        columnRangePartitioner.setDataSource(dataSource);
        columnRangePartitioner.setTable("transactions");
        return columnRangePartitioner;
    }


    public Step partitionStep() {
        return stepsFactory.get("partitionStep")
                .partitioner(slaveStep().getName(), columnRangePartitioner())
                .step(slaveStep())
                .gridSize(3)
                .taskExecutor(new SimpleAsyncTaskExecutor())
                .build();
    }


    public Step slaveStep() {
        return stepsFactory.get("slaveStep")
                .<Transaction, Transaction>chunk(5)
                .reader(pagingItemReader(null, null))
                .writer(new ConsoleItemWriter())
                .build();
    }

    @Bean
    public Job columnPartitionJob() {
        return jobsFactory.get("columnPartitionJob")
                .incrementer(new RunIdIncrementer())
                .start(partitionStep())
                .build();
    }

}
