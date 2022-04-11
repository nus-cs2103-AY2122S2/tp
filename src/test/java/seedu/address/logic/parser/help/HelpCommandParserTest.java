package seedu.address.logic.parser.help;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.HelpArgument;
import seedu.address.logic.commands.help.DetailHelpCommand;

public class HelpCommandParserTest {
    private static final String VALID_INPUT_1 = "list";
    private static final String VALID_INPUT_2 = "add";
    private static final String VALID_INPUT_3 = "export";

    private static final String WHITESPACE = " \t\r\n";

    private HelpCommandParser parser;

    @BeforeEach
    void init() {
        parser = new HelpCommandParser();
    }

    @Test
    public void parse_null_throwsNullPointerException() {
        assertParseFailure(parser, null, null);
    }

    @Test
    public void parse_invalidInput_throwsParseException() {
        assertParseFailure(parser, "invalid input", HelpArgument.COMMAND_NOT_FOUND_DESCRIPTION);
        assertParseFailure(parser, "21321w!23#%^&(*&^%!", HelpArgument.COMMAND_NOT_FOUND_DESCRIPTION);
    }

    @Test
    public void parse_emptyInput_returnsDetailHelpCommandWithOverallHelp() {
        DetailHelpCommand expectedHelpCommand =
                new DetailHelpCommand(new HelpArgument(""));
        assertParseSuccess(parser, "", expectedHelpCommand);
        // Multiple whitespaces
        assertParseSuccess(parser, "        ", expectedHelpCommand);
    }

    @Test
    public void parse_validInput_returnsDetailHelpCommand() {
        DetailHelpCommand expectedHelpCommand1 =
                new DetailHelpCommand(new HelpArgument(VALID_INPUT_1));
        DetailHelpCommand expectedHelpCommand2 =
                new DetailHelpCommand(new HelpArgument(VALID_INPUT_2));
        DetailHelpCommand expectedHelpCommand3 =
                new DetailHelpCommand(new HelpArgument(VALID_INPUT_3));

        assertParseSuccess(parser, VALID_INPUT_1, expectedHelpCommand1);
        assertParseSuccess(parser, VALID_INPUT_2, expectedHelpCommand2);
        assertParseSuccess(parser, VALID_INPUT_3, expectedHelpCommand3);
        // Multiple whitespace trailing
        assertParseSuccess(parser, WHITESPACE + VALID_INPUT_1 + WHITESPACE, expectedHelpCommand1);
        assertParseSuccess(parser, WHITESPACE + VALID_INPUT_2 + WHITESPACE, expectedHelpCommand2);
        assertParseSuccess(parser, WHITESPACE + VALID_INPUT_3 + WHITESPACE, expectedHelpCommand3);
    }
}
