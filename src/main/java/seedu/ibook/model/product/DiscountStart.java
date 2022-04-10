package seedu.ibook.model.product;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Product's discount start in the iBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidDiscountStart(String)}
 */
public class DiscountStart {

    public static final String DEFAULT_DISCOUNT_START = "0";

    public static final String MESSAGE_CONSTRAINTS =
            "Discount Start should be a non-negative integer at most 999999";

    public static final String VALIDATION_REGEX = "|0*\\d{1,6}";

    public final Integer discountStart;

    /**
     * Constructs a {@code DiscountStart}.
     *
     * @param discountStart A valid discount start.
     */
    public DiscountStart(String discountStart) {
        requireNonNull(discountStart);
        checkArgument(isValidDiscountStart(discountStart), MESSAGE_CONSTRAINTS);
        if (discountStart.equals("")) {
            discountStart = DEFAULT_DISCOUNT_START;
        }
        this.discountStart = Integer.parseInt(discountStart);
        assert this.discountStart >= 0; // ensure that the discount rate is not negative
    }

    /**
     * Checks if the string is valid as per {@code VALIDATION_REGEX}.
     *
     * @param test String to test.
     * @return Result of test.
     */
    public static boolean isValidDiscountStart(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public boolean hasDiscountStarted(long dayBeforeExpiry) {
        return dayBeforeExpiry < discountStart;
    }

    @Override
    public String toString() {
        return String.valueOf(discountStart);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DiscountStart // instanceof handles nulls
                && discountStart.equals(((DiscountStart) other).discountStart)); // state check
    }

    @Override
    public int hashCode() {
        return discountStart.hashCode();
    }

}
