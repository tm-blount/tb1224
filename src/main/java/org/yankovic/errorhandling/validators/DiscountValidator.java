package org.yankovic.errorhandling.validators;

import org.yankovic.errorhandling.ErrorCodes;

/**
 * Make this validator an actual class so if there's expansion
 * later we already have the object ready and the current validation is
 * properly encapsulated.
 */
public class DiscountValidator {
    /**
     * Validate discount. May be 1 - 100.
     *
     * @param discount the discount to apply
     * @return true if the discount is between 1 - 100 percent
     */
    public static boolean validateDiscount(int discount) {
        if (discount < 0 || discount > 100) {
            System.out.println(ErrorCodes.DISCOUNT_OUT_OF_RANGE.getError());

            return false;
        }

        return true;
    }
}
