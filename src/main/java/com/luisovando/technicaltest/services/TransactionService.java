package com.luisovando.technicaltest.services;

import com.luisovando.technicaltest.entities.Transaction;
import com.luisovando.technicaltest.repositories.TransactionRepository;
import com.luisovando.technicaltest.tools.jackson.mapper.JsonMapperService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

abstract class TransactionService implements TransactionRepository {

    @Autowired
    protected JsonMapperService mapperService;

    public Transaction fillTransaction(String attributes) throws IOException {
        Transaction transaction = mapperService.fromJson(attributes, Transaction.class);
        return transaction;
    }
}
