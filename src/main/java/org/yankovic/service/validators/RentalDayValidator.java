package org.yankovic.service.validators;

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
    public boolean validateRentalDay(int numDays) {
        if (numDays < 1) {
            System.out.println(ErrorCodes.RENTAL_DAYS_OUT_OF_RANGE.getError());

            return false;
        }

        return true;
    }
}
