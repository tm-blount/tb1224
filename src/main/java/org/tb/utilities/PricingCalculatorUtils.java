package org.tb.utilities;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * This is one of those classes that might end up renamed
 * "CalendarUtils" or have the calendar methods refactored out,
 * but this is acceptable given the specs.
 * <br />
 * A utility used for various (mainly) pricing-related concerns
 */
public final class PricingCalculatorUtils {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");

    private PricingCalculatorUtils() {
    }

    /**
     * If it is Labor Day (first Monday in September), then this
     * is a holiday charge and may or may not be added to the final
     * total depending on th tool.
     *
     * @param date the date to consider
     * @return true if it's Labor Day
     */
    public static boolean isLaborDay(LocalDate date) {
        if (date.getMonthValue() != 9) {
            return false;
        }
        else {
            LocalDate firstMonday = java.time.LocalDate.of(date.getYear(), Month.SEPTEMBER, 1)
                    .with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));

            return date.equals(firstMonday);
        }
    }

    public static boolean isIndependenceDayOnWeekend(LocalDate inputDate) {
        return checkIndependenceDayMonth(inputDate) &&
                dateIsWeekendDay(inputDate.getDayOfWeek());
    }

    public static boolean isIndependenceDay(LocalDate inputDate) {
        return checkIndependenceDayMonth(inputDate) &&
                !dateIsWeekendDay(inputDate.getDayOfWeek());
    }

    public static boolean dateIsWeekendDay(DayOfWeek dayOfWeek) {
        return (dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY));
    }

    public static LocalDate formatDateString(String date) {
        return LocalDate.parse(date, formatter);
    }

    private static boolean checkIndependenceDayMonth(LocalDate inputDate) {
        return (inputDate.getMonth().equals(Month.JULY) && inputDate.getDayOfMonth() == 4);
    }
}