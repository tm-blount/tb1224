package org.tb.errorhandling;

public enum ErrorCodes {
    DISCOUNT_OUT_OF_RANGE("Discount must be between 0 - 100"),
    RENTAL_DAYS_OUT_OF_RANGE("A tool must be rented at least one day"),

    TOOL_NOT_FOUND("A tool with that id was not found");

    private final String error;

    ErrorCodes(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
