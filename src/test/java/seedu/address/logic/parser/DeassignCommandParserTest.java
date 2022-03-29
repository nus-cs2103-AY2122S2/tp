package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.GROUP_DESC_NUS_FINTECH_SOCIETY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_NAME_NUS_FINTECH_SOCIETY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeassignCommand;
import seedu.address.model.group.Group;
import seedu.address.testutil.GroupBuilder;

public class DeassignCommandParserTest {
    private DeassignCommandParser parser = new DeassignCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        Group expectedGroup = new GroupBuilder().withGroupName(VALID_GROUP_NAME_NUS_FINTECH_SOCIETY).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + 1
                + GROUP_DESC_NUS_FINTECH_SOCIETY, new DeassignCommand(INDEX_FIRST_PERSON, expectedGroup));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeassignCommand.MESSAGE_USAGE);

        // missing group name prefix
        assertParseFailure(parser, 1 + VALID_GROUP_NAME_NUS_FINTECH_SOCIETY,
                expectedMessage);

        // missing index
        assertParseFailure(parser, GROUP_DESC_NUS_FINTECH_SOCIETY,
                expectedMessage);
        // missing group name
        assertParseFailure(parser, String.valueOf(1),
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, 1 + VALID_GROUP_NAME_NUS_FINTECH_SOCIETY,
                expectedMessage);
    }

    @Test
    public void parse_invalidIndex_failure() {
        // invalid index 0
        assertParseFailure(parser, INVALID_INDEX + GROUP_DESC_NUS_FINTECH_SOCIETY, MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidValue_failure() {
        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + INDEX_FIRST_PERSON + GROUP_DESC_NUS_FINTECH_SOCIETY,
                MESSAGE_INVALID_INDEX);
    }
}
