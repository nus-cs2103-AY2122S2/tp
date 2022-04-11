package manageezpz.logic.parser;

import static manageezpz.commons.core.Messages.MESSAGE_FIELD_NOT_EDITED;
import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT_BIND;
import static manageezpz.logic.parser.CliSyntax.PREFIX_AT_DATETIME;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DATE;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static manageezpz.logic.parser.CommandParserTestUtil.assertParseFailure;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import manageezpz.commons.core.index.Index;
import manageezpz.logic.commands.EditTaskCommand;
import manageezpz.logic.parser.exceptions.ParseException;


public class EditTaskCommandParserTest {

    private static final String INVALID_PREAMBLE_CONTAINS_WHITESPACE = "INVALID PREAMBLE";
    private static final String VALID_INPUT = "1 desc/ hello date/ 2022-01-01 at/ 1900";
    private EditTaskCommandParser parser = new EditTaskCommandParser();

    @Test
    public void parse_invalidPreamble_failure() {
        assertParseFailure(parser, INVALID_PREAMBLE_CONTAINS_WHITESPACE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT_BIND,
                EditTaskCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT_BIND,
                        EditTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_onlyPreambleNoPrefixes_failure() {
        assertParseFailure(parser, "1",
                MESSAGE_FIELD_NOT_EDITED + EditTaskCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_success() throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(VALID_INPUT,
                        PREFIX_DESCRIPTION, PREFIX_AT_DATETIME, PREFIX_DATE);
        String desc = argMultimap.getValue(PREFIX_DESCRIPTION).orElse("");
        String date = argMultimap.getValue(PREFIX_DATE).orElse("");
        String time = argMultimap.getValue(PREFIX_AT_DATETIME).orElse("");
        HashMap<String, Boolean> prefixStatusHash = new HashMap<>();
        prefixStatusHash.put("description", true);
        prefixStatusHash.put("date", true);
        prefixStatusHash.put("datetime", true);
        EditTaskCommand expectedCommand = parser.parse(VALID_INPUT);
        EditTaskCommand actualCommand = new EditTaskCommand(Index.fromZeroBased(0), desc, date, time, prefixStatusHash);
        assertEquals(expectedCommand, actualCommand);
    }
}
