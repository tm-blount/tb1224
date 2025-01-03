package org.tb.errorhandling.validators;

import org.tb.errorhandling.exceptions.DiscountOutOfRangeException;
import org.tb.errorhandling.exceptions.RentalDaysOutOfRangeException;

/**
 * Instead of validating, for example, during program flow in the PricingCalculatorService
 * or elsewhere, encapsulate the validation in a separate object. This allows for greater
 * flexibility and will encourage code reuse.
 */
public class RentAgreementValidator {
    /**
     * Validate the discount and number of rental days
     *
     * @param discount the discount to apply
     * @param numDays  the number of days to rent the tool
     * @return true if discount and numDays are valid, ValidationError otherwise
     */
    public static boolean validateRentalAgreement(int discount, int numDays) throws DiscountOutOfRangeException, RentalDaysOutOfRangeException {
        return DiscountValidator.validateDiscount(discount) &&
                RentalDayValidator.validateRentalDay(numDays);
    }
}
