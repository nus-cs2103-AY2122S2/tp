package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.Details;

/**
 * Jackson-friendly version of {@link Details}.
 */
public class JsonAdaptedDetails {
    private final String detailsName;

    /**
     * Constructs a {@code JsonAdaptedDetails} with the given {@code detailsName}.
     */
    @JsonCreator
    public JsonAdaptedDetails(String detailsName) {
        this.detailsName = detailsName;
    }

    /**
     * Converts a given {@code Details} into this class for Jackson use.
     */
    public JsonAdaptedDetails(Details source) {
        detailsName = source.value;
    }

    @JsonValue
    public String getDetailsName() {
        return detailsName;
    }

    /**
     * Converts this Jackson-friendly adapted details object into the model's {@code Details} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted details.
     */
    public Details toModelType() throws IllegalValueException {
        if (!Details.isValidDetails(detailsName)) {
            throw new IllegalValueException(Details.MESSAGE_CONSTRAINTS);
        }
        return new Details(detailsName);
    }
}
