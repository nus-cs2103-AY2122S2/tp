package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.MULTIPLE_FIELDS_FIND_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.MULTIPLE_FIELDS_FIND;
import static seedu.address.logic.commands.CommandTestUtil.NAME_FIND_ALICE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_FIND_ALICE_BOB_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.predicates.CombineContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.AddressContainsKeywordsPredicate;
import seedu.address.testutil.PredicatesListBuilder;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsOneFieldFindCommand() {
        // no leading and trailing whitespaces
        List<String> nameKeywords = Arrays.asList("Alice", "Bob");
        CombineContainsKeywordsPredicate onlyNamePredicate = new CombineContainsKeywordsPredicate(
                new PredicatesListBuilder().addNamePredicate(
                new NameContainsKeywordsPredicate(nameKeywords)).build());
        FindCommand expectedFindCommand = new FindCommand(onlyNamePredicate);
        assertParseSuccess(parser, NAME_FIND_ALICE_BOB, expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, NAME_FIND_ALICE_BOB_WHITESPACE, expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsMultipleFieldsFindCommand() {
        // no leading and trailing whitespaces
        List<String> nameKeywords = Arrays.asList("Alice", "Bob");
        List<String> addressKeywords = Arrays.asList("Jurong", "Clementi");
        CombineContainsKeywordsPredicate multiPredicate = new CombineContainsKeywordsPredicate(
                new PredicatesListBuilder()
                        .addNamePredicate(new NameContainsKeywordsPredicate(nameKeywords))
                        .addAddressPredicate(new AddressContainsKeywordsPredicate(addressKeywords))
                        .build());
        FindCommand expectedFindCommand = new FindCommand(multiPredicate);
        assertParseSuccess(parser, MULTIPLE_FIELDS_FIND, expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, MULTIPLE_FIELDS_FIND_WHITESPACE, expectedFindCommand);
    }
}
