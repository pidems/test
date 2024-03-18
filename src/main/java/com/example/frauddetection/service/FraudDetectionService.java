package com.example.frauddetection.service;

import com.example.frauddetection.model.Transaction;

import java.time.Instant;
import java.util.List;

public interface FraudDetectionService {

    List<String> findFraudulentTransaction(final List<Transaction> transactions,final Instant fromDate,
                                           final String serviceId, final Double thresholdPrice);
}
