package seedu.address.storage;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.property.House;
import seedu.address.model.property.HouseType;
import seedu.address.model.property.Location;

class JsonAdaptedHouseTest {

    private final House house1 = new House(HouseType.NULLHOUSETYPE, new Location(""));

    private final JsonAdaptedHouse jsonAdaptedHouse = new JsonAdaptedHouse(house1);


    @Test
    void toModelType() throws IllegalValueException {
    }
}
