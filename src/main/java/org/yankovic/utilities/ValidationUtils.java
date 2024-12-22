package org.yankovic.utilities;

import org.yankovic.service.validators.RentAgreementValidator;

public class ValidationUtils {
    public static boolean rentalAgreementIsValid(int discount, int numDaysToRent) {
        return RentAgreementValidator.validateRentalAgreement(discount, numDaysToRent);
    }
}
