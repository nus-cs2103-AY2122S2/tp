package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_RANGE_INDEX;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.contax.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.contax.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.commands.RangeCommand;


/**
 * Test for parses input arguments and creates a new RangeCommand object.
 */
public class RangeCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_RANGE_INDEX,
            RangeCommand.MESSAGE_USAGE);

    private RangeCommandParser parser = new RangeCommandParser();

    @Test
    public void parse_missingParts_failure() {

        // no index specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "range editperson", MESSAGE_INVALID_FORMAT);

        // to index is not specified
        assertParseFailure(parser, "range editperson p/123 from/1", MESSAGE_INVALID_FORMAT);

        // field name is provided
        assertParseFailure(parser, "range editperson n/name", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_someFieldsSpecified_invalidIndex() {
        String sampleUserInput1 = "range delete from/2 to/1";
        assertParseFailure(parser, sampleUserInput1, MESSAGE_INVALID_FORMAT);
        String sampleUserInput2 = "range delete from/a to/1";
        assertParseFailure(parser, sampleUserInput2, MESSAGE_INVALID_FORMAT);
        String sampleUserInput3 = "range delete from/2 to/b";
        assertParseFailure(parser, sampleUserInput3, MESSAGE_INVALID_FORMAT);
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
                new RangeCommand(fromIndex, toIndex, "range delete");
        assertParseSuccess(parser, sampleUserInput, expectedRangeCommand);
    }
}
