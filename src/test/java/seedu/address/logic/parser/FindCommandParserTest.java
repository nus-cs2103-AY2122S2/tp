package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMPTY_FIND_ARG;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MULTIPLE_EMPTY_FIND_FIELD;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SINGLE_EMPTY_FIND_FIELD;
import static seedu.address.logic.commands.CommandTestUtil.MULTIPLE_FIELDS_FIND;
import static seedu.address.logic.commands.CommandTestUtil.MULTIPLE_FIELDS_FIND_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_FIND_ALICE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_FIND_ALICE_BOB_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MULTIPLE_ADDRESS_KEYWORDS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MULTIPLE_NAME_KEYWORDS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.predicates.AddressContainsKeywordsPredicate;
import seedu.address.model.person.predicates.CombineContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.testutil.PredicatesListBuilder;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, INVALID_EMPTY_FIND_ARG,
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_singleEmptyField_throwsParseException() {
        assertParseFailure(parser, INVALID_SINGLE_EMPTY_FIND_FIELD,
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_NO_KEYWORD));
    }

    @Test
    public void parse_multipleEmptyField_throwsParseException() {
        assertParseFailure(parser, INVALID_MULTIPLE_EMPTY_FIND_FIELD,
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_NO_KEYWORD));
    }

    @Test
    public void parse_validArgs_returnsOneFieldFindCommand() {
        // no leading and trailing whitespaces
        CombineContainsKeywordsPredicate onlyNamePredicate = new CombineContainsKeywordsPredicate(
                new PredicatesListBuilder().addNamePredicate(
                new NameContainsKeywordsPredicate(VALID_MULTIPLE_NAME_KEYWORDS)).build());
        FindCommand expectedFindCommand = new FindCommand(onlyNamePredicate);
        assertParseSuccess(parser, NAME_FIND_ALICE_BOB, expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, NAME_FIND_ALICE_BOB_WHITESPACE, expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsMultipleFieldsFindCommand() {
        // no leading and trailing whitespaces
        CombineContainsKeywordsPredicate multiPredicate = new CombineContainsKeywordsPredicate(
                new PredicatesListBuilder()
                        .addNamePredicate(new NameContainsKeywordsPredicate(VALID_MULTIPLE_NAME_KEYWORDS))
                        .addAddressPredicate(new AddressContainsKeywordsPredicate(VALID_MULTIPLE_ADDRESS_KEYWORDS))
                        .build());
        FindCommand expectedFindCommand = new FindCommand(multiPredicate);
        assertParseSuccess(parser, MULTIPLE_FIELDS_FIND, expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, MULTIPLE_FIELDS_FIND_WHITESPACE, expectedFindCommand);
    }
}
