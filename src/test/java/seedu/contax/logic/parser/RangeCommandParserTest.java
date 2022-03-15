package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.contax.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.contax.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.contax.commons.core.Messages;
import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.commands.RangeCommand;


/**
 * Test for parses input arguments and creates a new RangeCommand object.
 */
public class RangeCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            RangeCommand.MESSAGE_USAGE);

    private RangeCommandParser parser = new RangeCommandParser();

    @Test
    public void parse_missingParts_failure() {

        // no index specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "range edit", MESSAGE_INVALID_FORMAT);

        // to index is not specified
        assertParseFailure(parser, "range edit p/123 from/1", MESSAGE_INVALID_FORMAT);

        // field name is provided
        assertParseFailure(parser, "range edit n/name", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_someFieldsSpecified_invalidIndex() {
        Index fromIndex = INDEX_FIRST_PERSON;
        Index toIndex = INDEX_SECOND_PERSON;
        String sampleUserInput = "range delete from/2 to/1";
        ArgumentMultimap argumentMultimap = new ArgumentMultimap();
        argumentMultimap.put(new Prefix(""), "range delete");
        argumentMultimap.put(new Prefix("/from"), "2");
        argumentMultimap.put(new Prefix("/to"), "1");
        RangeCommand expectedRangeCommand =
                new RangeCommand(fromIndex, toIndex, argumentMultimap, "range delete");
        assertParseFailure(parser, sampleUserInput, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index fromIndex = INDEX_FIRST_PERSON;
        Index toIndex = INDEX_SECOND_PERSON;
        String sampleUserInput = "range delete from/1 to/2";
        ArgumentMultimap argumentMultimap = new ArgumentMultimap();
        argumentMultimap.put(new Prefix(""), "range delete");
        argumentMultimap.put(new Prefix("/from"), "1");
        argumentMultimap.put(new Prefix("/to"), "2");
        RangeCommand expectedRangeCommand =
                new RangeCommand(fromIndex, toIndex, argumentMultimap, "range delete");
        assertParseSuccess(parser, sampleUserInput, expectedRangeCommand);
    }
}
