package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PARAMETERS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnassignCommand;

class UnassignCommandParserTest {
    private UnassignCommandParser parser = new UnassignCommandParser();

    @Test
    void parse_allFieldsPresent_success() {

        Index firstTask = INDEX_FIRST_TASK;
        Index secondPerson = INDEX_SECOND_PERSON;

        String userInput = firstTask.getOneBased() + " " + PREFIX_PERSON + secondPerson.getOneBased();
        Logger.getLogger("a").log(Level.INFO, userInput);
        assertParseSuccess(parser, userInput,
                new UnassignCommand(firstTask, secondPerson));
    }

    @Test
    void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_PARAMETERS + "p/\n"
                + UnassignCommand.MESSAGE_USAGE);

        assertParseFailure(parser, String.valueOf(INDEX_FIRST_TASK.getOneBased()), expectedMessage);
    }

    @Test
    void parse_noInput_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_PARAMETERS + "p/\n"
                + UnassignCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "", expectedMessage);
    }
}
