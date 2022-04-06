package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedLesson.MISSING_FIELD_MESSAGE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLessons.TEMPORARY_LESSON;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lesson.LessonAddress;
import seedu.address.model.lesson.LessonName;
import seedu.address.model.lesson.Subject;

public class JsonAdaptedLessonTest {
    private static final String INVALID_NAME = "B!ology Lesson";
    private static final String INVALID_SUBJECT = " ";
    private static final String INVALID_ADDRESS = " ";

    private static final String VALID_NAME = TEMPORARY_LESSON.getName().toString();
    private static final String VALID_SUBJECT = TEMPORARY_LESSON.getSubject().toString();
    private static final String VALID_ADDRESS = TEMPORARY_LESSON.getLessonAddress().toString();
    private static final JsonAdaptedDateTimeSlot VALID_DATETIMESLOT =
            new JsonAdaptedDateTimeSlot(
                    TEMPORARY_LESSON.getDateTimeSlot().getJsonDate(),
                    TEMPORARY_LESSON.getDateTimeSlot().getJsonStartTime(),
                    TEMPORARY_LESSON.getDateTimeSlot().getJsonDurationHours(),
                    TEMPORARY_LESSON.getDateTimeSlot().getJsonDurationMinutes()
            );

    private static final Boolean VALID_IS_RECURRING = TEMPORARY_LESSON.isRecurring();
    private static final List<JsonAdaptedStudent> VALID_STUDENTS = TEMPORARY_LESSON.getEnrolledStudents()
            .getStudentsList()
            .stream()
            .map(JsonAdaptedStudent::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validLessonDetails_returnsLesson() throws Exception {
        JsonAdaptedLesson lesson = new JsonAdaptedLesson(TEMPORARY_LESSON);
        assertEquals(TEMPORARY_LESSON, lesson.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(INVALID_NAME, VALID_SUBJECT, VALID_ADDRESS, VALID_DATETIMESLOT,
                        VALID_IS_RECURRING, VALID_STUDENTS);
        String expectedMessage = LessonName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(null, VALID_SUBJECT, VALID_ADDRESS, VALID_DATETIMESLOT,
                        VALID_IS_RECURRING, VALID_STUDENTS);
        String expectedMessage = MISSING_FIELD_MESSAGE;
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_invalidSubject_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_NAME, INVALID_SUBJECT, VALID_ADDRESS, VALID_DATETIMESLOT,
                        VALID_IS_RECURRING, VALID_STUDENTS);
        String expectedMessage = Subject.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_nullSubject_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_NAME, null, VALID_ADDRESS, VALID_DATETIMESLOT,
                        VALID_IS_RECURRING, VALID_STUDENTS);
        String expectedMessage = MISSING_FIELD_MESSAGE;
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_NAME, VALID_SUBJECT, INVALID_ADDRESS, VALID_DATETIMESLOT,
                        VALID_IS_RECURRING, VALID_STUDENTS);
        String expectedMessage = LessonAddress.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_NAME, VALID_SUBJECT, null, VALID_DATETIMESLOT,
                        VALID_IS_RECURRING, VALID_STUDENTS);
        String expectedMessage = MISSING_FIELD_MESSAGE;
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_nullDateTimeSlot_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_NAME, VALID_SUBJECT, VALID_ADDRESS, null,
                        VALID_IS_RECURRING, VALID_STUDENTS);
        String expectedMessage = MISSING_FIELD_MESSAGE;
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_nullIsRecurring_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_NAME, VALID_SUBJECT, VALID_ADDRESS, VALID_DATETIMESLOT,
                        null, VALID_STUDENTS);
        String expectedMessage = MISSING_FIELD_MESSAGE;
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }
}
