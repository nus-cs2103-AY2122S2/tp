package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.property.House;
import seedu.address.model.property.PriceRange;
import seedu.address.model.property.PropertyToBuy;

import java.util.List;

/**
 * Jackson-friendly version of {@link PropertyToBuy}.
 */
class JsonAdaptedPropertyToBuy {

    private final JsonAdaptedHouse house;
    private final JsonAdaptedPriceRange priceRange;
//    /**
//     * Constructs a {@code JsonAdaptedclient} with the given client details.
//     */
//    @JsonCreator
//    public JsonAdaptedBuyer(@JsonProperty("name") String name,
//                            @JsonProperty("phone") String phone,
//                            @JsonProperty("appointment") String appointment,
//                            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
//                            @JsonProperty("property") JsonAdaptedPropertyToBuy propertyToBuy) {
//        this.name = name;
//        this.phone = phone;
//        if (tagged != null) {
//            this.tagged.addAll(tagged);
//        }
//        this.appointment = appointment;
//        this.propertyToBuy = propertyToBuy;
//    }
//    /**
//     * Constructs a {@code JsonAdaptedPropertyToBuy} with the given property details.
//     */
//    @JsonCreator
//    public JsonAdaptedPropertyToBuy(House house, PriceRange priceRange) {
//        this.house = house;
//        this.priceRange = priceRange;
//    }

    /**
     * Constructs a {@code JsonAdaptedPropertyToBuy} with the given property details.
     */
    @JsonCreator
    public JsonAdaptedPropertyToBuy(@JsonProperty("house") JsonAdaptedHouse house,
                                    @JsonProperty("pricerange") JsonAdaptedPriceRange priceRange) {
        this.house = house;
        this.priceRange = priceRange;
    }

    /**
     * Converts a given {@code PropertyToBuy} into this class for Jackson use.
     */
    public JsonAdaptedPropertyToBuy(PropertyToBuy source) {
        this.house = new JsonAdaptedHouse(source.getHouse());
        this.priceRange = new JsonAdaptedPriceRange(source.getPriceRange());
    }

    @JsonValue
    public House getHouse() throws IllegalValueException {
        return house.toModelType();
    }

    public PriceRange getPriceRange() throws IllegalValueException {
        return priceRange.toModelType();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code PropertyToBuy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public PropertyToBuy toModelType() throws IllegalValueException {
        return new PropertyToBuy(house.toModelType(), priceRange.toModelType());
    }


}
