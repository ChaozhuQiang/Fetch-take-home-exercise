package com.example.fetchtakehomeexercise.repository;

import com.example.fetchtakehomeexercise.database.Database;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class ReceiptRepositoryImpl implements ReceiptRepository {

    private final Database database;

    @Autowired
    public ReceiptRepositoryImpl(Database database) {
        this.database = database;
    }

    @Override
    public int getPointsById(String id) {
        log.info("Getting points from database by id {}", id);
        return database.getPointsById(id);
    }

    @Override
    public String saveReceipt(String id, int points) {
        log.info("Saving points to database with id {} and points {}", id, points);
        return database.save(id, points);
    }
}