package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.recipe.Ingredient;

/**
 * Jackson-friendly version of {@link Ingredient}.
 */
public class JsonAdaptedIngredient {

    public final String ingredientName;
    public final String quantity;
    public final String quantifier;

    /**
     * Constructs a {@code JsonAdaptedIngredient} with the given {@code ingredientName},
     * {@code quantity} and {@code quantifier}.
     */
    @JsonCreator
    public JsonAdaptedIngredient(@JsonProperty("ingredientName") String ingredientName,
                                 @JsonProperty("quantity") String quantity,
                                 @JsonProperty("quantifier") String quantifier) {
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.quantifier = quantifier;
    }

    /**
     * Converts a given {@code Ingredient} into this class for Jackson use.
     */
    public JsonAdaptedIngredient(Ingredient source) {
        ingredientName = source.ingredientName;
        quantity = String.valueOf(source.quantity);
        quantifier = source.quantifier;
    }

    /**
     * Converts this Jackson-friendly adapted ingredient object into the model's {@code Ingredient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted ingredient.
     */
    public Ingredient toModelType() throws IllegalValueException {
        double dblQuantity;
        if (!Ingredient.isValidIngredientName(ingredientName)) {
            throw new IllegalValueException(Ingredient.NAME_CONSTRAINTS);
        }

        try {
            dblQuantity = Double.parseDouble(quantity);
            if (!Ingredient.isValidQuantity(dblQuantity)) {
                throw new IllegalValueException(Ingredient.QUANTITY_CONSTRAINTS);
            }
        } catch (NumberFormatException nfe) {
            throw new IllegalValueException(Ingredient.QUANTITY_CONSTRAINTS);
        }

        return new Ingredient(ingredientName, dblQuantity, quantifier);
    }

}
