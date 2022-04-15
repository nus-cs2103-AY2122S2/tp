package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_MIDTERM;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_MIDTERM;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRIORITY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_MIDTERM;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_LOW;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CS2102;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_TEST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_MIDTERM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MIDTERM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MIDTERM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_LOW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CS2102;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TEST;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTasks.CS2102_MIDTERM;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Name;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        Task expectedTask = new TaskBuilder(CS2102_MIDTERM).withTags(VALID_TAG_TEST).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_MIDTERM
                + DESCRIPTION_DESC_MIDTERM + DEADLINE_DESC_MIDTERM + PRIORITY_LOW
                + TAG_DESC_TEST, new AddCommand(expectedTask));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_TUTORIAL + NAME_DESC_MIDTERM
                + DESCRIPTION_DESC_MIDTERM + DEADLINE_DESC_MIDTERM + PRIORITY_LOW
                + TAG_DESC_TEST , new AddCommand(expectedTask));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, NAME_DESC_MIDTERM + DESCRIPTION_DESC_TUTORIAL
                + DESCRIPTION_DESC_MIDTERM + DEADLINE_DESC_MIDTERM + PRIORITY_LOW
                + TAG_DESC_TEST , new AddCommand(expectedTask));

        // multiple deadlines - last deadline accepted
        assertParseSuccess(parser, NAME_DESC_MIDTERM + DESCRIPTION_DESC_MIDTERM
                + DEADLINE_DESC_TUTORIAL + DEADLINE_DESC_MIDTERM + PRIORITY_LOW
                + TAG_DESC_TEST, new AddCommand(expectedTask));

        // multiple tags - all accepted
        Task expectedTaskMultipleTags = new TaskBuilder(CS2102_MIDTERM).withTags(VALID_TAG_TEST, VALID_TAG_CS2102)
                .build();
        assertParseSuccess(parser, NAME_DESC_MIDTERM
                + DESCRIPTION_DESC_MIDTERM + DEADLINE_DESC_MIDTERM + PRIORITY_LOW + TAG_DESC_TEST
                + TAG_DESC_CS2102, new AddCommand(expectedTaskMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Task expectedTask = new TaskBuilder(CS2102_MIDTERM).withTags().build();
        assertParseSuccess(parser, NAME_DESC_MIDTERM + DESCRIPTION_DESC_MIDTERM + DEADLINE_DESC_MIDTERM
                        + PRIORITY_LOW, new AddCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_MIDTERM + DESCRIPTION_DESC_MIDTERM + DEADLINE_DESC_MIDTERM
                + PRIORITY_LOW, expectedMessage);

        // missing description prefix
        assertParseFailure(parser, NAME_DESC_MIDTERM + VALID_DESCRIPTION_MIDTERM + DEADLINE_DESC_MIDTERM
                + PRIORITY_LOW, expectedMessage);

        // missing deadline prefix
        assertParseFailure(parser, NAME_DESC_MIDTERM + DESCRIPTION_DESC_MIDTERM + VALID_DEADLINE_MIDTERM
                + PRIORITY_LOW, expectedMessage);

        // missing priority prefix
        assertParseFailure(parser, NAME_DESC_MIDTERM + DESCRIPTION_DESC_MIDTERM + DEADLINE_DESC_MIDTERM
                + VALID_PRIORITY_LOW, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_MIDTERM + VALID_DESCRIPTION_MIDTERM + VALID_DEADLINE_MIDTERM
                + VALID_PRIORITY_LOW, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + DESCRIPTION_DESC_MIDTERM + DEADLINE_DESC_MIDTERM
                + PRIORITY_LOW + TAG_DESC_TEST + TAG_DESC_CS2102, Name.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, NAME_DESC_MIDTERM + INVALID_DESCRIPTION_DESC + DEADLINE_DESC_MIDTERM
                + PRIORITY_LOW + TAG_DESC_TEST + TAG_DESC_CS2102, Description.MESSAGE_CONSTRAINTS);

        // invalid deadline
        assertParseFailure(parser, NAME_DESC_MIDTERM + DESCRIPTION_DESC_MIDTERM + INVALID_DEADLINE_DESC
                + PRIORITY_LOW + TAG_DESC_TEST + TAG_DESC_CS2102, Deadline.MESSAGE_CONSTRAINTS);

        // invalid priority
        assertParseFailure(parser, NAME_DESC_MIDTERM + DESCRIPTION_DESC_MIDTERM + DEADLINE_DESC_MIDTERM
                + INVALID_PRIORITY + TAG_DESC_TEST + TAG_DESC_CS2102, Priority.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_MIDTERM + DESCRIPTION_DESC_MIDTERM + DEADLINE_DESC_MIDTERM
                + PRIORITY_LOW + INVALID_TAG_DESC + TAG_DESC_CS2102, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + DESCRIPTION_DESC_MIDTERM + INVALID_DEADLINE_DESC
                + PRIORITY_LOW + TAG_DESC_TEST + TAG_DESC_CS2102, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_MIDTERM + DESCRIPTION_DESC_MIDTERM
                + DEADLINE_DESC_MIDTERM + PRIORITY_LOW + TAG_DESC_TEST + TAG_DESC_CS2102,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
