package com.smoothstack.springbatch.serviceitemreader.simpleclient.reader;

import com.smoothstack.springbatch.serviceitemreader.simpleclient.model.Transaction;
import com.smoothstack.springbatch.serviceitemreader.simpleclient.service.TransactionService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class TransactionServiceAdapter implements InitializingBean {

    @Autowired
    private TransactionService service;

    private ArrayList<Transaction> transactions;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.transactions = service.getTransactions();
    }

    public Transaction nextProduct(){
        if ( transactions.size() >0){
            return transactions.remove(0);
        }else
            return null;
    }

    public TransactionService getService() {
        return service;
    }

    public void setService(TransactionService service) {
        this.service = service;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setProducts(ArrayList<Transaction> products) {
        this.transactions = transactions;
    }
}
