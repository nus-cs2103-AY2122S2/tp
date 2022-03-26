package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.property.House;
import seedu.address.model.property.HouseType;
import seedu.address.model.property.NullPropertyToSell;
import seedu.address.model.property.PriceRange;
import seedu.address.model.property.PropertyToSell;

import static org.junit.jupiter.api.Assertions.*;

class JsonAdaptedPropertyToSellTest {
    PriceRange priceRange1 = new PriceRange(1,100);
    House house1 = new House(HouseType.BUNGALOW, "house1_location");

    PropertyToSell propertyToSell1 = new PropertyToSell(house1, priceRange1,"house1_address");
    JsonAdaptedPropertyToSell jsonAdaptedPropertyToSell1 = new JsonAdaptedPropertyToSell(
            new JsonAdaptedHouse(house1),
            new JsonAdaptedPriceRange(priceRange1),
            "house1_address");

    PropertyToSell nullPropertyToSell = NullPropertyToSell.getNullPropertyToSell();
    @Test
    void getHouse() {
        assertEquals(house1, propertyToSell1.getHouse());
    }

    @Test
    void getPriceRange() throws IllegalValueException {
        assertEquals(propertyToSell1.getPriceRange(), jsonAdaptedPropertyToSell1.getPriceRange());
    }

    @Test
    void getAddress() throws IllegalValueException {
        assertEquals(propertyToSell1.getAddress(), jsonAdaptedPropertyToSell1.getAddress());
    }

    @Test
    void toModelType() throws IllegalValueException {
        assertEquals(propertyToSell1, jsonAdaptedPropertyToSell1.toModelType());
    }
}