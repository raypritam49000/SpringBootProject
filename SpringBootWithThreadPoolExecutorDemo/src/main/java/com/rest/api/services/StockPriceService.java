package com.rest.api.services;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class StockPriceService {

    Logger logger = LoggerFactory.getLogger(StockPriceService.class);

    @Async
    public  CompletableFuture<Double>  getStockPrice(String stockName) throws InterruptedException {
        logger.info("Starting: getStockPrice for company {} with thread {}", stockName, Thread.currentThread().getName());
        Thread.sleep(1000);
        Double price;
        switch (stockName) {
            case "company1":
                price = 50.0;
                break;
            case "company2":
                price = 20.0;
                break;
            case "company3":
                price= 15.0;
                break;
            default:
                price = null;

        }
        logger.info("Complete: getStockPrice for company {} with thread {}", stockName, Thread.currentThread().getName());

        return CompletableFuture.completedFuture(price);
    }
}