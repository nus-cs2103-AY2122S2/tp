package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_NO_PARAMETERS_SUPPLIED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_validArgs_returnsSortModulesCommand() {
        try {
            List<Prefix> prefixes = Arrays.asList(PREFIX_NAME, PREFIX_PHONE,
                    PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_STATUS, PREFIX_MODULE);
            List<String> firstOrder = Arrays.asList("asc", "asc", "desc", "asc", "asc", "desc");

            SortCommand correctCommand = new SortCommand(prefixes,
                    firstOrder, SortCommandParser.formatFields(prefixes, firstOrder));
            assertParseSuccess(parser, " n/asc p/asc e/desc a/asc s/asc m/desc", correctCommand);
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void parse_noArgs_returnsSortModulesCommand() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_NO_PARAMETERS_SUPPLIED, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_returnsSortModulesCommand() {
        assertParseFailure(parser, " 1 n/asc m/desc",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " n/asc e/desc p/asccc",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
}
