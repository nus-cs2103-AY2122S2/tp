package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteFieldCommand;
import seedu.address.model.person.EditPersonDescriptor;
import seedu.address.testutil.PersonBuilder;

public class DeleteFieldCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteFieldCommand.MESSAGE_USAGE);

    private final DeleteFieldCommandParser parser = new DeleteFieldCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", DeleteFieldCommand.MESSAGE_NO_PROVIDED_FIELD + "\n"
                + DeleteFieldCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_singleFieldSpecified_success() {
        String userInput = " c/ ";

        EditPersonDescriptor descriptor = new EditPersonDescriptor(new PersonBuilder()
                .withoutCompany().build());
        DeleteFieldCommand expectedCommand = new DeleteFieldCommand(descriptor, new PersonBuilder().build());

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleFieldsSpecified_success() {
        String userInput = " c/ j/";

        EditPersonDescriptor descriptor = new EditPersonDescriptor(new PersonBuilder()
                .withoutJobTitle().withoutCompany().build());
        DeleteFieldCommand expectedCommand = new DeleteFieldCommand(descriptor, new PersonBuilder().build());

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_deleteEmptyField_success() {
        String userInput = TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptor(new PersonBuilder().withoutTags().build());
        DeleteFieldCommand expectedCommand = new DeleteFieldCommand(descriptor, new PersonBuilder().build());

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
