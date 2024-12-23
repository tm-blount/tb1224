package org.tb.service.validators;

/**
 * Instead of validating, for example, during program flow in the PricingCalculatorService
 * or elsewhere, encapsulate the validation in a separate object. This allows for greater
 * flexibility and will encourage code reuse.
 */
public class RentAgreementValidator {
    // Alternatively these could be internal to the RentAgreementValidator.
    // It seemed better to make them their own separate classes because
    // hypothetically you might want to validate a discount or rental day elsewhere,
    // and it's good to keep separation of concerns.
    private static final DiscountValidator discountValidator = new DiscountValidator();
    private static final RentalDayValidator rentalDayValidator = new RentalDayValidator();

    /**
     * Validate the discount and number of rental days
     *
     * @param discount the discount to apply
     * @param numDays  the number of days to rent the tool
     * @return true if discount and numDays are valid, ValidationError otherwise
     */
    public static boolean validateRentalAgreement(int discount, int numDays) {
        return DiscountValidator.validateDiscount(discount) &&
                rentalDayValidator.validateRentalDay(numDays);
    }
}
