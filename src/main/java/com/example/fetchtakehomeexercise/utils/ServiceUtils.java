package com.example.fetchtakehomeexercise.utils;

import java.time.LocalDate;
import java.time.LocalTime;

public class ServiceUtils {

    public static int countAlphanumeric(String input) {
        int count = 0;
        for (char ch : input.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                count++;
            }
        }
        return count;
    }

    public static boolean isRoundDollarAmount(String input) {
        double amount = Double.parseDouble(input);
        return amount % 1 == 0;
    }

    public static boolean isMultiple(String input, double amount) {
        double number = Double.parseDouble(input);
        return number % amount == 0;
    }

    public static int getTrimmedLength(String input) {
        return input.trim().length();
    }

    public static boolean isDateOdd(LocalDate date) {
        return date.getDayOfMonth() % 2 == 1;
    }

    public static boolean isBetween2And4(LocalTime time) {
        // Define the range 14:00 (2 PM) to 16:00 (4 PM)
        LocalTime startTime = LocalTime.of(14, 0); // 2:00 PM
        LocalTime endTime = LocalTime.of(16, 0); // 4:00 PM

        // Check if the time is between 14:00 and before 16:00
        return !time.isBefore(startTime) && !time.isAfter(endTime);
    }
}
