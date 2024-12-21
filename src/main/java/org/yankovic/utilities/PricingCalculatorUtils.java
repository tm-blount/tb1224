package org.yankovic.utilities;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.EnumSet;
import java.util.Set;

/**
 * This is one of those classes that might end up renamed
 * "CalendarUtils" or have the calendar methods refactored out,
 * but this is acceptable given the specs.
 * <br />
 * A utility class for dealing with primarily the following:
 * <br />
 * 1. Determine if Jul 4th occurs on a weekend and, if so, the closest day
 * before/after the holiday (Saturday = Friday, Sunday = Monday)
 * 2. Determine if the date in question is Labor Day
 * 3. Calculate the total number of chargeable days
 */
public final class PricingCalculatorUtils {
    private static final Set<DayOfWeek> weekendSet = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");

    private PricingCalculatorUtils() {
    }

    /**
     * If it is Labor Day (first Monday in September), then this
     * is a holiday charge and may or may not be added to the final
     * total depending on th tool.
     *
     * @param date the date to consider
     * @return TODO wat
     */
    public static boolean isLaborDay(String date) {
        // TODO error support
        // First, try to parse the String date into a LocalDate
        LocalDate laborDate = formatDateString(date);

        if (laborDate.getMonthValue() != 9) {
            return false;
        }
        else {
            LocalDate firstMonday = java.time.LocalDate.of(laborDate.getYear(), 9, 1)
                    .with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));

            return laborDate.equals(firstMonday);
        }
    }

    /**
     * @param date the date to consider
     * @return boolean if Jul 4th is observed on a weekend
     */
    public static boolean isIndependenceDayOnWeekend(String date) {
        LocalDate inputDate = formatDateString(date);

        // Is it actually Jul 4th?
        LocalDate july4th = LocalDate.of(inputDate.getYear(), Month.JULY, 4);

        // Get dof for Jul 4th
        DayOfWeek dayOfWeek = july4th.getDayOfWeek();

        return (dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY));
    }

    public static LocalDate formatDateString(String date) {
        return LocalDate.parse(date, formatter);
    }
}