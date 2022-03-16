package com.smoothstack.springbatch6.processor;

import com.smoothstack.springbatch6.models.Transaction;
import org.springframework.batch.item.ItemProcessor;

public class TransactionProcessor implements ItemProcessor<Transaction, Transaction> {
    @Override
    public Transaction process(Transaction transaction) throws Exception {
        if(transaction.getCounty().equals("Saare maakond")) {
            System.out.println("skipping county: Saare maakond");
            return null;
        }
        else {
//            System.out.println(transaction.toString());
            return transaction;
        }

    }
}