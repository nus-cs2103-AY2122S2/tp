package seedu.address.model.order;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents an order's details in ReadyBakey.
 * Guarantees: immutable; is valid as declared in {@link #isValidDetails(String)}
 */
public class Details {
    public static final int ITEM_SIZE_LIMIT = 30;
    public static final int QUANTITY_SIZE_MIN_LIMIT = 1;
    public static final int QUANTITY_SIZE_MAX_LIMIT = 99;
    public static final String ITEM_MESSAGE_LIMIT = "An order item cannot contain more than "
            + ITEM_SIZE_LIMIT + " characters.";
    public static final String QUANTITY_MESSAGE_LIMIT = "Details can have a minimum quantity of "
            + QUANTITY_SIZE_MIN_LIMIT + " up to a maximum of "
            + QUANTITY_SIZE_MAX_LIMIT + ".";
    public static final String MESSAGE_CONSTRAINTS = "Details consists of a number (quantity), "
            + "followed by a colon (:) and by alphabetical or whitespace characters (order item). "
            + "\n EXAMPLE: d/1: vanilla cake";
    public static final String FIND_ITEM_MESSAGE_CONSTRAINTS = "findo only supports finding items and not quantities\n"
            + "EXAMPLE: findo d/Cake";
    /*
     * Details passed in should follow convention eg. "1 : chocolate cake with sprinkles".
     * Characters preceding the colon should only be numerical. This is parsed as the quantity.
     * Characters following the colon should be either alphabets or whitespace. This is parsed as the order item.
     */
    private static final String ORDER_QUANTITY_REGEX_KEYWORD = "orderQuantity";
    private static final String ORDER_ITEM_REGEX_KEYWORD = "orderItem";
    private static final String VALIDATION_REGEX = String.format(
            "^(\\s+)?(?<%s>\\d+)\\s*:{1}\\s*(?<%s>((\\w|\\w +)+))(\\s+)?$",
            ORDER_QUANTITY_REGEX_KEYWORD, ORDER_ITEM_REGEX_KEYWORD);
    private static final String VALIDATION_REGEX_QUANTITY = "^([0-9]+:).*";
    private static final Pattern DETAIL_PATTERN = Pattern.compile(VALIDATION_REGEX);

    public final String value;
    public final String item;
    public final int quantity;

    /**
     * Constructs an {@code Details}.
     *
     * @param inputDetails A valid details.
     * @param item A valid order item.
     * @param quantity A valid order quantity.
     */
    public Details(String inputDetails, String item, int quantity) {
        value = inputDetails;
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * Constructs an {@code Details}. For backwards compatability with SampleDataUtil and test cases
     *
     * @param details A valid details.
     */
    public Details(String details) {
        requireNonNull(details);
        checkArgument(isValidDetails(details), MESSAGE_CONSTRAINTS);
        value = details;
        quantity = Integer.parseInt(parseValidatedQuantity(value));
        item = parseValidatedItem(value);
    }

    /**
     * Returns a valid quantity passed in input.
     */
    public static String parseValidatedQuantity(String inputDetails) {
        String quantityParsed = parseDetails(inputDetails, ORDER_QUANTITY_REGEX_KEYWORD);
        assert quantityParsed != null; // shouldn't return null due to isValidDetails and trim checks
        return quantityParsed;
    }

    /**
     * Returns a valid item passed in input.
     */
    public static String parseValidatedItem(String inputDetails) {
        String itemParsed = parseDetails(inputDetails, ORDER_ITEM_REGEX_KEYWORD);
        assert itemParsed != null; // shouldn't return null due to isValidDetails and trim checks
        return itemParsed;
    }

    /**
     * Returns true if a given string is a valid detail.
     */
    public static boolean isValidDetails(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid detail item.
     */
    public static boolean isValidFindDetailsItem(String test) {
        return !test.matches(VALIDATION_REGEX_QUANTITY);
    }

    /**
     * Returns matching string based on {@code regexKeyword} specified in {@code DETAIL_PATTERN}.
     */
    public static String parseDetails(String regexInput, String regexKeyword) {
        // only allowed these two keywords to be queried
        assert regexKeyword.equals(ORDER_QUANTITY_REGEX_KEYWORD) || regexKeyword.equals(ORDER_ITEM_REGEX_KEYWORD);
        assert isValidDetails(regexInput); // should have been executed in Parser

        final Matcher matcher = DETAIL_PATTERN.matcher(regexInput);

        if (!matcher.matches()) {
            return null;
        }
        return matcher.group(regexKeyword);
    }

    @Override
    public String toString() {
        return String.valueOf(quantity) + ": " + item;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Details // instanceof handles nulls
                && value.equalsIgnoreCase(((Details) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
