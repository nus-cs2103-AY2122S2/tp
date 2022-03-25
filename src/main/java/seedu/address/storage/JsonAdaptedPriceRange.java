package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.property.PriceRange;

public class JsonAdaptedPriceRange {

    private final String lower;
    private final String upper;

    /**
     * Constructs a {@code JsonAdaptedPriceRange} with the given house details.
     */
    @JsonCreator
    public JsonAdaptedPriceRange(@JsonProperty("lower") String lower,
                                 @JsonProperty("upper") String upper) {
        this.lower = lower;
        this.upper = upper;
    }

    /**
     * Converts a given {@code PriceRange} into this class for Jackson use.
     */
    public JsonAdaptedPriceRange(PriceRange source) {
        this.lower = source.getLowerToString();
        this.upper = source.getUpperToString();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code PriceRange} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted house.
     */
    public PriceRange toModelType() throws IllegalValueException {
        return new PriceRange(Integer.parseInt(lower), Integer.parseInt(upper));
    }
}
