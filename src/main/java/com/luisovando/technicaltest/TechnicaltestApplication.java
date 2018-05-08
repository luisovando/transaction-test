package com.luisovando.technicaltest;

import com.luisovando.technicaltest.entities.Transacted;
import com.luisovando.technicaltest.entities.Transaction;
import com.luisovando.technicaltest.services.PrintConsoleService;
import com.luisovando.technicaltest.services.TransactionDiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

import static java.lang.System.exit;

@SpringBootApplication
public class TechnicaltestApplication implements CommandLineRunner {

    @Autowired
    private TransactionDiskService service;

    @Autowired
    private PrintConsoleService console;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TechnicaltestApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        int userId = Integer.parseInt(args[0]);
        switch (args.length) {
            case 2:
                if (args[1].equalsIgnoreCase("list")) {
                    List<Transaction> transactions = service.listTransactionsByUser(userId);
                    console.printCollection(transactions);
                } else if (args[1].equalsIgnoreCase("sum")) {
                    double total = service.totalTransactedByUser(userId);
                    console.printItem(new Transacted(userId, total));
                } else {
                    Transaction transaction = service.getTransactionByUser(userId, args[1]);

                    if (transaction == null) {
                        System.out.println("Transaction not found");
                    } else {
                        console.printItem(transaction);
                    }
                }

                break;
            case 3:
                if (args[1].equalsIgnoreCase("add")) {
                    Transaction transaction = service.saveTransaction(userId, args[2]);
                    console.printItem(transaction);
                } else {
                    System.out.println("Invalid action");
                }
                break;
            default:
                System.out.println("Invalid request");
                break;
        }

        exit(0);
    }
}
