# Assignment

## 1. Find the problem with the following code and fix it.

```
import org.springframework.boot.autoconfigure.SpringApplication;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
@EnableBatchProcessing
@SpringApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication, args);
    }
}
```

### 1. Solution
```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
```


## 2. Find the problem with the following code and fix it.

```
import org.springframework.context.annotation.Job;
@Job
public Job helloWorldJob() {  // Job is an interface
    return jobs.get("helloWorldJob").start(step1()).build();
}
```

### 2. Solution
```
import org.springframework.context.annotation.Job;
@Job
public Job helloWorldJob(Step step1) {  // Job is an interface
    return jobs.get("helloWorldJob").start(step1).build();
}
```


## 3. Identify the correct statements.

A. A JobInstance is a single attempt to run a job.
B. A JobBuilder needs to be implemented by a user in order to create jobs.
C. The JobExecutionListener object is optional.
D. A task consists of tasklets.

### 3. Solution
A. Not true, this would describe a JobExecution. A job instance is a a built job.
B. Not true(skeptical), A JobBuilderFactory will return a JobBuilder from get method where the parameter is the Job bean.
C. This is true. However, it is good practice to implement in production.
D. Not true. A step consists of tasklets. Tasklets could be considered synonymous with a task.


## 4. What is this definition referring to?

It is an enum, which can take values of continuable or finished.
The continuable indicates that processing can continue.

### 4. Solution
This definition describes RepeatStatus used to indicate whether more work needs to be done or execution is successful/unsuccessful.


## 5. Find the problem with the following code and fix it.

```
public Tasklet creditTasklet() {
    return (new Tasklet() (
        @Override
        public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
            // processing credit transaction!
            return RepeatStatus.FINISHED;
        }
    ));
}
```

### 5. Solution
```
public Tasklet creditTasklet() {
    return (new Tasklet() (
        @Override
        public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
            // processing credit transaction!
            return RepeatStatus.FINISHED;
        }
    ));
}
```
