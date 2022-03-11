package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_DURATION_HOURS_NEGATIVE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DURATION_HOURS_ZERO_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DURATION_MINUTES_EXCEEDS_59_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DURATION_MINUTES_NEGATIVE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DURATION_MINUTES_ZERO_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LESSON_DATE_FORMAT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LESSON_DATE_PASTDATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LESSON_START_TIME_FORMAT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LESSON_ADDRESS_DESC_AMK;
import static seedu.address.logic.commands.CommandTestUtil.LESSON_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LESSON_DURATION_HOURS_DESC_2HOUR;
import static seedu.address.logic.commands.CommandTestUtil.LESSON_DURATION_MINUTES_DESC_30MIN;
import static seedu.address.logic.commands.CommandTestUtil.LESSON_NAME_DESC_TRIAL_LESSON;
import static seedu.address.logic.commands.CommandTestUtil.LESSON_START_TIME_DESC_6PM;
import static seedu.address.logic.commands.CommandTestUtil.LESSON_SUBJECT_DESC_BIOLOGY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_ADDRESS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_DURATION_HOURS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_DURATION_MINUTES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_SUBJECT;
import static seedu.address.logic.parser.AddLessonCommandParser.INVALID_DURATION_MESSAGE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.INVALID_DATE_FORMAT_MESSAGE;
import static seedu.address.logic.parser.ParserUtil.INVALID_DATE_PASTDATE_MESSAGE;
import static seedu.address.logic.parser.ParserUtil.INVALID_START_TIME_MESSAGE;
import static seedu.address.logic.parser.ParserUtil.MINUTES_GREATER_THAN_59_MESSAGE;
import static seedu.address.logic.parser.ParserUtil.NEGATIVE_HOURS_MESSAGE;
import static seedu.address.logic.parser.ParserUtil.NEGATIVE_MINUTES_MESSAGE;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddLessonCommand;
import seedu.address.model.lesson.Lesson;

public class AddLessonCommandParserTest {
    private AddLessonCommandParser parser = new AddLessonCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // TODO: fix generation of lessonDate to update based on VALID_LESSON_DATE
        // (RELATED TO: CommandTestUtil#VALID_LESSON_DATE)
        Lesson expectedLesson = Lesson.makeTemporaryLesson(
                VALID_LESSON_NAME,
                VALID_LESSON_SUBJECT,
                VALID_LESSON_ADDRESS,
                LocalDateTime.of(2022, 12, 1, 18, 0),
                Integer.parseInt(VALID_LESSON_DURATION_HOURS),
                Integer.parseInt(VALID_LESSON_DURATION_MINUTES)
        );

        assertParseSuccess(parser, LESSON_NAME_DESC_TRIAL_LESSON + LESSON_SUBJECT_DESC_BIOLOGY
                + LESSON_ADDRESS_DESC_AMK + LESSON_DATE_DESC + LESSON_START_TIME_DESC_6PM
                + LESSON_DURATION_HOURS_DESC_2HOUR + LESSON_DURATION_MINUTES_DESC_30MIN,
                new AddLessonCommand(expectedLesson));
    }

    @Test
    public void addLessonCommand_withInvalidDateFormat_throwsParseException() {
        assertParseFailure(parser, LESSON_NAME_DESC_TRIAL_LESSON + LESSON_SUBJECT_DESC_BIOLOGY
                + LESSON_ADDRESS_DESC_AMK
                + INVALID_LESSON_DATE_FORMAT_DESC
                + LESSON_START_TIME_DESC_6PM + LESSON_DURATION_HOURS_DESC_2HOUR + LESSON_DURATION_MINUTES_DESC_30MIN,
                INVALID_DATE_FORMAT_MESSAGE);
    }

    @Test
    public void addLessonCommand_withInvalidStartTimeFormat_throwsParseException() {
        assertParseFailure(parser, LESSON_NAME_DESC_TRIAL_LESSON + LESSON_SUBJECT_DESC_BIOLOGY
                + LESSON_ADDRESS_DESC_AMK + LESSON_DATE_DESC
                + INVALID_LESSON_START_TIME_FORMAT_DESC
                + LESSON_DURATION_HOURS_DESC_2HOUR + LESSON_DURATION_MINUTES_DESC_30MIN,
                INVALID_START_TIME_MESSAGE);
    }

    @Test
    public void addLessonCommand_withZeroHoursAndZeroMinutes_throwsParseException() {
        assertParseFailure(parser, LESSON_NAME_DESC_TRIAL_LESSON + LESSON_SUBJECT_DESC_BIOLOGY
                + LESSON_ADDRESS_DESC_AMK + LESSON_DATE_DESC + LESSON_START_TIME_DESC_6PM
                + INVALID_DURATION_HOURS_ZERO_DESC
                + INVALID_DURATION_MINUTES_ZERO_DESC,
                INVALID_DURATION_MESSAGE);
    }

    @Test
    public void addLessonCommand_withNegativeHours_throwsParseException() {
        assertParseFailure(parser, LESSON_NAME_DESC_TRIAL_LESSON + LESSON_SUBJECT_DESC_BIOLOGY
                + LESSON_ADDRESS_DESC_AMK + LESSON_DATE_DESC + LESSON_START_TIME_DESC_6PM
                + INVALID_DURATION_HOURS_NEGATIVE_DESC
                + LESSON_DURATION_MINUTES_DESC_30MIN,
                NEGATIVE_HOURS_MESSAGE);
    }

    @Test
    public void addLessonCommand_withNegativeMinutes_throwsParseException() {
        assertParseFailure(parser, LESSON_NAME_DESC_TRIAL_LESSON + LESSON_SUBJECT_DESC_BIOLOGY
                + LESSON_ADDRESS_DESC_AMK + LESSON_DATE_DESC + LESSON_START_TIME_DESC_6PM
                + LESSON_DURATION_HOURS_DESC_2HOUR
                + INVALID_DURATION_MINUTES_NEGATIVE_DESC,
                NEGATIVE_MINUTES_MESSAGE);
    }

    @Test
    public void addLessonCommand_withMinutesExceeding59_throwsParseException() {
        assertParseFailure(parser, LESSON_NAME_DESC_TRIAL_LESSON + LESSON_SUBJECT_DESC_BIOLOGY
                + LESSON_ADDRESS_DESC_AMK + LESSON_DATE_DESC + LESSON_START_TIME_DESC_6PM
                + LESSON_DURATION_HOURS_DESC_2HOUR
                + INVALID_DURATION_MINUTES_EXCEEDS_59_DESC,
                MINUTES_GREATER_THAN_59_MESSAGE);
    }

    @Test
    public void addLessonCommand_withPastDate_throwsParseException() {
        assertParseFailure(parser, LESSON_NAME_DESC_TRIAL_LESSON + LESSON_SUBJECT_DESC_BIOLOGY
                        + LESSON_ADDRESS_DESC_AMK
                        + INVALID_LESSON_DATE_PASTDATE_DESC
                        + LESSON_START_TIME_DESC_6PM
                        + LESSON_DURATION_HOURS_DESC_2HOUR
                        + LESSON_DURATION_MINUTES_DESC_30MIN,
                INVALID_DATE_PASTDATE_MESSAGE);
    }
}
