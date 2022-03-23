package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_EQUALS;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_SEARCH_TYPE;
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
        assertParseFailure(parser, "batch editperson", MESSAGE_INVALID_FORMAT);

        // search type is not specified
        assertParseFailure(parser, "batch editperson =/1", MESSAGE_INVALID_FORMAT);

        // =/ is not specified
        assertParseFailure(parser, "batch editperson by/phone", MESSAGE_INVALID_FORMAT);

    }

    @Test
    public void parse_someFieldsSpecified_success() {
        ArgumentMultimap argumentMultimap = new ArgumentMultimap();
        argumentMultimap.put(PREFIX_EQUALS, "123");
        argumentMultimap.put(PREFIX_SEARCH_TYPE, "name");
        argumentMultimap.put(new Prefix(""), "editperson p/321");
        String sampleUserInput = "editperson p/321 by/name =/123";
        BatchCommand expectedBatchCommand =
                new BatchCommand("editperson p/321", new SearchType(SearchType.TYPE_NAME), argumentMultimap);
        assertParseSuccess(parser, sampleUserInput, expectedBatchCommand);
    }

    @Test
    public void parse_byDifferentSearchType_success() {
        String sampleUserInput = "editperson p/321 by/name =/123";
        ArgumentMultimap argumentMultimap = new ArgumentMultimap();
        argumentMultimap.put(PREFIX_EQUALS, "123");
        BatchCommand expectedBatchCommand =
                new BatchCommand("editperson p/321", new SearchType(SearchType.TYPE_NAME), argumentMultimap);
        assertParseSuccess(parser, sampleUserInput, expectedBatchCommand);
        sampleUserInput = "editperson p/321 by/phone =/123";
        expectedBatchCommand =
                new BatchCommand("editperson p/321", new SearchType(SearchType.TYPE_PHONE), argumentMultimap);
        assertParseSuccess(parser, sampleUserInput, expectedBatchCommand);
        sampleUserInput = "editperson p/321 by/email =/123";
        expectedBatchCommand =
                new BatchCommand("editperson p/321", new SearchType(SearchType.TYPE_EMAIL), argumentMultimap);
        assertParseSuccess(parser, sampleUserInput, expectedBatchCommand);
        sampleUserInput = "editperson p/321 by/address =/123";
        expectedBatchCommand =
                new BatchCommand("editperson p/321", new SearchType(SearchType.TYPE_ADDRESS), argumentMultimap);
        assertParseSuccess(parser, sampleUserInput, expectedBatchCommand);

    }

    @Test
    public void parse_someFieldsSpecified_invalidCommand() {
        ArgumentMultimap argumentMultimap = new ArgumentMultimap();
        argumentMultimap.put(PREFIX_EQUALS, "123");
        String sampleUserInput = "list";
        BatchCommand expectedBatchCommand =
                new BatchCommand("list", new SearchType(SearchType.TYPE_NAME), argumentMultimap);
        assertParseFailure(parser, sampleUserInput, MESSAGE_INVALID_FORMAT);

    }
}
