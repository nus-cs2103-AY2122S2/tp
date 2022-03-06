package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_NO_PARAMETERS_SUPPLIED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;

class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_validArgs_returnsClearModulesCommand() {
        List<Prefix> prefixes = new ArrayList<>();
        prefixes.add(PREFIX_NAME);
        prefixes.add(PREFIX_EMAIL);
        prefixes.add(PREFIX_PHONE);
        SortCommand correctCommand = new SortCommand(prefixes, "desc");
        assertParseSuccess(parser, " n/ e/ p/ o/desc", correctCommand);
    }

    @Test
    public void parse_noArgs_returnsClearModulesCommand() {
        assertParseFailure(parser, "",
                MESSAGE_NO_PARAMETERS_SUPPLIED);
    }

    @Test
    public void parse_invalidArgs_returnsClearModulesCommand() {
        assertParseFailure(parser, " 1 n/ o/desc",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " n/ e/ p/ o/descending",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
}
