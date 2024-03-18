package com.example.frauddetection.service.impl;

import com.example.frauddetection.model.Transaction;
import com.example.frauddetection.service.FraudDetectionService;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FraudDetectionServiceImpl implements FraudDetectionService {

    @Override
    public List<String> findFraudulentTransaction(List<Transaction> transactions, Instant fromDate,
                                                  String serviceId, Double thresholdPrice) {
        Map<String, Double> transactionWithTotalAmount = groupByAmountAndSum(transactions, fromDate, serviceId);
        return filterTransactionByThreshold(thresholdPrice, transactionWithTotalAmount);
    }

    private List<String> filterTransactionByThreshold(Double thresholdPrice,
                                                      Map<String, Double> transactionWithTotalAmount) {
        return transactionWithTotalAmount.entrySet().stream().filter(t -> t.getValue() < thresholdPrice)
                .map(Map.Entry::getKey).collect(Collectors.toList());
    }


    private Map<String, Double> groupByAmountAndSum(List<Transaction> transactions, Instant startDate,
                                                    String serviceId) {
        Instant windowedEnd = startDate.plus(Duration.ofMinutes(5));

        return transactions.stream()
                .filter(transaction -> transaction.getTimestamp().isAfter(startDate) &&
                        transaction.getTimestamp().isBefore(windowedEnd) &&
                        transaction.getServiceId().equals(serviceId)) // Additional condition for serviceId
                .collect(Collectors.groupingBy(Transaction::getUserId,
                        Collectors.summingDouble(Transaction::getAmount))
                );
    }


}
