package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.property.House;
import seedu.address.model.property.PriceRange;
import seedu.address.model.property.PropertyToBuy;

/**
 * Jackson-friendly version of {@link PropertyToBuy}.
 */
class JsonAdaptedPropertyToBuy {

    private final House house;
    private final PriceRange priceRange;

    /**
     * Constructs a {@code JsonAdaptedPropertyToBuy} with the given {@code house, @code PriceRange}.
     */
    @JsonCreator
    public JsonAdaptedPropertyToBuy(House house, PriceRange priceRange) {
        this.house = house;
        this.priceRange = priceRange;
    }

    /**
     * Converts a given {@code PropertyToBuy} into this class for Jackson use.
     */
    public JsonAdaptedPropertyToBuy(PropertyToBuy source) {
        this.house = source.getHouse();
        this.priceRange = source.getPriceRange();
    }

    @JsonValue
    public String getHouseTypeToString() {
        return house.getHouseTypeToString();
    }


    public House getHouse() {
        return house;
    }

    public PriceRange getPriceRange() {
        return priceRange;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public PropertyToBuy toModelType() throws IllegalValueException {

        return new PropertyToBuy(house, priceRange);
    }


}
