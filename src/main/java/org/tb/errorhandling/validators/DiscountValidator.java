package org.tb.errorhandling.validators;

import org.tb.errorhandling.exceptions.DiscountOutOfRangeException;

import static org.tb.errorhandling.ErrorCodes.DISCOUNT_OUT_OF_RANGE;

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
    public static boolean validateDiscount(int discount) throws DiscountOutOfRangeException {
        if (discount < 0 || discount > 100) {
            throw new DiscountOutOfRangeException(DISCOUNT_OUT_OF_RANGE.getError());
        }

        return true;
    }
}
