package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.property.Address;
import seedu.address.model.property.House;
import seedu.address.model.property.PriceRange;
import seedu.address.model.property.PropertyToSell;

/**
 * Jackson-friendly version of {@link PropertyToSell}.
 */
class JsonAdaptedPropertyToSell {

    private final JsonAdaptedHouse house;
    private final JsonAdaptedPriceRange priceRange;
    private final String address;

    /**
     * Constructs a {@code JsonAdaptedPropertyToBuy} with the given property details.
     */
    @JsonCreator
    public JsonAdaptedPropertyToSell(@JsonProperty("house") JsonAdaptedHouse house,
                                     @JsonProperty("priceRange") JsonAdaptedPriceRange priceRange,
                                     @JsonProperty("address") String address) {
        this.house = house;
        this.priceRange = priceRange;
        this.address = address;
    }

    /**
     * Converts a given {@code PropertyToSell} into this class for Jackson use.
     */
    public JsonAdaptedPropertyToSell(PropertyToSell source) {
        this.house = new JsonAdaptedHouse(source.getHouse());
        this.priceRange = new JsonAdaptedPriceRange(source.getPriceRange());
        this.address = source.getAddress().toString();
    }


    public House getHouse() throws IllegalValueException {
        return house.toModelType();
    }

    public PriceRange getPriceRange() throws IllegalValueException {
        return priceRange.toModelType();
    }

    public Address getAddress() {
        return new Address(address);
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code PropertyToSell} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public PropertyToSell toModelType() throws IllegalValueException {
        return new PropertyToSell(house.toModelType(), priceRange.toModelType(), getAddress());
    }
}
