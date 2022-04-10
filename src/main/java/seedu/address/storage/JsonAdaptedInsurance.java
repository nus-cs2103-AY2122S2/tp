package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.insurance.Insurance;
import seedu.address.model.insurance.Price;
import seedu.address.model.insurance.Title;

/**
 * Jackson-friendly version of {@link Insurance}.
 */
public class JsonAdaptedInsurance {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Insurance's %s field is missing!";

    private final String title;
    private final String price;

    /**
     * Constructs a {@code JsonAdaptedInsurance} with the given insurance details.
     */
    @JsonCreator
    public JsonAdaptedInsurance(@JsonProperty("title") String title, @JsonProperty("price") String price) {
        this.title = title;
        this.price = price;
    }

    /**
     * Converts a given {@code Insurance} into this class for Jackson use.
     */
    public JsonAdaptedInsurance(Insurance source) {
        title = source.getTitle().title;
        price = source.getPrice().value;
    }

    /**
     * Converts this Jackson-friendly adapted insurance object into the model's {@code Insurance} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted insurance.
     */
    public Insurance toModelType() throws IllegalValueException {
        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (price == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName()));
        }
        if (!Price.isValidPrice(price)) {
            throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS);
        }
        final Price modelPrice = new Price(price);

        return new Insurance(modelTitle, modelPrice);
    }

}

