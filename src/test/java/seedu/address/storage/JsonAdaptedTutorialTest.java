package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTutorial.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTutorials.T01;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assessment.UniqueAssessmentList;
import seedu.address.model.tutorial.Day;
import seedu.address.model.tutorial.Time;
import seedu.address.model.tutorial.TutorialName;
import seedu.address.model.tutorial.Venue;

public class JsonAdaptedTutorialTest {
    private static final String INVALID_TUTORIAL_NAME = "T@!$%04";
    private static final String INVALID_VENUE = "";
    private static final String INVALID_DAY = "Yesterday";
    private static final String INVALID_TIME = "24:01";
    private static final String INVALID_WEEKS = "-1";

    private static final String VALID_TUTORIAL_NAME = T01.getTutorialName().toString();
    private static final String VALID_VENUE = T01.getVenue().toString();
    private static final String VALID_DAY = T01.getDay().toString();
    private static final String VALID_TIME = T01.getTime().toString();
    private static final String VALID_WEEKS = String.valueOf(T01.getWeeks());

    private static final JsonAdaptedAttendanceList ATTENDANCE_LIST = new JsonAdaptedAttendanceList(
            "13", null);
    private static final List<JsonAdaptedAssessmentResults> ASSESSMENT_LIST = Stream.of(
            new JsonAdaptedAssessmentResults(null, null)).collect(Collectors.toList());

    @Test
    public void toModelType_validTutorialDetails_returnsTutorial() throws Exception {
        JsonAdaptedTutorial tutorial = new JsonAdaptedTutorial(T01);
        assertEquals(T01, tutorial.toModelType(new UniqueAssessmentList()));
    }

    @Test
    public void toModelType_invalidTutorialName_throwsIllegalValueException() {
        JsonAdaptedTutorial tutorial =
                new JsonAdaptedTutorial(INVALID_TUTORIAL_NAME, VALID_VENUE,
                        VALID_DAY, VALID_TIME, VALID_WEEKS, ATTENDANCE_LIST, ASSESSMENT_LIST);
        String expectedMessage = TutorialName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class,
                expectedMessage, () -> tutorial.toModelType(new UniqueAssessmentList()));
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTutorial tutorial =
                new JsonAdaptedTutorial(null, VALID_VENUE, VALID_DAY, VALID_TIME, VALID_WEEKS,
                        ATTENDANCE_LIST, ASSESSMENT_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                TutorialName.class.getSimpleName());
        assertThrows(IllegalValueException.class,
                expectedMessage, () -> tutorial.toModelType(new UniqueAssessmentList()));
    }

    @Test
    public void toModelType_invalidVenue_throwsIllegalValueException() {
        JsonAdaptedTutorial tutorial =
                new JsonAdaptedTutorial(VALID_TUTORIAL_NAME, INVALID_VENUE,
                        VALID_DAY, VALID_TIME, VALID_WEEKS, ATTENDANCE_LIST, ASSESSMENT_LIST);
        String expectedMessage = Venue.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class,
                expectedMessage, () -> tutorial.toModelType(new UniqueAssessmentList()));
    }

    @Test
    public void toModelType_nullVenue_throwsIllegalValueException() {
        JsonAdaptedTutorial tutorial =
                new JsonAdaptedTutorial(VALID_TUTORIAL_NAME, null,
                        VALID_DAY, VALID_TIME, VALID_WEEKS, ATTENDANCE_LIST, ASSESSMENT_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Venue.class.getSimpleName());
        assertThrows(IllegalValueException.class,
                expectedMessage, () -> tutorial.toModelType(new UniqueAssessmentList()));
    }

    @Test
    public void toModelType_invalidDay_throwsIllegalValueException() {
        JsonAdaptedTutorial tutorial =
                new JsonAdaptedTutorial(VALID_TUTORIAL_NAME, VALID_VENUE,
                        INVALID_DAY, VALID_TIME, VALID_WEEKS, ATTENDANCE_LIST, ASSESSMENT_LIST);
        String expectedMessage = Day.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class,
                expectedMessage, () -> tutorial.toModelType(new UniqueAssessmentList()));
    }

    @Test
    public void toModelType_nullDay_throwsIllegalValueException() {
        JsonAdaptedTutorial tutorial =
                new JsonAdaptedTutorial(VALID_TUTORIAL_NAME, VALID_VENUE,
                        null, VALID_TIME, VALID_WEEKS, ATTENDANCE_LIST, ASSESSMENT_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Day.class.getSimpleName());
        assertThrows(IllegalValueException.class,
                expectedMessage, () -> tutorial.toModelType(new UniqueAssessmentList()));
    }

    @Test
    public void toModelType_invalidTime_throwsIllegalValueException() {
        JsonAdaptedTutorial tutorial =
                new JsonAdaptedTutorial(VALID_TUTORIAL_NAME, VALID_VENUE,
                        VALID_DAY, INVALID_TIME, VALID_WEEKS, ATTENDANCE_LIST, ASSESSMENT_LIST);
        String expectedMessage = Time.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class,
                expectedMessage, () -> tutorial.toModelType(new UniqueAssessmentList()));
    }

    @Test
    public void toModelType_nullTime_throwsIllegalValueException() {
        JsonAdaptedTutorial tutorial =
                new JsonAdaptedTutorial(VALID_TUTORIAL_NAME, VALID_VENUE,
                        VALID_DAY, null, VALID_WEEKS, ATTENDANCE_LIST, ASSESSMENT_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName());
        assertThrows(IllegalValueException.class,
                expectedMessage, () -> tutorial.toModelType(new UniqueAssessmentList()));
    }

    @Test
    public void toModelType_invalidWeeks_throwsIllegalValueException() {
        JsonAdaptedTutorial tutorial =
                new JsonAdaptedTutorial(VALID_TUTORIAL_NAME, VALID_VENUE,
                        VALID_DAY, VALID_TIME, INVALID_WEEKS, ATTENDANCE_LIST, ASSESSMENT_LIST);
        String expectedMessage = "Weeks must be numeric and range from 1-60";
        assertThrows(IllegalValueException.class,
                expectedMessage, () -> tutorial.toModelType(new UniqueAssessmentList()));
    }

    @Test
    public void toModelType_nullWeeks_throwsIllegalValueException() {
        JsonAdaptedTutorial tutorial =
                new JsonAdaptedTutorial(VALID_TUTORIAL_NAME, VALID_VENUE,
                        VALID_DAY, VALID_TIME, null, ATTENDANCE_LIST, ASSESSMENT_LIST);
        String expectedMessage = "Weeks field is missing!";
        assertThrows(IllegalValueException.class,
                expectedMessage, () -> tutorial.toModelType(new UniqueAssessmentList()));
    }
}
