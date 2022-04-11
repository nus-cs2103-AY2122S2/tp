package seedu.address.model.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents a Recipe's ingredient in the recipe book.
 * Guarantees: immutable; is valid as declared in {@link #isValidIngredientName(String)},
 * and {@link #isValidQuantity(double)}.
 */
public class Ingredient {

    public static final String NAME_CONSTRAINTS = "Ingredients name should not be left blank";
    public static final String QUANTITY_CONSTRAINTS =
            "Ingredient should contain a valid name and quantity (greater than 0.0) and should not be"
                    + " left blank";
    public static final String QUANTIFIER_CONSTRAINTS = "Quantifier name should not be left blank";

    /*
     * The first character of the ingredient must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String NAME_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    /*
     * The quantity of the ingredient must be a valid number.
     */
    public static final String QUANTITY_VALIDATION_REGEX = "[0-9]+\\.?[0-9]*|\\.[0-9]+";

    public final String ingredientName;
    public final double quantity;
    public final String quantifier;

    /**
     * Constructs a {@code Ingredient}.
     *
     * @param name A valid name.
     * @param quantity A valid quantity.
     * @param quantifier A valid quantifier, blanks are allowed.
     */
    public Ingredient(String name, double quantity, String quantifier) {
        requireNonNull(name);
        checkArgument(isValidIngredientName(name), NAME_CONSTRAINTS);

        checkArgument(isValidQuantity(quantity), QUANTITY_CONSTRAINTS);


        if (quantifier == null) {
            quantifier = "";
        }

        this.ingredientName = name;
        this.quantity = quantity;
        this.quantifier = quantifier.trim();
    }

    public Ingredient(String name, double quantity) {
        this(name, quantity, "");
    }

    public static boolean isValidIngredientName(String test) {
        return test.matches(NAME_VALIDATION_REGEX);
    }

    public static boolean isValidQuantity(double test) {
        return test > 0;
    }

    @Override
    public String toString() {
        // trim required to remove trailing space when quantifier is blank
        return String.format("%s %s %s", ingredientName, quantity, quantifier).trim();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Ingredient)) {
            return false;
        }

        if (this == o) {
            return true;
        }

        Ingredient other = (Ingredient) o;
        return this.ingredientName.equals(other.ingredientName)
                && this.quantity == other.quantity
                && this.quantifier.equals(other.quantifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientName, quantity, quantifier);
    }
}
