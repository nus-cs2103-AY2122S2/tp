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
import static seedu.address.logic.commands.CommandTestUtil.STARTTIME_DESC_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.STARTTIME_DESC_TASK2;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_EVENT;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_MEET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_TASK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENDTIME_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENDTIME_TASK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STARTTIME_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STARTTIME_TASK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_EVENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MEET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.TASK_FIRST_INDEX;
import static seedu.address.testutil.TypicalIndexes.TASK_SECOND_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.EditTaskDescriptor;
import seedu.address.logic.commands.task.EditTaskCommand;
import seedu.address.model.name.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Date;
import seedu.address.model.task.EndTime;
import seedu.address.model.task.StartTime;
import seedu.address.testutil.EditTaskDescriptorBuilder;

public class EditTaskCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

    private EditTaskCommandParser parser = new EditTaskCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_TASK1, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditTaskCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_TASK1, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_TASK1, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_TASK_NAME_DESC, Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_TASK_DATE_DESC, Date.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_TASK_STARTTIME_DESC, StartTime.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_TASK_ENDTIME_DESC, EndTime.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_TASK_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid date followed by valid start time
        assertParseFailure(parser, "1" + INVALID_TASK_DATE_DESC + STARTTIME_DESC_TASK1, Date.MESSAGE_CONSTRAINTS);

        // valid date followed by invalid date
        assertParseFailure(parser, "1" + DATE_DESC_TASK2 + INVALID_TASK_DATE_DESC, Date.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Task} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_MEET + TAG_DESC_EVENT + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_MEET + TAG_EMPTY + TAG_DESC_EVENT, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_MEET + TAG_DESC_EVENT, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_TASK_NAME_DESC + INVALID_TASK_DATE_DESC
                        + VALID_STARTTIME_TASK1 + VALID_DATE_TASK1,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = TASK_SECOND_INDEX;
        String userInput = targetIndex.getOneBased() + DATE_DESC_TASK2 + TAG_DESC_MEET
                + STARTTIME_DESC_TASK1 + ENDTIME_DESC_TASK1 + NAME_DESC_TASK1 + TAG_DESC_EVENT;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withName(VALID_NAME_TASK1)
                .withDate(VALID_DATE_TASK2).withStartTime(VALID_STARTTIME_TASK1).withEndTime(VALID_ENDTIME_TASK1)
                .withTags(VALID_TAG_MEET, VALID_TAG_EVENT).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = TASK_FIRST_INDEX;
        String userInput = targetIndex.getOneBased() + DATE_DESC_TASK2 + STARTTIME_DESC_TASK1;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withDate(VALID_DATE_TASK2)
                .withStartTime(VALID_STARTTIME_TASK1).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = TASK_SECOND_INDEX;
        String userInput = targetIndex.getOneBased() + NAME_DESC_TASK1;
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withName(VALID_NAME_TASK1).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // Date
        userInput = targetIndex.getOneBased() + DATE_DESC_TASK1;
        descriptor = new EditTaskDescriptorBuilder().withDate(VALID_DATE_TASK1).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // start time
        userInput = targetIndex.getOneBased() + STARTTIME_DESC_TASK1;
        descriptor = new EditTaskDescriptorBuilder().withStartTime(VALID_STARTTIME_TASK1).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // end time
        userInput = targetIndex.getOneBased() + ENDTIME_DESC_TASK1;
        descriptor = new EditTaskDescriptorBuilder().withEndTime(VALID_ENDTIME_TASK1).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_MEET;
        descriptor = new EditTaskDescriptorBuilder().withTags(VALID_TAG_MEET).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = TASK_FIRST_INDEX;
        String userInput = targetIndex.getOneBased() + DATE_DESC_TASK1 + STARTTIME_DESC_TASK1 + ENDTIME_DESC_TASK1
                + TAG_DESC_MEET + DATE_DESC_TASK1 + STARTTIME_DESC_TASK1 + ENDTIME_DESC_TASK1 + TAG_DESC_MEET
                + DATE_DESC_TASK2 + STARTTIME_DESC_TASK2 + ENDTIME_DESC_TASK2 + TAG_DESC_EVENT;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withDate(VALID_DATE_TASK2)
                .withStartTime(VALID_STARTTIME_TASK2).withEndTime(VALID_ENDTIME_TASK2).withTags(VALID_TAG_MEET,
                        VALID_TAG_EVENT).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = TASK_SECOND_INDEX;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTags().build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
