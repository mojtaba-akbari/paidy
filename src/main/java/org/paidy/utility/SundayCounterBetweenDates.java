package org.paidy.utility;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SundayCounterBetweenDates {
    // Pattern Start And End Date dd-mm-yyyy //
    final static String dateFormat = "dd-MM-yyyy"; // Question has wrong format dd-mm-yy (mm is minutes)
    final static short loopCounterDayAdded = 1; // Day Plus inside of loop

    public static int sundayCounterBetweenDates(String startStringDate, String endStringDate) {
        // Reformat Date To The Pattern //
        DateTimeFormatter dateFormatParse = DateTimeFormatter.ofPattern(dateFormat, Locale.ENGLISH);

        LocalDate startDate = LocalDate.parse(startStringDate, dateFormatParse);
        LocalDate endDate = LocalDate.parse(endStringDate, dateFormatParse);

        // Count Of Sundays //
        int sundaysCounter = 0;

        // Loop From First Of Day Of Start Time -> End Day / Step 1 day //
        for (LocalDate date = startDate;
             !date.isAfter(endDate);
             date = date.plusDays(loopCounterDayAdded)) {
            if (date.getDayOfWeek() == DayOfWeek.SUNDAY) { // Point To The Sunday Day
                sundaysCounter++;
            }
        }

        return sundaysCounter;
    }
}
