package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CopyCommand;
import seedu.address.model.person.FormatPersonUtil;


class CopyCommandParserTest {
    private CopyCommandParser parser = new CopyCommandParser();
    private FormatPersonUtil fpCsv = new FormatPersonUtil(FormatPersonUtil.CSV_FORMAT);
    private List<Prefix> partialPrefixes = Arrays.asList(PREFIX_NAME, PREFIX_PHONE,
        PREFIX_EMAIL, PREFIX_ADDRESS);

    private List<Prefix> allPrefixes = Arrays.asList(PREFIX_NAME, PREFIX_PHONE,
        PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_STATUS, PREFIX_MODULE, PREFIX_COMMENT);

    @Test
    public void parse_validArgs_person() {
        CopyCommand correctCommand = new CopyCommand(INDEX_FIRST_PERSON, partialPrefixes, fpCsv);
        assertParseSuccess(parser, " 1 n/ p/ e/ a/ f/csv", correctCommand);
    }

    @Test
    public void parse_validArgs_addressBook() {
        CopyCommand correctCommand = new CopyCommand(partialPrefixes, fpCsv);
        assertParseSuccess(parser, " n/ p/ e/ a/ f/csv", correctCommand);
    }

    @Test
    public void parse_validArgs_noPrefixes() {
        CopyCommand correctCommand = new CopyCommand(INDEX_FIRST_PERSON, allPrefixes, fpCsv);
        assertParseSuccess(parser, " 1 f/csv", correctCommand);
    }

    @Test
    public void parse_validArgs_noArgs() {
        CopyCommand correctCommand = new CopyCommand(allPrefixes, fpCsv);
        assertParseSuccess(parser, " f/csv", correctCommand);
    }

    @Test
    public void parse_invalidArgs_noArgs() {
        assertParseFailure(parser, " +1-234 n/ f/csv",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyCommand.MESSAGE_USAGE));
    }
}
