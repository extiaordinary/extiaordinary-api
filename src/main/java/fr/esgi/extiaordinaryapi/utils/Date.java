package fr.esgi.extiaordinaryapi.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Date {

    public static LocalDateTime convertDate(String dateString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            return LocalDateTime.parse(dateString, formatter);
        } catch (Exception e) {
            throw new RuntimeException("Date format is not valid");
        }
    }
}
