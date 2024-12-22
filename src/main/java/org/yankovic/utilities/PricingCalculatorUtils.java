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
 * A utility used for various (mainly) pricing-related concerns
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
     * @return true if it's Labor Day
     */
    public static boolean isLaborDay(String date) {
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

    // TODO WHY'D WE HAVE TO THROW THAT DANG TEA IN THE HARBOR
    public static boolean isIndependenceDayOnWeekend(LocalDate inputDate) {
        // Is it actually Jul 4th?
        LocalDate july4th = LocalDate.of(inputDate.getYear(), Month.JULY, 4);

        // Get dof for Jul 4th
        DayOfWeek dayOfWeek = july4th.getDayOfWeek();

        return dateIsWeekendDay(dayOfWeek);
    }

    public static boolean isIndependenceDay(String date) {
        LocalDate inputDate = formatDateString(date);

        return isIndependenceDayOnWeekend(inputDate) || (inputDate.getMonth().equals(Month.JULY) && inputDate.getDayOfMonth() == 7);
    }

    public static boolean dateIsWeekendDay(DayOfWeek dayOfWeek) {
        return (dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY));
    }

    public static LocalDate formatDateString(String date) {
        return LocalDate.parse(date, formatter);
    }
}