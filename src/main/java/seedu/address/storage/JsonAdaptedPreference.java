package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Preference;
import seedu.address.model.property.Price;
import seedu.address.model.property.Region;
import seedu.address.model.property.Size;

public class JsonAdaptedPreference {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Preference's %s field is missing!";

    private final String region;
    private final String size;
    private final String lowPrice;
    private final String highPrice;

    /**
     * Constructs a {@code JsonAdapterPreference} with the given preference details.
     */
    @JsonCreator
    public JsonAdaptedPreference(@JsonProperty("region") String region, @JsonProperty("size") String size,
            @JsonProperty("lowPrice") String lowPrice, @JsonProperty("highPrice") String highPrice) {
        this.region = region;
        this.size = size;
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
    }

    /**
     * Converts a given {@code Preference} into this class for Jackson use.
     */
    public JsonAdaptedPreference(Preference source) {
        region = source.getRegion().value;
        size = source.getSize().value;
        lowPrice = source.getLowPrice().value;
        highPrice = source.getHighPrice().value;
    }

    /**
     * Converts this Jackson-friendly adapted preference object into the model's {@code Preference} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted property.
     */
    public Preference toModelType() throws IllegalValueException {
        if (region == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Region.class.getSimpleName()));
        }
        if (!Region.isValidRegion(region)) {
            throw new IllegalValueException(Region.MESSAGE_CONSTRAINTS);
        }
        final Region modelRegion = Region.fromString(region);

        if (size == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Size.class.getSimpleName()));
        }
        if (!Size.isValidSize(size)) {
            throw new IllegalValueException(Size.MESSAGE_CONSTRAINTS);
        }
        final Size modelSize = Size.fromString(size);

        if (lowPrice == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName()));
        }
        if (!Price.isValidPrice(lowPrice)) {
            throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS);
        }
        final Price modelLowPrice = new Price(lowPrice);

        if (highPrice == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName()));
        }
        if (!Price.isValidPrice(highPrice)) {
            throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS);
        }
        final Price modelHighPrice = new Price(highPrice);

        return new Preference(modelRegion, modelSize, modelLowPrice, modelHighPrice);
    }

}
