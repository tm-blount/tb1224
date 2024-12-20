package org.yankovic.utilities;

import java.time.DayOfWeek;
import java.time.LocalDate;
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
        LocalDate laborDate = LocalDate.parse(date, formatter);

        if (laborDate.getMonthValue() != 9) {
            System.out.println("\n\n\n\nnot Labor day!\n\n\n\n");
            return false;
        }
        else {
            System.out.println("\n\n\n\nLabor day!\n\n\n\n");
            //LocalDate firstMonday = laborDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
            LocalDate firstMonday = LocalDate.of(laborDate.getYear(), 9, 1)
                    .with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));

            return laborDate.equals(firstMonday);
        }
    }

    // TODO
    public static int numberOfChargeableDays() {
    }

    public int getNumberOfChargeableDays() {
        return 0;
    }

    /**
     * If Jul 4th (Independence Day) on the given date occurs on Saturday, return
     * Friday. If Sunday, return Monday.
     * <br />
     * If not on the weekend, return the current DayOfWeek.
     * <br />
     * This means that tools with holiday charges are charged but
     * weekend charges are skipped.
     *
     * @param date the date to consider
     * @return DayOfWeek when holiday is observed
     */
    public DayOfWeek getIndependenceDay(String date) {
        return DayOfWeek.FRIDAY;
    }

    // TODO
    private LocalDate formatDateString() {
        return LocalDate.now();
    }
}