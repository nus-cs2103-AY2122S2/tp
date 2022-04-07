package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPropertyToBuyCommand;

public class AddPtbCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPropertyToBuyCommand.MESSAGE_USAGE);


    private AddPropertyToBuyCommandParser parser = new AddPropertyToBuyCommandParser();


    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPropertyToBuyCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_weirdString_failure() {
        assertParseFailure(parser, "This is weird", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsPresent_success() {
        //PropertyToBuy validProperty = new PropertyToBuyBuilder().withHouse()

        //assertParseSuccess(parser, PREAMBLE_WHITESPACE + HOUSE_TYPE_DESC_1 + LOCATION_DESC_1
          //      + PR_DESC_1, new AddPropertyToBuyCommand())
    }

}
