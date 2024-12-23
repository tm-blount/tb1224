package org.tb.utilities;

import org.tb.service.validators.RentAgreementValidator;

/**
 * Separate the validations into their own utils class, because validations are
 * likely to be one of the first things to be extended.
 */
public class ValidationUtils {
    public static boolean rentalAgreementIsValid(int discount, int numDaysToRent) {
        return RentAgreementValidator.validateRentalAgreement(discount, numDaysToRent);
    }
}
