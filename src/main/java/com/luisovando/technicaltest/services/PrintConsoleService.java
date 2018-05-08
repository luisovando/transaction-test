package com.luisovando.technicaltest.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.luisovando.technicaltest.entities.Transaction;
import com.luisovando.technicaltest.tools.jackson.mapper.JsonMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PrintConsoleService {

    @Autowired
    private JsonMapperService mapperService;


    public void printItem(Object entity) throws IOException {
        String result = mapperService.toJson(entity);
        System.out.println(result);
    }

    public void printCollection(List<Transaction> transactions) {
        try {
            String result = mapperService.toJsonCollection(transactions);
            System.out.println(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
