package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_REMINDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMINDER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMINDER_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemindCommand;
import seedu.address.model.Reminder;

class RemindCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemindCommand.MESSAGE_USAGE);

    private RemindCommandParser parser = new RemindCommandParser();

    @Test
    void parse_missingParts_failure() {
        Index targetIndex = INDEX_FIRST_PERSON;
        // no reminder specified
        assertParseFailure(parser, "1 " + INVALID_REMINDER_DESC, Reminder.MESSAGE_CONSTRAINTS);

        // no index specified
        assertParseFailure(parser, INVALID_REMINDER_DESC, MESSAGE_INVALID_FORMAT);

        // no index and no reminder specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_REMINDER_DESC, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_REMINDER_DESC, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_REMINDER_DESC, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + VALID_REMINDER_DESC;

        RemindCommand expectedCommand = new RemindCommand(targetIndex, Optional.of(new Reminder(VALID_REMINDER)));

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
