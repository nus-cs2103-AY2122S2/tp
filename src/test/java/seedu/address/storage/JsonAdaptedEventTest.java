package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedEvent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.DateTime;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Information;

public class JsonAdaptedEventTest {
    private static final String INVALID_NAME = "";
    private static final String INVALID_INFO = "";
    private static final String INVALID_DATETIME = "2022-30-11 10:10";

    private static final String VALID_NAME = "lunch";
    private static final String VALID_INFO = "at HDL";
    private static final List<JsonAdaptedName> VALID_PARTICIPANTS =
            new ArrayList<>(List.of(new JsonAdaptedName(BENSON.getName().toString())));
    private static final String VALID_DATETIME = "2022-9-11 10:10";
    private static final Event VALID_EVENT = new Event(new EventName(VALID_NAME), new Information(VALID_INFO),
            new ArrayList<>(List.of(BENSON.getName())),
            new DateTime(2022, 9, 11, 10, 10));
    @Test
    public void toModelType_validEventDetails_returnsEvent() throws Exception {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_NAME, VALID_INFO, VALID_PARTICIPANTS, VALID_DATETIME);
        assertEquals(VALID_EVENT, event.toModelType());
    }

    @Test
    public void toModelType_invalidEventName_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(INVALID_NAME, VALID_INFO, VALID_PARTICIPANTS, VALID_DATETIME);
        String expectedMessage = EventName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidInfo_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, INVALID_INFO, VALID_PARTICIPANTS, VALID_DATETIME);
        String expectedMessage = Information.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidDateTime_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, VALID_INFO, VALID_PARTICIPANTS, INVALID_DATETIME);
        String expectedMessage = DateTime.DATE_MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullEventName_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(null, VALID_INFO, VALID_PARTICIPANTS, VALID_DATETIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventName.class.getSimpleName());

        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullInfo_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, null, VALID_PARTICIPANTS, VALID_DATETIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Information.class.getSimpleName());

        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);

    }

    @Test
    public void toModelType_nullDateTime_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, VALID_INFO, VALID_PARTICIPANTS, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DateTime.class.getSimpleName());

        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }
}
