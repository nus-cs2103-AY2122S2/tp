package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.contax.logic.commands.BatchCommand;
import seedu.contax.model.util.SearchType;

/**
 * Test for parses input arguments and creates a new BatchCommand object.
 */
public class BatchCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            BatchCommand.MESSAGE_USAGE);

    private BatchCommandParser parser = new BatchCommandParser();

    @Test
    public void parse_missingParts_failure() {

        // no index specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // no regex and no field specified
        assertParseFailure(parser, "batch edit", MESSAGE_INVALID_FORMAT);

        // search type is not specified
        assertParseFailure(parser, "batch edit =/1", MESSAGE_INVALID_FORMAT);

    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String sampleUserInput = "edit p/321 by/name =/123";
        ArgumentMultimap argumentMultimap = new ArgumentMultimap();
        argumentMultimap.put(new Prefix(""), "edit p/321");
        argumentMultimap.put(new Prefix("by"), "phone");
        argumentMultimap.put(new Prefix("regex"), "123");
        BatchCommand expectedBatchCommand =
                new BatchCommand("edit p/321", new SearchType(SearchType.TYPE_NAME), "123");
        assertParseSuccess(parser, sampleUserInput, expectedBatchCommand);
    }
}
