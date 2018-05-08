package com.luisovando.technicaltest.repositories;

import com.luisovando.technicaltest.entities.Transaction;

import java.text.ParseException;
import java.util.List;

public interface TransactionRepository {
    Transaction saveTransaction(int userId, String attributes);
    Transaction getTransactionByUser(int userId, String transactionId) throws ParseException;
    List<Transaction> listTransactionsByUser(int userId) throws ParseException;
    double totalTransactedByUser(int userId);
}
