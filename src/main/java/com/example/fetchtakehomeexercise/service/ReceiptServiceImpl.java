package com.example.fetchtakehomeexercise.service;

import com.example.fetchtakehomeexercise.model.request.ReceiptProcessingRequest;
import com.example.fetchtakehomeexercise.repository.ReceiptRepository;
import com.example.fetchtakehomeexercise.utils.ServiceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;

    @Autowired
    public ReceiptServiceImpl(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    @Override
    public int getTotalPoints(String receiptId){
        return receiptRepository.getPointsById(receiptId);
    }

    @Override
    public String parseReceipt(ReceiptProcessingRequest receiptProcessingRequest) {
        int totalPoints = 0;
        int count = ServiceUtils.countAlphanumeric(receiptProcessingRequest.getRetailer());
        totalPoints += count;
        log.info("One point for every alphanumeric character in the retailer name - earning points: {}", count);
        if (ServiceUtils.isRoundDollarAmount(receiptProcessingRequest.getTotal())) {
            totalPoints += 50;
            log.info("50 points if the total is a round dollar amount with no cents - earning points: 50");
        }
        if (ServiceUtils.isMultiple(receiptProcessingRequest.getTotal(), 0.25)) {
            totalPoints += 25;
            log.info("25 points if the total is a multiple of 0.25 - earning points: 25");
        }
        int pointsForItemCount = receiptProcessingRequest.getItems().size() / 2 * 5;
        totalPoints += pointsForItemCount;
        log.info("5 points for every two items on the receipt - earning points: {}", pointsForItemCount);
        int pointsForItemDescription = receiptProcessingRequest.getItems().stream()
                .filter(item -> ServiceUtils.getTrimmedLength(item.getShortDescription()) % 3 == 0) // Check if the trimmed length is a multiple of 3
                .mapToInt(item -> {
                    double price = Double.parseDouble(item.getPrice()); // Parse the price from String to double
                    return (int)Math.ceil(price * 0.2); // Round up to the nearest integer
                })
                .sum();
        totalPoints += pointsForItemDescription;
        log.info("If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2 and round up to the nearest integer - earning points: {}", pointsForItemDescription);
        if (ServiceUtils.isDateOdd(receiptProcessingRequest.getPurchaseDate())) {
            totalPoints += 6;
            log.info("6 points if the day in the purchase date is odd - earning points: 6");
        }
        if (ServiceUtils.isBetween2And4(receiptProcessingRequest.getPurchaseTime())) {
            totalPoints += 10;
            log.info("10 points if the time of purchase is after 2:00pm and before 4:00pm - earning points: 10");
        }
        UUID receiptId = UUID.randomUUID();
        return receiptRepository.saveReceipt(receiptId.toString(), totalPoints);
    }
}
