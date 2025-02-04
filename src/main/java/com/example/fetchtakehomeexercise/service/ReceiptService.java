package com.example.fetchtakehomeexercise.service;

import com.example.fetchtakehomeexercise.model.request.ReceiptProcessingRequest;

public interface ReceiptService {

    int getTotalPoints(String receiptId);

    String parseReceipt(ReceiptProcessingRequest receiptProcessingRequest);
}