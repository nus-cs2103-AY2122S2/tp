package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.dish.Dish;
import seedu.address.model.dish.NameDish;
import seedu.address.model.dish.PriceDish;

/**
 * Jackson-friendly version of {@link Dish}.
 */
class JsonAdaptedDish {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Dish's %s field is missing!";

    private final String name;
    private final String price;

    /**
     * Constructs a {@code JsonAdaptedDish} with the given dish details.
     */
    @JsonCreator
    public JsonAdaptedDish(@JsonProperty("name") String name, @JsonProperty("price") String price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Converts a given {@code Dish} into this class for Jackson use.
     */
    public JsonAdaptedDish(Dish source) {
        name = source.getName().fullName;
        price = source.getPrice().toString();
    }

    /**
     * Converts this Jackson-friendly adapted dish object into the model's {@code Dish} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted dish.
     */
    public Dish toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    NameDish.class.getSimpleName()));
        }
        if (!NameDish.isValidName(name)) {
            throw new IllegalValueException(NameDish.MESSAGE_CONSTRAINTS);
        }
        final NameDish modelName = new NameDish(name);

        if (price == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PriceDish.class.getSimpleName()));
        }
        if (!PriceDish.isValidPrice(price)) {
            throw new IllegalValueException(PriceDish.MESSAGE_CONSTRAINTS);
        }
        final PriceDish modelPrice = new PriceDish(price);

        return new Dish(modelName, modelPrice);
    }

}

