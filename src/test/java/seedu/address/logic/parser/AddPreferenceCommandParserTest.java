package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREFERENCE_CAT_PREF;
import static seedu.address.logic.commands.CommandTestUtil.PREFERENCE_INDEX_CAT;
import static seedu.address.logic.commands.CommandTestUtil.PREFERENCE_INDEX_CAT_VALUE;
import static seedu.address.logic.commands.CommandTestUtil.PREFERENCE_INDEX_VALUE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREFERENCE_CATEGORY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREFERENCE_CATEGORY_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREFERENCE_VALUE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREFERENCE_VALUE_2;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERENCE_DETAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERENCE_KEY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddPreferenceCommand;


public class AddPreferenceCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPreferenceCommand.MESSAGE_USAGE);

    private AddPreferenceCommandParser parser = new AddPreferenceCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, PREFERENCE_CAT_PREF, MESSAGE_INVALID_FORMAT);

        // no category specified
        assertParseFailure(parser, PREFERENCE_INDEX_VALUE, MESSAGE_INVALID_FORMAT);

        // no preference specified
        assertParseFailure(parser, PREFERENCE_INDEX_CAT, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + PREFERENCE_CAT_PREF, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "-5" + PREFERENCE_CAT_PREF, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string" + PREFERENCE_CAT_PREF, MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_CLIENT;
        String userInput = PREFERENCE_INDEX_CAT_VALUE;

        AddPreferenceCommand expectedCommand = new AddPreferenceCommand(targetIndex, VALID_PREFERENCE_CATEGORY,
                VALID_PREFERENCE_VALUE);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_CLIENT;
        String userInput = PREFERENCE_INDEX_CAT_VALUE + " " + PREFIX_PREFERENCE_KEY + VALID_PREFERENCE_CATEGORY_2 + " "
                + PREFIX_PREFERENCE_DETAIL + VALID_PREFERENCE_VALUE_2;

        AddPreferenceCommand expectedCommand = new AddPreferenceCommand(targetIndex, VALID_PREFERENCE_CATEGORY_2,
                VALID_PREFERENCE_VALUE_2);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
