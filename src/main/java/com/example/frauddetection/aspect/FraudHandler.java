package com.example.frauddetection.aspect;

import com.example.frauddetection.exception.FraudDetectionException;
import com.example.frauddetection.service.FraudDetectionService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

public class FraudHandler implements InvocationHandler {

    private final FraudDetectionService fraudDetectionService;

    public FraudHandler(final FraudDetectionService fraudDetectionService) {
        this.fraudDetectionService = fraudDetectionService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        validate(args);
        return method.invoke(fraudDetectionService, args);
    }

    private void validate(Object[] args) {
        Instant fromDate = (Instant) args[1];
        Duration windowDuration = Duration.ofMinutes(5);
        Instant currentTime = Instant.now();

        // Calculate the duration between fromDate and currentTime
        Duration duration = Duration.between(fromDate, currentTime);

        // Check if the duration is less than the sliding window duration
        if (duration.compareTo(windowDuration) < 0) {
            throw new FraudDetectionException("Sliding window exceeded. Minimum duration required is 5 minutes");
        }
    }

}
