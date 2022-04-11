package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.AddPackageCommandParserTest.VALID_PACKAGE_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditPackageCommand;

public class EditPackageCommandParserTest {


    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPackageCommand.MESSAGE_USAGE);

    private EditPackageCommandParser parser = new EditPackageCommandParser();

    @Test
    public void parse_missingParts_failure() {

        // no package name specified
        assertParseFailure(parser, VALID_PACKAGE_NAME, MESSAGE_INVALID_FORMAT);
    }
}
