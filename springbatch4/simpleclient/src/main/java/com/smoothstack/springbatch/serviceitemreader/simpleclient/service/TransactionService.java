package com.smoothstack.springbatch.serviceitemreader.simpleclient.service;

import com.smoothstack.springbatch.serviceitemreader.simpleclient.model.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class TransactionService {
    public ArrayList<Transaction> getTransactions(){
        RestTemplate restTemplate = new RestTemplate();
        String url="http://localhost:8070/transaction";
        Transaction[] transactions = restTemplate.getForObject(url, Transaction[].class);
        ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
        for( Transaction t : transactions)
            transactionList.add(t);
        return transactionList;
    }
}
