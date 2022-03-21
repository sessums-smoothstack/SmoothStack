package com.smoothstack.controljobflow.utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.smoothstack.controljobflow.models.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FileUtils {

    private final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private String fileName;
    private CSVReader CSVReader;
    private CSVWriter CSVWriter;
    private FileReader fileReader;
    private FileWriter fileWriter;
    private File file;

    public FileUtils(String fileName) {
        this.fileName = fileName;
    }

    public Transaction readLine() {
        try {
            if (CSVReader == null) initReader();
            String[] transaction = CSVReader.readNext();
            if (transaction == null) return null;
            return new Transaction(transaction[0], transaction[1], transaction[2], transaction[3], transaction[4],
                    transaction[5], transaction[6], transaction[7], transaction[8], transaction[9], transaction[10],
                    transaction[11], transaction[12], transaction[13], transaction[14], transaction[15], transaction[16]);

        } catch (Exception e) {
            logger.error("Error while reading line in file: " + this.fileName);
            return null;
        }
    }

    private void initReader() throws Exception {
        logger.info("init reader");
        ClassLoader classLoader = this
                .getClass()
                .getClassLoader();
        if (file == null) file = new File("src/main/resources/files/input/transactions.csv");
        if (fileReader == null) fileReader = new FileReader(file);
        if (CSVReader == null) CSVReader = new CSVReader(fileReader);
    }


    public void closeReader() {
        try {
            CSVReader.close();
            fileReader.close();
        } catch (IOException e) {
            logger.error("Error while closing reader.");
        }
    }

}