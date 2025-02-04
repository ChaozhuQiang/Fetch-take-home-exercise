package com.example.fetchtakehomeexercise.database;

import com.example.fetchtakehomeexercise.exception.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Database {

    private Map<String, Integer> map;

    public Database() {
        map = new HashMap<>();
    }

    public int getPointsById(String id) {
        if (map.containsKey(id)) {
            return map.get(id);
        }
        throw new NotFoundException("No receipt found for that ID.", id);
    }

    public String save(String id, int points) {
        map.put(id, points);
        return id;
    }
}
