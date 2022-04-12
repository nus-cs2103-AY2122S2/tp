package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPosition.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPositions.SR_FE_SOFTWARE_ENGINEER;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.position.Description;
import seedu.address.model.position.PositionName;
import seedu.address.model.position.PositionOffers;
import seedu.address.model.position.PositionOpenings;

public class JsonAdaptedPositionTest {
    private static final String INVALID_NAME = " invalidName";
    private static final String LONG_INVALID_NAME = "100 characters long 100 characters long"
            + " 100 characters long 100 characters long 100 characters long 100 characters long";
    private static final String INVALID_DESCRIPTION = " invalidDescription";
    private static final String LONG_INVALID_DESCRIPTION = "200 characters long 200 characters long"
            + " 200 characters long 200 characters long 200 characters long 200 characters long 200 characters long"
            + " 200 characters long 200 characters long 200 characters long 200 characters long 200 characters long";
    private static final String INVALID_POSITION_OPENINGS = "twenty";
    private static final String LONG_INVALID_POSITION_OPENINGS = "99999999999";

    private static final String VALID_POSITION_NAME = SR_FE_SOFTWARE_ENGINEER.getPositionName().toString();
    private static final String VALID_DESCRIPTION = SR_FE_SOFTWARE_ENGINEER.getDescription().toString();
    private static final String VALID_POSITION_OPENINGS = SR_FE_SOFTWARE_ENGINEER.getPositionOpenings().toString();
    private static final String VALID_POSITION_OFFERS = SR_FE_SOFTWARE_ENGINEER.getPositionOffers().toString();
    private static final List<JsonAdaptedRequirement> VALID_REQUIREMENTS = SR_FE_SOFTWARE_ENGINEER
            .getRequirements().stream()
            .map(JsonAdaptedRequirement::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPositionDetails_returnsPosition() throws Exception {
        JsonAdaptedPosition position = new JsonAdaptedPosition(SR_FE_SOFTWARE_ENGINEER);
        assertEquals(SR_FE_SOFTWARE_ENGINEER, position.toModelType());
    }

    @Test
    public void toModelType_invalidPositionName_throwsIllegalValueException() {
        String expectedMessage = PositionName.MESSAGE_CONSTRAINTS;
        JsonAdaptedPosition shortPosition =
                new JsonAdaptedPosition(INVALID_NAME, VALID_DESCRIPTION, VALID_POSITION_OPENINGS,
                        VALID_REQUIREMENTS, VALID_POSITION_OFFERS);
        assertThrows(IllegalValueException.class, expectedMessage, shortPosition::toModelType);
        JsonAdaptedPosition longPosition =
                new JsonAdaptedPosition(LONG_INVALID_NAME, VALID_DESCRIPTION, VALID_POSITION_OPENINGS,
                        VALID_REQUIREMENTS, VALID_POSITION_OFFERS);
        assertThrows(IllegalValueException.class, expectedMessage, longPosition::toModelType);
    }

    @Test
    public void toModelType_nullPositionName_throwsIllegalValueException() {
        JsonAdaptedPosition position =
                new JsonAdaptedPosition(null, VALID_DESCRIPTION, VALID_POSITION_OPENINGS,
                        VALID_REQUIREMENTS, VALID_POSITION_OFFERS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PositionName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, position::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        JsonAdaptedPosition shortDescription =
                new JsonAdaptedPosition(VALID_POSITION_NAME, INVALID_DESCRIPTION, VALID_POSITION_OPENINGS,
                        VALID_REQUIREMENTS, VALID_POSITION_OFFERS);
        assertThrows(IllegalValueException.class, expectedMessage, shortDescription::toModelType);
        JsonAdaptedPosition longDescription =
                new JsonAdaptedPosition(VALID_POSITION_NAME, LONG_INVALID_DESCRIPTION, VALID_POSITION_OPENINGS,
                        VALID_REQUIREMENTS, VALID_POSITION_OFFERS);
        assertThrows(IllegalValueException.class, expectedMessage, longDescription::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedPosition position =
                new JsonAdaptedPosition(VALID_POSITION_NAME, null, VALID_POSITION_OPENINGS,
                        VALID_REQUIREMENTS, VALID_POSITION_OFFERS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, position::toModelType);
    }

    @Test
    public void toModelType_invalidPositionOpenings_throwsIllegalValueException() {
        String expectedMessage = PositionOpenings.MESSAGE_CONSTRAINTS;
        JsonAdaptedPosition shortPositionOpenings =
                new JsonAdaptedPosition(VALID_POSITION_NAME, VALID_DESCRIPTION, INVALID_POSITION_OPENINGS,
                        VALID_REQUIREMENTS, VALID_POSITION_OFFERS);
        assertThrows(IllegalValueException.class, expectedMessage, shortPositionOpenings::toModelType);
        JsonAdaptedPosition longPositionOpenings =
                new JsonAdaptedPosition(VALID_POSITION_NAME, VALID_DESCRIPTION, LONG_INVALID_POSITION_OPENINGS,
                        VALID_REQUIREMENTS, VALID_POSITION_OFFERS);
        assertThrows(IllegalValueException.class, expectedMessage, longPositionOpenings::toModelType);
    }

    @Test
    public void toModelType_nullPositionOpenings_throwsIllegalValueException() {
        JsonAdaptedPosition position =
                new JsonAdaptedPosition(VALID_POSITION_NAME, VALID_DESCRIPTION, null,
                        VALID_REQUIREMENTS, VALID_POSITION_OFFERS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PositionOpenings.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, position::toModelType);
    }

    @Test
    public void toModelType_nullPositionOffers_throwsIllegalValueException() {
        JsonAdaptedPosition position =
                new JsonAdaptedPosition(VALID_POSITION_NAME, VALID_DESCRIPTION, VALID_POSITION_OPENINGS,
                        VALID_REQUIREMENTS, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PositionOffers.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, position::toModelType);
    }
}
