package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedSchedule.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleDateTime;
import seedu.address.model.schedule.ScheduleDescription;
import seedu.address.model.schedule.ScheduleName;

public class JsonAdaptedScheduleTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_DESCRIPTION_EMPTY_STRING = "";
    private static final String INVALID_DESCRIPTION_EMPTY_SPACE = " ";
    private static final String INVALID_DATETIME = "29/02/2023 1900";

    private static final String VALID_NAME = "Competition";
    private static final String VALID_DESCRIPTION = "A Div Men's Basketball finals";
    private static final String VALID_DATETIME = "20/03/2024 1900";

    private static final Schedule VALID_SCHEDULE = new Schedule(new ScheduleName(VALID_NAME),
            new ScheduleDescription(VALID_DESCRIPTION), new ScheduleDateTime(VALID_DATETIME));

    @Test
    public void toModelType_validScheduleDetails_returnsSchedule() throws Exception {
        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(VALID_SCHEDULE);
        assertEquals(VALID_SCHEDULE, schedule.toModelType());
    }

    @Test
    public void toModelType_invalidScheduleName_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule =
                new JsonAdaptedSchedule(INVALID_NAME, VALID_DESCRIPTION, VALID_DATETIME);
        String expectedMessage = ScheduleName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, schedule::toModelType);
    }

    @Test
    public void toModelType_nullScheduleName_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(null, VALID_DESCRIPTION, VALID_DATETIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ScheduleName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, schedule::toModelType);
    }

    @Test
    public void toModelType_invalidScheduleDescriptionEmpty_throwsIllegalValueException() {
        JsonAdaptedSchedule scheduleEmptyDescription =
                new JsonAdaptedSchedule(VALID_NAME, INVALID_DESCRIPTION_EMPTY_STRING, VALID_DATETIME);
        String expectedMessage = ScheduleDescription.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, scheduleEmptyDescription::toModelType);
    }

    @Test
    public void toModelType_invalidScheduleDescriptionEmptySpace_throwsIllegalValueException() {
        JsonAdaptedSchedule scheduleEmptySpace =
                new JsonAdaptedSchedule(VALID_NAME, INVALID_DESCRIPTION_EMPTY_SPACE, VALID_DATETIME);
        String expectedMessage = ScheduleDescription.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, scheduleEmptySpace::toModelType);
    }

    @Test
    public void toModelType_nullScheduleDescription_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(VALID_NAME, null, VALID_DATETIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ScheduleDescription.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, schedule::toModelType);
    }

    @Test
    public void toModelType_invalidDateTime_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule =
                new JsonAdaptedSchedule(VALID_NAME, VALID_DESCRIPTION, INVALID_DATETIME);
        String expectedMessage = ScheduleDateTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, schedule::toModelType);
    }

    @Test
    public void toModelType_nullDateTime_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(VALID_NAME, VALID_DESCRIPTION, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ScheduleDateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, schedule::toModelType);
    }

}
