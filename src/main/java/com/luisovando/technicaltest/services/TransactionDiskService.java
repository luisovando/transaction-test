package com.luisovando.technicaltest.services;

import com.luisovando.technicaltest.entities.Transaction;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.System.exit;

@Service
public class TransactionDiskService extends TransactionService {

    private static final String FILENAME = "/database.txt";

    private String pathFile;

    public TransactionDiskService() {
        Path currentRelativePath = Paths.get("");
        String path = currentRelativePath.toAbsolutePath().toString();
        this.pathFile = path.concat(TransactionDiskService.FILENAME);
    }

    @Override
    public Transaction saveTransaction(int userId, String attributes) {
        Transaction transaction = null;
        try {
            transaction = this.fillTransaction(attributes);
            transaction.setTransactionId(String.valueOf(UUID.randomUUID()));
            File fout = new File(pathFile);
            BufferedWriter bw = new BufferedWriter(new FileWriter(fout, true));
            bw.write(this.mapperService.toJson(transaction));
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transaction;
    }

    @Override
    public Transaction getTransactionByUser(int userId, String transactionId) {
        Transaction transaction = null;
        File file = new File(pathFile);
        String userNeedle = String.format("\"user_id\":%d", userId);
        String transactionNeedle = String.format("\"transaction_id\":\"%s\"", transactionId);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                if(row.contains(transactionNeedle) && row.contains(userNeedle)) {
                    transaction = this.fillTransaction(row);
                    break;
                }
            }
        }
        catch (FileNotFoundException fnfe) {
            System.out.println("File database not found, run create transaction action for create one.");
            exit(0);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return transaction;
    }

    @Override
    public List<Transaction> listTransactionsByUser(int userId) {
        List<Transaction> transactions =  new ArrayList<>();
        File file = new File(pathFile);
        String needle = String.format("\"user_id\":%s", String.valueOf(userId));
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                if(row.contains(needle)) {
                    Transaction transaction = this.fillTransaction(row);
                    transactions.add(transaction);
                }
            }
        }
        catch (FileNotFoundException fnfe) {
            System.out.println("File database not found, run create transaction action for create one.");
            exit(0);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        transactions.sort((t1, t2) -> {
            if (t1.getDate() == null || t2.getDate() == null)
                return 0;
            return t2.getDate().compareTo(t1.getDate());
        });

        return transactions;
    }

    @Override
    public double totalTransactedByUser(int userId) {
        List<Transaction> transactions = this.listTransactionsByUser(userId);

        return transactions.stream().filter(transaction -> transaction.getAmount() > 0).mapToDouble(Transaction::getAmount).sum();
    }
}
