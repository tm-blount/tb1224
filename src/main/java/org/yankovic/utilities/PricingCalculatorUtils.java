package org.yankovic.utilities;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * This is one of those classes that might end up renamed
 * "CalendarUtils" or have the calendar methods refactored out,
 * but this is acceptable given the specs.
 * <br />
 * A utility class for dealing with primarily the following:
 * <br />
 * 1. Determine if Jul 4th occurs on a weekend and, if so, the closest day
 *    before/after the holiday (Saturday = Friday, Sunday = Monday)
 * 2. Determine if the date in question is Labor Day
 * 3. Calculate the total number of chargeable days
 */
public final class PricingCalculatorUtils {
    private PricingCalculatorUtils() {
    }

    public int getNumberOfChargeableDays() { return 0; }

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
     *
     * @return DayOfWeek when holiday is observed
     */
    public DayOfWeek getIndependenceDay(String date) {
        return DayOfWeek.FRIDAY;
    }

    /**
     * If it is Labor Day (first Monday in September), then this
     * is a holiday charge and may or may not be added to the final
     * total depending on th tool.
     *
     * @param date the date to consider
     *
     * @return true if Labor Day/first Monday in September
     */
    public boolean isLaborDay(String date) {
        return false;
    }

    // TODO
    private LocalDate formatDateString() {
        return LocalDate.now();
    }
}