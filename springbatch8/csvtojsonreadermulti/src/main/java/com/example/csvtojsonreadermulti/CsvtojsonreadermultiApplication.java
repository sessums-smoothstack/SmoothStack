package com.example.csvtojsonreadermulti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class CsvtojsonreadermultiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvtojsonreadermultiApplication.class, args);
	}

}
