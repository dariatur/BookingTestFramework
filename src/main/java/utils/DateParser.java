package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {
        public static LocalDate parseDate(String shortDate) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate parsed = LocalDate.parse(shortDate + "." + LocalDate.now().getYear(), formatter);

            return parsed;
        }
}
