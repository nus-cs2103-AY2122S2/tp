package seedu.ibook.model.product;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Product's category in the iBook. Categories are case insensitive.
 * Guarantees: immutable; is valid as declared in {@link #isValidCategoryName(String)}
 */
public class Category {

    public static final String DEFAULT_CATEGORY = "Miscellaneous";

    public static final String MESSAGE_CONSTRAINTS =
            "Categories (if given) should not have a space as the first character";

    /*
     * Empty strings are allowed. Otherwise, the first character of the description
     * must not be a whitespace
     */
    public static final String VALIDATION_REGEX = "|[^ ].*";

    public final String fullCategoryName;

    /**
     * Constructs a {@code Category}.
     *
     * @param categoryName A valid category name.
     */
    public Category(String categoryName) {
        requireNonNull(categoryName);
        checkArgument(isValidCategoryName(categoryName), MESSAGE_CONSTRAINTS);
        if (categoryName.isEmpty()) {
            fullCategoryName = DEFAULT_CATEGORY;
        } else {
            fullCategoryName = categoryName;
        }
    }

    /**
     * Checks if the string is valid as per {@code VALIDATION_REGEX}.
     *
     * @param test String to test.
     * @return Result of test.
     */
    public static boolean isValidCategoryName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Checks if the category contains the keyword.
     */
    public boolean contains(Category keyword) {
        String key = keyword.fullCategoryName.toLowerCase();
        return fullCategoryName.toLowerCase().contains(key);
    }

    @Override
    public String toString() {
        return fullCategoryName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Category // instanceof handles nulls
                && fullCategoryName.equalsIgnoreCase(((Category) other).fullCategoryName)); // state check
    }

    @Override
    public int hashCode() {
        return fullCategoryName.hashCode();
    }

}
