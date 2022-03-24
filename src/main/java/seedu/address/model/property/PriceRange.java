package seedu.address.model.property;


import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Price range of a property, which includes the lower and upper bound that a buyer is willing to buy at or a seller
 * is willing to sell at.
 *
 */
public class PriceRange {

    public static final String MESSAGE_CONSTRAINTS =
        "PriceRange: lower should be lower than upper(inclusive), all values should be non-negative, and please" +
                "use numerical values";

    /**
     * The lower and upper bounds are inclusive, meaning the lower bound includes the lower and higher value itself,
     * respectively.
     */
    private final int lower;
    private final int upper;

    /**
     * Constructor
     * @param lower lower bound of the PriceRange, inclusive.
     * @param upper upper bound of the PriceRange, inclusive.
     */
    public PriceRange(int lower, int upper) {
        checkArgument(isValidPriceRange(lower, upper), MESSAGE_CONSTRAINTS);

        this.lower = lower;
        this.upper = upper;
    }

    /**
     * Constructor of the class during parsing.
     *
     * @param priceRange The string to be converted to int price ranges.
     */
    public PriceRange(String priceRange) {
        int[] bounds = getValuesFromString(priceRange);
        this.lower = bounds[0];
        this.upper = bounds[1];
    }

    /**
     * Checks if the given string can be converted to a numerical price range.
     *
     * @param priceRange The string.
     * @return True if it can be converted, False otherwise.
     */
    public static boolean isValidPriceRange(String priceRange) {
        try {
            String[] numbers = priceRange.split(",");
            if (numbers.length != 2) {
                return false;
            }
            int lower = Integer.parseInt(numbers[0]);
            int upper = Integer.parseInt(numbers[1]);
            return isValidPriceRange(lower, upper);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private int[] getValuesFromString(String priceRange) {
        String[] values = priceRange.split(",");
        int lower = Integer.parseInt(values[0]);
        int upper = Integer.parseInt(values[1]);
        return new int[] {lower, upper};
    }

    /**
     * Checks if a Price Range, given a lower and upper bound, is valid.
     * @param lower lower bound of range.
     * @param upper upper bound of range.
     * @return True if PriceRange is valid, False otherwise.
     */
    public static boolean isValidPriceRange(int lower, int upper) {
        //valid as long as positive and lower is lower than upper
        return lower >= 0 && upper >= 0 && lower <= upper;
    }

    public int getLower() {
        return this.lower;
    }

    public String getLowerToString() {
        return Integer.toString(this.lower);
    }

    public int getUpper() {
        return this.upper;
    }

    public String getUpperToString() {
        return Integer.toString(this.upper);
    }

    /**
     * Checks whether a value is within a PriceRange.
     *
     * @param value value to be checked if within the price range.
     * @param priceRange the priceRange.
     * @return True if the value falls within the PriceRange, false otherwise.
     */
    public static boolean isWithinRange(int value, PriceRange priceRange) {
        return value >= priceRange.lower && value <= priceRange.upper;
    }

    /**
     * Checks whether a Price Range is within another PriceRange, meaning whether any value in the toBuy priceRange
     * is within the toSell PriceRange.
     * @param buyRange the priceRange a buyer is willing to buy a Property for.
     * @param sellRange the priceRange a seller is willing to sell a Property for.
     * @return True if the price in the buyRange can match one in the sellRange, false otherwise.
     */
    public static boolean canMatchPrice(PriceRange buyRange, PriceRange sellRange) {
        // [50, 100] , [99, 200] should match.
        // [100, 200] . [40, 99] should NOT match.
        // [50, 60], [10, 100] should match.
        // [10, 100], [50, 60] should match.
        return isWithinRange(buyRange.getLower(), sellRange) || isWithinRange(buyRange.getUpper(), sellRange);
    }

    @Override
    public String toString() {
        return "["
                + lower
                + ','
                + upper
                + ']';
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof PriceRange // instanceof handles nulls
            && lower == ((PriceRange) other).lower
            && upper == ((PriceRange) other).upper); // state check
    }
}
