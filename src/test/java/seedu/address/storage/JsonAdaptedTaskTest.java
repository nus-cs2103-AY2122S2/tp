package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.FIRST_TASK;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.name.Name;
import seedu.address.model.task.Date;
import seedu.address.model.task.StartTime;


/**
 * Contains unit tests for JsonAdaptedTaskTest
 */
public class JsonAdaptedTaskTest {

    private static final String INVALID_NAME = "m33@TING";
    private static final String INVALID_DATE = "29-02-2022";
    private static final String INVALID_START_TIME = "9:90";
    private static final String INVALID_END_TIME = "10;0";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = FIRST_TASK.getName().toString();
    private static final String VALID_DATE = FIRST_TASK.getDate().toString();
    private static final String VALID_START_TIME = FIRST_TASK.getStartTime().toString();
    private static final String VALID_END_TIME = FIRST_TASK.getEndTime().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = FIRST_TASK.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedName> VALID_PERSONS = FIRST_TASK.getPersons().stream()
            .map(JsonAdaptedName::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(FIRST_TASK);
        assertEquals(FIRST_TASK, task.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(INVALID_NAME, VALID_DATE, VALID_START_TIME,
                        VALID_END_TIME, VALID_TAGS, VALID_PERSONS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, VALID_DATE, VALID_START_TIME,
                VALID_END_TIME, VALID_TAGS, VALID_PERSONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, INVALID_DATE, VALID_START_TIME, VALID_END_TIME,
                        VALID_TAGS, VALID_PERSONS);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, null, VALID_START_TIME, VALID_END_TIME,
                VALID_TAGS, VALID_PERSONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidStartTime_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_DATE, INVALID_START_TIME, VALID_END_TIME,
                        VALID_TAGS, VALID_PERSONS);
        String expectedMessage = StartTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullStartTime_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_DATE, null, VALID_END_TIME,
                VALID_TAGS, VALID_PERSONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StartTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    /*
    // To be fixed
    @Test
    public void toModelType_invalidEndTime_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_DATE, VALID_START_TIME, INVALID_END_TIME, VALID_TAGS);
        String expectedMessage = EndTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullEndTime_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_DATE, VALID_START_TIME, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EndTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
     */

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_DATE, VALID_START_TIME, VALID_END_TIME, invalidTags,
                        VALID_PERSONS);
        assertThrows(IllegalValueException.class, task::toModelType);
    }

    @Test
    public void toModelType_invalidPersons_throwsIllegalValueException() {
        List<JsonAdaptedName> invalidPersons = new ArrayList<>(VALID_PERSONS);
        invalidPersons.add(new JsonAdaptedName(INVALID_NAME));
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_DATE, VALID_START_TIME, VALID_END_TIME,
                        VALID_TAGS, invalidPersons);
        assertThrows(IllegalValueException.class, task::toModelType);
    }
}
