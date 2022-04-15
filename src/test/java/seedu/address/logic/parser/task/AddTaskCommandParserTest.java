package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_TASK2;
import static seedu.address.logic.commands.CommandTestUtil.ENDTIME_DESC_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.ENDTIME_DESC_TASK2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_ENDTIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_STARTTIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_TASK2;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.STARTTIME_DESC_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.STARTTIME_DESC_TASK2;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_EVENT;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_MEET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENDTIME_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STARTTIME_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_EVENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MEET;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTasks.TASK1;
import static seedu.address.testutil.TypicalTasks.TASK2;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.task.AddTaskCommand;
import seedu.address.model.name.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Date;
import seedu.address.model.task.EndTime;
import seedu.address.model.task.StartTime;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(TASK2).withTags(VALID_TAG_MEET).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_TASK2 + DATE_DESC_TASK2
                + STARTTIME_DESC_TASK2 + ENDTIME_DESC_TASK2 + TAG_DESC_MEET, new AddTaskCommand(expectedTask));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_TASK1 + NAME_DESC_TASK2 + DATE_DESC_TASK2 + STARTTIME_DESC_TASK2
                + ENDTIME_DESC_TASK2 + TAG_DESC_MEET, new AddTaskCommand(expectedTask));

        // multiple date - last date accepted
        assertParseSuccess(parser, NAME_DESC_TASK2 + DATE_DESC_TASK1 + DATE_DESC_TASK2 + STARTTIME_DESC_TASK2
                + ENDTIME_DESC_TASK2 + TAG_DESC_MEET, new AddTaskCommand(expectedTask));

        // multiple starttime - last starttime accepted
        assertParseSuccess(parser, NAME_DESC_TASK2 + DATE_DESC_TASK2 + STARTTIME_DESC_TASK1
                + STARTTIME_DESC_TASK2 + ENDTIME_DESC_TASK2 + TAG_DESC_MEET, new AddTaskCommand(expectedTask));

        // multiple endtime - last endtime accepted
        assertParseSuccess(parser, NAME_DESC_TASK2 + DATE_DESC_TASK2 + STARTTIME_DESC_TASK2
                + ENDTIME_DESC_TASK1 + ENDTIME_DESC_TASK2 + TAG_DESC_MEET, new AddTaskCommand(expectedTask));

        // multiple tags - all accepted
        Task expectedTaskMultipleTags = new TaskBuilder(TASK2).withTags(VALID_TAG_EVENT, VALID_TAG_MEET)
                .build();
        assertParseSuccess(parser, NAME_DESC_TASK2 + DATE_DESC_TASK2 + STARTTIME_DESC_TASK2
                + ENDTIME_DESC_TASK2 + TAG_DESC_EVENT + TAG_DESC_MEET, new AddTaskCommand(expectedTaskMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // no tags
        Task expectedTask = new TaskBuilder(TASK1).withTags().build();
        assertParseSuccess(parser,
                NAME_DESC_TASK1 + DATE_DESC_TASK1 + STARTTIME_DESC_TASK1 + ENDTIME_DESC_TASK1,
                new AddTaskCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_TASK1 + DATE_DESC_TASK1 + STARTTIME_DESC_TASK1
                + ENDTIME_DESC_TASK1, expectedMessage);

        // missing date prefix
        assertParseFailure(parser, NAME_DESC_TASK1 + VALID_DATE_TASK1 + STARTTIME_DESC_TASK1
                + ENDTIME_DESC_TASK1, expectedMessage);

        // missing starttime prefix
        assertParseFailure(parser, NAME_DESC_TASK1 + DATE_DESC_TASK1 + VALID_STARTTIME_TASK1
                + ENDTIME_DESC_TASK1, expectedMessage);

        // missing endtime prefix
        assertParseFailure(parser, NAME_DESC_TASK1 + DATE_DESC_TASK1 + STARTTIME_DESC_TASK1
                + VALID_ENDTIME_TASK1, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_TASK1 + VALID_DATE_TASK1 + VALID_STARTTIME_TASK1
                + VALID_ENDTIME_TASK1, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_TASK_NAME_DESC + DATE_DESC_TASK2 + STARTTIME_DESC_TASK2
                + ENDTIME_DESC_TASK2 + TAG_DESC_MEET + TAG_DESC_EVENT, Name.MESSAGE_CONSTRAINTS);

        // invalid Date
        assertParseFailure(parser, NAME_DESC_TASK2 + INVALID_TASK_DATE_DESC + STARTTIME_DESC_TASK2
                + ENDTIME_DESC_TASK2 + TAG_DESC_MEET + TAG_DESC_EVENT, Date.MESSAGE_CONSTRAINTS);

        // invalid StartTime
        assertParseFailure(parser, NAME_DESC_TASK2 + DATE_DESC_TASK2 + INVALID_TASK_STARTTIME_DESC
                + ENDTIME_DESC_TASK2 + TAG_DESC_MEET + TAG_DESC_EVENT, StartTime.MESSAGE_CONSTRAINTS);

        // invalid EndTime
        assertParseFailure(parser, NAME_DESC_TASK2 + DATE_DESC_TASK2 + STARTTIME_DESC_TASK2
                + INVALID_TASK_ENDTIME_DESC + TAG_DESC_MEET + TAG_DESC_EVENT, EndTime.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_TASK2 + DATE_DESC_TASK2 + STARTTIME_DESC_TASK2
                + ENDTIME_DESC_TASK2 + INVALID_TASK_TAG_DESC + VALID_TAG_EVENT, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TASK_NAME_DESC + DATE_DESC_TASK2 + STARTTIME_DESC_TASK2
                        + INVALID_TASK_ENDTIME_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_TASK2 + DATE_DESC_TASK2
                        + STARTTIME_DESC_TASK2 + ENDTIME_DESC_TASK2 + TAG_DESC_MEET + TAG_DESC_EVENT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
    }


}
