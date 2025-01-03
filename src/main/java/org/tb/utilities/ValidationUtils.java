package org.tb.utilities;

import org.tb.errorhandling.exceptions.DiscountOutOfRangeException;
import org.tb.errorhandling.exceptions.RentalDaysOutOfRangeException;
import org.tb.errorhandling.validators.RentAgreementValidator;

/**
 * Separate the validations into their own utils class, because validations are
 * likely to be one of the first things to be extended.
 */
public class ValidationUtils {
    public static boolean rentalAgreementIsValid(int discount, int numDaysToRent) throws DiscountOutOfRangeException, RentalDaysOutOfRangeException {
        return RentAgreementValidator.validateRentalAgreement(discount, numDaysToRent);
    }
}
