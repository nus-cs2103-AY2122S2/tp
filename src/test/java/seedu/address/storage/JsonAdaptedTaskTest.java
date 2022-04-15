package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.CS2105_MIDTERM;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.CompletionStatus;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Name;
import seedu.address.model.task.Priority;


public class JsonAdaptedTaskTest {
    private static final String INVALID_NAME = "ㅊㅋㅌㅍㅎ";
    private static final String INVALID_DESCRIPTION = "";
    private static final String INVALID_COMPLETION_STATUS = "unknown";
    private static final String INVALID_DEADLINE = "example.com";
    private static final String INVALID_PRIORITY = "highest";
    private static final String INVALID_TAG = " #CS2103T";

    private static final String VALID_NAME = CS2105_MIDTERM.getName().toString();
    private static final String VALID_DESCRIPTION = CS2105_MIDTERM.getDescription().toString();
    private static final String VALID_COMPLETION_STATUS = CS2105_MIDTERM.getCompletionStatus().toString();
    private static final String VALID_PRIORITY = "high";
    private static final String VALID_DEADLINE = CS2105_MIDTERM.getDeadline().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = CS2105_MIDTERM.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(CS2105_MIDTERM);
        assertEquals(CS2105_MIDTERM, task.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(INVALID_NAME, VALID_DESCRIPTION, VALID_COMPLETION_STATUS,
                        VALID_DEADLINE, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(null, VALID_DESCRIPTION, VALID_COMPLETION_STATUS,
                        VALID_DEADLINE, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, INVALID_DESCRIPTION, VALID_COMPLETION_STATUS,
                        VALID_DEADLINE, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, null, VALID_COMPLETION_STATUS, VALID_DEADLINE,
                        VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidCompletionStatus_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_DESCRIPTION, INVALID_COMPLETION_STATUS,
                        VALID_DEADLINE, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = CompletionStatus.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullCompletionStatus_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_DESCRIPTION, null, VALID_DEADLINE, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, CompletionStatus.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_DESCRIPTION, VALID_COMPLETION_STATUS,
                        INVALID_DEADLINE, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_DESCRIPTION, VALID_COMPLETION_STATUS, null,
                        VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidPriority_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_DESCRIPTION, VALID_COMPLETION_STATUS,
                        VALID_DEADLINE, INVALID_PRIORITY, VALID_TAGS);
        String expectedMessage = Priority.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullPriority_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_DESCRIPTION, VALID_COMPLETION_STATUS, VALID_DEADLINE,
                        null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Priority.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_DESCRIPTION, VALID_COMPLETION_STATUS,
                        VALID_DEADLINE, VALID_PRIORITY, invalidTags);
        assertThrows(IllegalValueException.class, task::toModelType);
    }
}
