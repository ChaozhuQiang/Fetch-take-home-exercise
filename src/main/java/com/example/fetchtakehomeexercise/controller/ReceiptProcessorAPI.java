package com.example.fetchtakehomeexercise.controller;

import com.example.fetchtakehomeexercise.model.request.ReceiptProcessingRequest;
import com.example.fetchtakehomeexercise.model.response.GetPointsResponse;
import com.example.fetchtakehomeexercise.model.response.ReceiptProcessingResponse;
import com.example.fetchtakehomeexercise.service.ReceiptService;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("receipts")
@Slf4j
@Validated
public class ReceiptProcessorAPI {

    private final ReceiptService receiptService;

    @Autowired
    public ReceiptProcessorAPI(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<GetPointsResponse> getPoints(@PathVariable @Pattern(regexp = "^\\S+$") String id) {
        log.info("Getting points for receipt with id {}", id);
        int points = receiptService.getTotalPoints(id);
        GetPointsResponse response = new GetPointsResponse();
        response.setPoints(points);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value = "/process")
    public ResponseEntity<ReceiptProcessingResponse> processReceipt(@Validated @RequestBody ReceiptProcessingRequest request) {
        log.info("Processing receipt {}", request);
        String receiptId = receiptService.parseReceipt(request);
        ReceiptProcessingResponse response = new ReceiptProcessingResponse();
        response.setId(receiptId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
