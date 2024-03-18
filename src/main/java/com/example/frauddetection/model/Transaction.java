package com.example.frauddetection.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class Transaction {

    private Instant timestamp;
    private Double amount;
    private String userId;
    private String serviceId;

}
