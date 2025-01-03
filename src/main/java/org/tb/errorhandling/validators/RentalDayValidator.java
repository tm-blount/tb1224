package org.tb.errorhandling.validators;

import org.tb.errorhandling.ErrorCodes;
import org.tb.errorhandling.exceptions.RentalDaysOutOfRangeException;

/**
 * Make this validator an actual class so if there's expansion
 * later we already have the object ready and the current validation is
 * properly encapsulated.
 */
public class RentalDayValidator {
    /**
     * Validate the number of days this tool is rented.
     * The tool must be rented one or more days.
     *
     * @param numDays days to rent this tool
     * @return true if numDays >= 1
     */
    public static boolean validateRentalDay(int numDays) throws RentalDaysOutOfRangeException {
        if (numDays < 1) {
            throw new RentalDaysOutOfRangeException(ErrorCodes.RENTAL_DAYS_OUT_OF_RANGE.getError());
        }

        return true;
    }
}
