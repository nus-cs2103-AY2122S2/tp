package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.property.House;
import seedu.address.model.property.HouseType;

import static org.junit.jupiter.api.Assertions.*;

class JsonAdaptedHouseTest {

    House house1 = new House(HouseType.NULLHOUSETYPE, "");

    JsonAdaptedHouse jsonAdaptedHouse = new JsonAdaptedHouse(house1);


    @Test
    void toModelType() throws IllegalValueException {
        assertEquals(house1, jsonAdaptedHouse.toModelType());
    }
}