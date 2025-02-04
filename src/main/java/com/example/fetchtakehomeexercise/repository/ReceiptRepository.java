package com.example.fetchtakehomeexercise.repository;

public interface ReceiptRepository {

    int getPointsById(String id);

    String saveReceipt(String id, int points);
}
