package com.smoothstack.springbatch.serviceitemreader.simpleservice;

import com.smoothstack.springbatch.serviceitemreader.simpleservice.model.Transaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TransactionController {

    @GetMapping("/transctions")
    public List<Transaction> getTransactions(){
        ArrayList<Transaction> transactions = new ArrayList<>();

        return transactions;
    }
}
