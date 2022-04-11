package seedu.ibook.model.product;

import static seedu.ibook.commons.util.AppUtil.checkArgument;
import static seedu.ibook.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a price range in the iBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidPriceRange(String, String)}
 */
public class PriceRange {

    public static final String MESSAGE_CONSTRAINTS = "Start price should be less than or equal to end price";

    public final Price startPrice;
    public final Price endPrice;

    /**
     * Constructs a {@code Price} from string.
     *
     * @param startPrice A valid price.
     */
    public PriceRange(String startPrice, String endPrice) {
        requireAllNonNull(startPrice, endPrice);
        checkArgument(isValidPriceRange(startPrice, endPrice), MESSAGE_CONSTRAINTS);
        Price parsedStartPrice = new Price(startPrice);
        Price parsedEndPrice = new Price(endPrice);

        this.startPrice = parsedStartPrice;
        this.endPrice = parsedEndPrice;
    }

    /**
     * Constructs a {@code Price} from double.
     *
     * @param startPrice A valid price.
     * @param endPrice A valid price.
     */
    public PriceRange(Price startPrice, Price endPrice) {
        this.startPrice = startPrice;
        this.endPrice = endPrice;
    }

    /**
     * Gets the start price.
     */
    public Price getStartPrice() {
        return startPrice;
    }

    /**
     * Gets the end price.
     */
    public Price getEndPrice() {
        return endPrice;
    }

    /**
     * Checks if the start price and end price is valid.
     *
     * @param startPrice price to check.
     * @param endPrice price to check.
     * @return Result of test.
     */
    public static boolean isValidPriceRange(String startPrice, String endPrice) {
        return Price.isValidPrice(startPrice)
            && Price.isValidPrice(endPrice)
            && !new Price(startPrice).isMoreThan(new Price(endPrice));
    }

    @Override
    public String toString() {
        return String.format("%s-%s", startPrice, endPrice);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof PriceRange // instanceof handles nulls
            && startPrice.equals(((PriceRange) other).startPrice) // state check
            && endPrice.equals(((PriceRange) other).endPrice)); // state check
    }
}
